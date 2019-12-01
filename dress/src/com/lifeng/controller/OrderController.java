package com.lifeng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.Dress;
import com.lifeng.entity.Cart;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;
import com.lifeng.entity.User;
import com.lifeng.service.DressService;
import com.lifeng.service.CartService;
import com.lifeng.service.OrdersService;


@Controller
public class OrderController{
	
	@Autowired
	private OrdersService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private DressService dressService;		
	
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
		order.setOrdertime(new Date());	
		Dress dress=new Dress();
		for(Cart cart:list) {
			OrderItem oi=new OrderItem();
			dress=cart.getDress();
			//�������
			if(dress.getQuantity()-cart.getBooknum()<0) {
				throw new Exception("�����������ܳ������!");
			}
			//�ۼ����
			dress.setQuantity(dress.getQuantity()-cart.getBooknum());
			//��������
			dress.setSales(dress.getSales()+cart.getBooknum());
			dressService.updateDress(dress);			
			oi.setDress(dress);
			oi.setBuynum(cart.getBooknum());
			oi.setOrder(order);	
			order.getOrderitems().add(oi);	
			//���¶�������Ʒ�ӹ��ﳵ�������
			cartService.removeCart(cart.getId());
		}			
		orderService.addOrder(order);
		int orderid=order.getId();
		ModelAndView mv=new ModelAndView();
		mv.addObject("orderid",orderid);
		mv.setViewName("pay");
		return mv;
	}
	
}
