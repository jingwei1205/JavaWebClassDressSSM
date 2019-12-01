package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;


public interface OrdersDao{

	
	// �ı䶩��״̬-����Ա
	public void updateOrder(Order order);
	
	// �½�����
	public void addOrder(Order order);
	
	// �½�������ϸ��
	public void addOrderItem(OrderItem orderItem); 
	
	//����idѰ��
	public Order findOrderById(int id);


	
}
