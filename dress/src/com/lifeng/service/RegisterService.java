package com.lifeng.service;

import com.lifeng.entity.User;

public interface RegisterService {
    public void regist(String username,String password,String gender,
                       String email,String telephone,String introduce,
                       String shippingAddress,String name);
}
