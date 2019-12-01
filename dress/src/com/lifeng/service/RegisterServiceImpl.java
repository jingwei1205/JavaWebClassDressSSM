package com.lifeng.service;

import com.lifeng.dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifeng.dao.UserDao;
import com.lifeng.entity.User;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterDao registerDao;
    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    public void setUserDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public void regist(String username,String password,String gender,
                       String email,String telephone,String introduce,
                       String shippingAddress,String name) {
        registerDao.insertUser(username, password,gender, email, telephone,introduce, shippingAddress,name);
    }
}
