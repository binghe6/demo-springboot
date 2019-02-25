package com.binghe.demo.realm;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.binghe.demo.dao.SysUserMapper;
import com.binghe.demo.pojo.SysUser;

@Slf4j
//@Repository
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//	    System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//	    UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
//	    Map<String, Object> param = new HashMap<String, Object>();
//	    param.put("uid", userInfo.getUid());
//	    List<SysRole> roleList = sysRoleMapper.listByParamTables(param);
//	    for(SysRole role : roleList){
//	        authorizationInfo.addRole(role.getRole());
//	        param.clear();
//	        param.put("role_id", role.getId());
//	        List<SysPermission> permissionList = sysPermissionMapper.listByParamTables(param);
//	        for(SysPermission p : permissionList){
//	            authorizationInfo.addStringPermission(p.getPermission());
//	        }
//	    }
//	    return authorizationInfo;
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken用于存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		log.debug("用户登录认证：验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		String username = token.getUsername();
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("username", username);
	    param.put("state", SysUser.State.STATE_ON.getValue());
	    SysUser user = sysUserMapper.getByParam(param);
	    log.info("用户登录认证！用户信息user：" + user);
	    if(user == null){
	    	// 用户不存在
	        return null;
	    }
	    // 用户存在
		// 第一个参数，登陆后，需要在session保存数据
		// 第二个参数，查询到密码(此密码可由new SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)计算得出 )
	    // 第三个参数，加密的盐值
		// 第四个参数，realm名字
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	    		user, 
	    		user.getPassword(), 
	            ByteSource.Util.bytes(user.getSalt()),
	            getName()  
	    );
	    return authenticationInfo;
	}

}
