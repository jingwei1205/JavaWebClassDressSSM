package com.lifeng.controller;
import	java.util.HashMap;
import	java.util.Map;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.lifeng.dao.RegisterDao;
import com.lifeng.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.Dress;
import com.lifeng.entity.Cart;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;
import com.lifeng.entity.User;


@Controller
public class OrderController{
	@Resource
	@Autowired
	private OrdersService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private DressService dressService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private UserService userService;

	private String username;

	
	public DressService getDressService() {
		return dressService;
	}
	public void setDressService(DressService dressService) {
		this.dressService = dressService;
	}
	
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	public OrdersService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrdersService orderService) {
		this.orderService = orderService;
	}	
	
	@RequestMapping("/addOrder")
	public ModelAndView addOrder(HttpSession session,
			String addressType,	String province,String city,
			String area,String detailed) throws Exception{
		User user=(User) session.getAttribute("user");
		double sum=(double)session.getAttribute("sum");
		String receiverAddress="";
		String telephone="";
		if(addressType=="newaddress") {
			receiverAddress=province+city+area+detailed;
		}else {
			receiverAddress=user.getShippingAddress();
			telephone=user.getTelephone();
		}
		List<Cart> list=(List<Cart>)session.getAttribute("cartlist");
		Order order=new Order();
		order.setMoney(sum);
		order.setPaystate(0);
		order.setReceiverAddress(receiverAddress);
		order.setReceiverName(user.getUsername());
		order.setReceiverPhone(telephone);		
		order.setUser(user);
		order.setUserid();
		System.out.println(order.getUserId());
		order.setOrdertime(new Date());	
		Dress dress=new Dress();
		for(Cart cart:list) {
			OrderItem oi=new OrderItem();
			dress=cart.getDress();
			//减掉库存
			if(dress.getQuantity()-cart.getBooknum()<0) {
				throw new Exception("订购数量不能超过库存!");
			}
			//扣减库存
			dress.setQuantity(dress.getQuantity()-cart.getBooknum());
			//增加销量
			dress.setSales(dress.getSales()+cart.getBooknum());
			dressService.updateDress(dress);			
			oi.setDress(dress);
			oi.setBuynum(cart.getBooknum());
			oi.setOrder(order);	
			order.getOrderitems().add(oi);	
			//已下订单的商品从购物车中清理掉
			cartService.removeCart(cart.getId());
		}			
		orderService.addOrder(order);
		int orderid=order.getId();
		ModelAndView mv=new ModelAndView();
		mv.addObject("orderid",orderid);
		mv.setViewName("pay");
		return mv;
	}

	@RequestMapping("/OrderShow")
	@ResponseBody
	public Object addDress(HttpServletRequest request, HttpServletResponse response){
		JSONObject info=new JSONObject();
		String type =request.getParameter("type");
		String pageNum =request.getParameter("pagenum");
		String whose=request.getParameter("whoseOrder");
		List<Order> order=orderService.findAll();
		Map<String, Object> map = new HashMap<String, Object>();
		int userId[]=new int[order.size()];
		int pnum[]=new int[order.size()];
		int dressid[]=new int[order.size()];
		int ostate[]=new int[order.size()];
		String dressName[]=new String[order.size()];
		String dressURL[]=new String[order.size()];
		double dressPrice[]=new double[order.size()];
		String username[]=new String[order.size()];
		Dress dress;
		OrderItem orderItem;
		User user;
		int j=0;
		for(int i=0;i<order.size();i++){
			userId[i] =order.get(i).getUserId();
			user=registerService.lookUserInId(userId[i]);
			username[i]=user.getUsername();
			orderItem=registerService.selectItem(order.get(i).getId());
			pnum[i]=orderItem.getBuynum();
            ostate[i]=orderItem.getOS();
			dressid[i]=orderItem.getDressId();
			dress=registerService.selectDress(dressid[i]);
			dressName[i]=dress.getDressname();
			dressURL[i]=dress.getImgurl();
			dressPrice[i]=dress.getPrice();
			System.out.println(dressName[i]);
		}
		if(!order.isEmpty()){
			map.put("state", "true");
			map.put("orders", order);
			map.put("pagenum", 1);
			map.put("userid",userId);
			map.put("pnum",pnum);
			map.put("dressid",dressid);
			map.put("ostate",ostate);
			map.put("dressName",dressName);
			map.put("dressPrice",dressPrice);
			map.put("dressURL",dressURL);
		}
		else {
			map.put("state", "false");
		}
		return map;
	}

	@RequestMapping("/orderN")
	@ResponseBody
	public int orderNumber(HttpServletRequest request, HttpServletResponse response){
		String type=request.getParameter("type");
		User user=registerService.lookUserInName(request.getParameter("username"));
		if("orderN".equals(type)) {
			List<Order> orders = orderService.findMine(user.getId());
			return orders.size();
		}else {
			List<Cart> carts=cartService.countCart(user.getId());
			return carts.size();
		}
	}

    @RequestMapping("/myShow")
    @ResponseBody
    public Object myShow(HttpServletRequest request, HttpServletResponse response){
        JSONObject info=new JSONObject();
        String type =request.getParameter("type");
        String pageNum =request.getParameter("pagenum");
        String whose=request.getParameter("whose");
        System.out.println(whose);
        User user=registerService.lookUserInName(this.username);
        int userN=user.getId();
        System.out.println(userN);
        List<Order> order=orderService.findMine(userN);

        Map<String, Object> map = new HashMap<String, Object>();
        int pnum[]=new int[order.size()];
        int dressid[]=new int[order.size()];
        int ostate[]=new int[order.size()];
        String dressName[]=new String[order.size()];
        String dressURL[]=new String[order.size()];
        double dressPrice[]=new double[order.size()];
        Dress dress;
        OrderItem orderItem;
        int j=0;
        for(int i=0;i<order.size();i++){
            orderItem=registerService.selectItem(order.get(i).getId());
            pnum[i]=orderItem.getBuynum();
            dressid[i]=orderItem.getDressId();
            dress=registerService.selectDress(dressid[i]);
            dressName[i]=dress.getDressname();
            dressURL[i]=dress.getImgurl();
            dressPrice[i]=dress.getPrice();
            ostate[i]=orderItem.getOS();
        }
        if(!order.isEmpty()){
            map.put("state", "true");
            map.put("orders", order);
            map.put("pnum",pnum);
            map.put("dressid",dressid);
            map.put("dressName",dressName);
            map.put("dressPrice",dressPrice);
            map.put("dressURL",dressURL);
            map.put("ostate",ostate);
        }
        else {
            map.put("state", "false");
        }
        return map;
    }

    @RequestMapping("/savename")
    @ResponseBody
    public void saveName(HttpServletRequest request, HttpServletResponse response) {
        this.username = request.getParameter("username");
    }
}
