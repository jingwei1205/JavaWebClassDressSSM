package com.lifeng.dao;

import com.lifeng.entity.User;

public interface RegisterDao {
    public User insertUser(String username,String password,String gender,
                           String email,String telephone,String introduce,
                           String shippingAddress,String name);
}
