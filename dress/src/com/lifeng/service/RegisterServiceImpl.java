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
    @Autowired
    private UserDao userDao;

    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public void regist(User user) {
        registerDao.insertUser(user);
    }

    @Override
    public User checkUser(String username){
        return userDao.whetherExist(username);
    }

    @Override
    public int deleteUser(int id){return userDao.deleteUser(id);}

    @Override
    public User lookUserInName(String value){return  userDao.lookUserInName(value);}

    @Override
    public User lookUserInId(int value){return  userDao.lookUserInId(value);}

    @Override
    public int modifiablevariable(User user){return userDao.modifiablevariable(user);}
}
