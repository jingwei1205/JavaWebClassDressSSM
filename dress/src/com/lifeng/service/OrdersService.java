package com.lifeng.service;

import java.util.List;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;


public interface OrdersService{
	
	// 改变订单状态
	public void updateOrder(Order order);	
	// 新建订单
	public void addOrder(Order order );	
	// 新建订单项
	public void addOrderItem(OrderItem orderItem); 	
	
	//订单id寻找
	public Order findOrderById(int id);
	public List<Order> findAll();

}
