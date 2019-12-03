package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.User;

public interface UserDao {	
	// 以用户名及密码寻找用户
	public User findUser(String username, String password) ;
	public User whetherExist(String username);
	public int deleteUser(String username);
	public User lookUserInName(String value);
	public User lookUserInId(int value);
}
