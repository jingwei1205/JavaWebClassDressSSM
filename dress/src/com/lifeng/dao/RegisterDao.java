package com.lifeng.dao;

import com.lifeng.entity.Dress;
import com.lifeng.entity.OrderItem;
import com.lifeng.entity.User;

public interface RegisterDao {
    public void insertUser(User user);
    public OrderItem selectItem(int orderid);
    public Dress selectDress(int dressid);
}
