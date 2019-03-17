package com.binghe.demo.dao.db1;

import com.binghe.demo.pojo.db1.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}