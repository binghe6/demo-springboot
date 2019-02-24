package com.binghe.demo.config;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binghe.demo.realm.MyShiroRealm;

@Configuration
@Slf4j
public class ShiroConfig {
	
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件过滤器
	 *	</br>1,配置shiro安全管理器接口securityManage;
	 *	</br>2,shiro 连接约束配置filterChainDefinitions;
	 */
	@Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		//shiroFilterFactoryBean对象
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		log.info("-----------------Shiro拦截器工厂类注入开始");
		// 配置shiro安全管理器 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 指定要求登录时的链接
		shiroFilterFactoryBean.setLoginUrl("/page/login");

		// filterChainDefinitions拦截器=map必须用：LinkedHashMap，因为它必须保证有序
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置退出过滤器,具体的退出代码Shiro已经实现
//		filterChainDefinitionMap.put("/logout", "logout");
		
		/*
		 *  anon:例子/admins/**=anon 没有参数，表示可以匿名使用。
			authc:例如/admins/user/**=authc表示需要认证(登录)才能使用，没有参数
			roles：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
			perms：例子/admins/user/**=perms[user:add:*],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
			rest：例子/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
			port：例子/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString
			是你访问的url里的？后面的参数。
			authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证
			ssl:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
			user:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
			注：
			anon，authcBasic，auchc，user是认证过滤器，
			perms，roles，ssl，rest，port是授权过滤器
		 */
		// 配置要被拦截的链接 从上向下顺序判断
		filterChainDefinitionMap.put("/page/index", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		log.info("-----------------Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 身份认证realm; (账号密码校验；权限等)
	 *
	 * @return
	 */
	@Bean
    public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}
	
	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码,更改密码生成规则和校验的逻辑一致即可; ）
	 *
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于 // md5(md5(""));
		return hashedCredentialsMatcher;
	}
}