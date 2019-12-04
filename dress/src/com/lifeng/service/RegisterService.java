package com.lifeng.service;

import com.lifeng.entity.User;

public interface RegisterService {
    public void regist(User user);
    public User checkUser(String username);
    public int deleteUser(int id);
    public User lookUserInName(String value);
    public User lookUserInId(int value);
    public int modifiablevariable(User user);
    public int modifyData(User user);
    public int modifyAddress(String shippingAddress,String telephone,String name,String username);
}
