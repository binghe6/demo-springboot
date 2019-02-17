package com.binghe.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.binghe.demo.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.insert(User.of().setName("binghe").setAge(27));
	}

	@Test
	public void testQuery() throws Exception {
		User user = userMapper.selectByPrimaryKey(3);
		System.out.println(user);
	}
	
	@Test
	public void testUpdate() throws Exception {
		User user = userMapper.selectByPrimaryKey(3);
		userMapper.updateByPrimaryKey(user.setAge(28));
	}
}