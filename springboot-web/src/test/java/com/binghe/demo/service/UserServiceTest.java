package com.binghe.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.binghe.demo.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void test() {
		userService.insertUser(User.of().setName("zhangsan").setAge(27));
	}
}
