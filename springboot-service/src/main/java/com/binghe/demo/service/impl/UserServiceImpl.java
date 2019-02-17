package com.binghe.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.binghe.demo.dao.UserMapper;
import com.binghe.demo.pojo.User;
import com.binghe.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	@Override
	public void insertUser(User user) {
		userMapper.insert(user);
//		int a = 1;
//		if (a == 1) {
//			throw new RuntimeException();
//		}
	}

	@Override
	public void getUserById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
