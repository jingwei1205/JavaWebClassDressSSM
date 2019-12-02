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
    public void regist(User user) {
        registerDao.insertUser(user);
    }
}
