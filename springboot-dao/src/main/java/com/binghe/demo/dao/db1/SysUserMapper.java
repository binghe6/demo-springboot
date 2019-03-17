package com.binghe.demo.dao.db1;

import java.util.Map;

import com.binghe.demo.pojo.db1.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据参数查询系统用户
     * @param param
     * @return
     */
	SysUser getByParam(Map<String, Object> param);
}