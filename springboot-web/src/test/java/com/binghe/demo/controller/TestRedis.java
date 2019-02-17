//package com.binghe.demo.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.binghe.demo.common.util.RedisUtil;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestRedis {
//
//	@Autowired
//	private RedisUtil redisUtil;
//	
//    @Test
//    public void test() throws Exception {
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	map.put("name", "binghe");
//    	map.put("age", 27);
//    	redisUtil.saveObject("person", map);
//    }
//}