package com.binghe.demo;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Main {

	/**
	 * 生成盐值加密后的密码
	 */
	@Test
	public void test() {
		String salt = DigestUtils.md5Hex(new Date().getTime()+"");
		System.out.println("盐值="+salt);
		SimpleHash simpleHash = new SimpleHash("md5", "123456", salt, 1);
		System.out.println("盐值md5加密后的密码="+simpleHash);
	}
}
