package com.lifeng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.Cart;
import com.lifeng.entity.Dress;
import com.lifeng.entity.User;
import com.lifeng.service.CartService;
import com.lifeng.service.DressService;




@Controller
public class CartController{

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
	
	
	@RequestMapping("/addCart")
	@ResponseBody
	public Object addCart(HttpSession session,int dressid,int bookSum) {
		Map<String,Object> map=new HashMap<String,Object>();
		User user=(User) session.getAttribute("user");
		try {
			System.out.println(user.getId());
			Cart cart=cartService.findCart(dressid,user.getId());
			if(cart!=null) {			
				//�޸�����
				cart.setBooknum(bookSum+cart.getBooknum());
				cartService.updateCart(cart);		
			}else {
				cart=new Cart();
				cart.setBooknum(bookSum);
				cart.setUser(user);
				Dress dress=dressService.findDressById(dressid);
				cart.setDress(dress);
				cartService.addCart(cart);			
			}		
			map.put("msg", "true");		
		}catch(Exception e) {
			map.put("msg", "false");
			e.printStackTrace();
		}
		return map;		
	}
	
	@RequestMapping("/findCart")
	public ModelAndView findCart(HttpSession session) {
		User user=(User)session.getAttribute("user");		
		List<Cart> cartList=cartService.findCartByUserId(user.getId());
		double sum=0.0;
		for(Cart cart:cartList)	{
				sum+=cart.getBooknum()*cart.getDress().getPrice();
		}
		ModelAndView mv=new ModelAndView();
		mv.addObject("cartlist",cartList);
		mv.addObject("sum", sum);
		mv.addObject("state", false);		
		mv.setViewName("cart");
		return mv;
	}
	
	
	// �����ȥ���㡱���ȡҪ�����Cart�ļ���,��ȡ�ܽ�����ת��dobuy.jsp
	@RequestMapping("/cartToOrder")
	public ModelAndView cartToOrder(HttpSession session,int ids[]) {
		User user=(User)session.getAttribute("user");
		List<Cart> cartlist=new ArrayList<Cart>();
		Cart cart=new Cart();	
		double sum=0.0;
		for(int i=0;i<ids.length;i++) {
			cart= cartService.findCartByCartId(ids[i]);			
			cartlist.add(cart);
			sum+=cart.getBooknum()*cart.getDress().getPrice();
		}	
		System.out.println(sum);
		ModelAndView mv=new ModelAndView();
		session.setAttribute("cartlist", cartlist);
		session.setAttribute("sum", sum);

		mv.setViewName("dobuy");
		return mv;		
	}
	
	@RequestMapping("/updateCart")
	public void updateCart(HttpSession session,int cartid,int bookSum) {
		User user=(User)session.getAttribute("user");
		Cart cart=new Cart();
		cart.setId(cartid);
		cart.setBooknum(bookSum);
		cartService.updateCart(cart);	
	}
	
	@RequestMapping("/removeCart")
	@ResponseBody
	public Object removeCart(HttpSession session,int cartid) {
		User user=(User)session.getAttribute("user");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			cartService.removeCart(cartid);
			map.put("state", "true");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("state", "false");
		}		
		return map;		
	}
	
	@RequestMapping("/removeCartAll")
	@ResponseBody
	public Object removeCartAll(HttpSession session,int[] id) {
		User user=(User)session.getAttribute("user");
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			for(int i=0;i<id.length;i++) {
				cartService.removeCart(id[i]);
			}
			map.put("state", "true");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("state", "false");
		}		
		return map;		
	}
	
}
