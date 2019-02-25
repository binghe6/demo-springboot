package com.binghe.demo.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.binghe.demo.thread.Hello;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TempService {

//	@Autowired
//	private ITestService testService;
//	
//	@Test
//	public void test() {
//		testService.testTrx();
//	}
	
	@Autowired
	private Hello hello;
	
	@Test
	public void test() throws InterruptedException, ExecutionException, TimeoutException {
		hello.sayHello("123");
		log.info(">>>>>>>>>>>> 1");
		log.info(">>>>>>>>>>>> 2");
		log.info(">>>>>>>>>>>> 3");
		log.info(">>>>>>>>>>>> 4");
	}
}
