package com.lifeng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeng.dao.OrdersDao;
import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private OrdersDao ordersDao;
	
	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	
	@Override
	public void updateOrder(Order order) {
		ordersDao.updateOrder(order);		
	}

	@Override
	public void addOrder(Order order) {
		ordersDao.addOrder(order);
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		ordersDao.addOrderItem(orderItem);		
	}

	@Override
	public Order findOrderById(int id) {
		return ordersDao.findOrderById(id);
	}

	

}
