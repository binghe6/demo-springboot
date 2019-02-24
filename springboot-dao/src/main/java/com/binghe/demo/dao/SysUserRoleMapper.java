package com.binghe.demo.dao;

import com.binghe.demo.pojo.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}