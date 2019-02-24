package com.binghe.demo.dao;

import com.binghe.demo.pojo.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}