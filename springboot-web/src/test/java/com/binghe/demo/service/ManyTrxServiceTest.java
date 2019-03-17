package com.binghe.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTrxServiceTest {

	@Autowired
	private IManyTrxService manyTrxService;
	
	@Test
	public void test() {
		manyTrxService.testSimpleTrx();
	}
	
	@Test
	public void test2() {
		manyTrxService.testSimpleTrx2();
	}
	
	@Test
	public void testMany() {
		manyTrxService.testManyTrx();
	}
}
