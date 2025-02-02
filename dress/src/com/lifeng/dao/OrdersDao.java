package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;


public interface OrdersDao{

	
	// 改变订单状态-管理员
	public void updateOrder(Order order);
	
	// 新建订单
	public void addOrder(Order order);
	
	// 新建订单明细项
	public void addOrderItem(OrderItem orderItem); 
	
	//订单id寻找
	public Order findOrderById(int id);

	public List<Order> findAll();
	public List<Order> findMine(int userid);

	
}
