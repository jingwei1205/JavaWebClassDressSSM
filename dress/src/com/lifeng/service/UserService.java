package com.lifeng.service;

import java.util.List;

import com.lifeng.entity.User;


public interface UserService {
	
	// 用户登录
	public User login(String username, String password);
	
}
