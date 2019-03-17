package com.binghe.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.binghe.demo.dao.db1.SysUserMapper;
import com.binghe.demo.dao.db1.SysUserRoleMapper;
import com.binghe.demo.dao.db2.UserMapper;
import com.binghe.demo.pojo.db1.SysUser;
import com.binghe.demo.pojo.db1.SysUserRole;
import com.binghe.demo.pojo.db2.User;
import com.binghe.demo.service.IManyTrxService;

@Service
public class ManyTrxServiceImpl implements IManyTrxService {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IManyTrxService manyTrxService;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/**
	 * db1的事务
	 */
	@Transactional(transactionManager="db1TransactionManager", propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void testSimpleTrx() {
		SysUserRole sysUserRole = SysUserRole.of().setCreateDate(new Date()).setRoleId(7).setUserId(7);
		sysUserRoleMapper.insert(sysUserRole);
//		throw new RuntimeException();
	}


//	@Transactional(transactionManager="db2TransactionManager", propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Transactional(transactionManager="db2TransactionManager", readOnly=true)
	@Override
	public void testSimpleTrx2() {
//		User user = User.of().setAge(20).setName("yu");
//		userMapper.insert(user);
//		throw new RuntimeException();
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(JSON.toJSONString(user));
	}
	
//	@Transactional(transactionManager="db1TransactionManager", propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Transactional(transactionManager="db1TransactionManager", readOnly=true)
	@Override
	public void testManyTrx() {
//		SysUserRole sysUserRole = SysUserRole.of().setCreateDate(new Date()).setRoleId(12).setUserId(12);
//		sysUserRoleMapper.insert(sysUserRole);
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(1);
		System.out.println(JSON.toJSONString(sysUser));
		
		// 调用另一个数据源的时候，需要通过这种注入service后调方法的方式来切换数据源
		manyTrxService.testSimpleTrx2();
		
		SysUser sysUser2 = sysUserMapper.selectByPrimaryKey(1);
		System.out.println(JSON.toJSONString(sysUser2));
//		sysUserRole.setRoleId(50).setUserId(50);
//		sysUserRoleMapper.insert(sysUserRole);
	}

}
