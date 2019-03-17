package com.binghe.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.binghe.demo.dao.db1.SysRolePermissionMapper;
import com.binghe.demo.dao.db1.SysUserRoleMapper;
import com.binghe.demo.pojo.db1.SysRolePermission;
import com.binghe.demo.pojo.db1.SysUserRole;
import com.binghe.demo.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	@Autowired
	private ITestService testService;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void testTrx() {
		SysUserRole userRole = SysUserRole.of().setCreateDate(new Date()).setRoleId(3).setUserId(3);
		sysUserRoleMapper.insert(userRole);
//		testC(3);
	}
	
	/**
	 * 
	 * @param count 异常重试次数
	 */
	public void testC(int count) {
		System.out.println("第几次？？"+ count);
		try {
//			直接 testB();	 这样调用则REQUIRES_NEW事务不会生效，需要注入自己的service通过service来调用testB才能触发它的事务
			// 执行结果，testB()抛异常事务回滚，testTrx()事务正常提交
			testService.testB();
		} catch (Exception e) {
			count--;
			if (count<=0) {
				// 3次的时候就直接结束了
				return;
			}
			testC(count);
		}
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void testB() {
		SysRolePermission rolePermission = SysRolePermission.of().setCreateDate(new Date()).setPermissionId(1).setRoleId(2);
		sysRolePermissionMapper.insert(rolePermission);
		throw new RuntimeException();
	}
}
