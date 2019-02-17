package com.binghe.demo.service;

import com.binghe.demo.pojo.User;

public interface IUserService {

	public void insertUser(User user);
	
	public void getUserById(Integer id);
}
