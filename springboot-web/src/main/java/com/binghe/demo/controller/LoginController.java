package com.binghe.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.binghe.demo.pojo.SysUser;

@Controller
public class LoginController {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public ModelAndView login(SysUser user) {
		ModelAndView mv = new ModelAndView();
		// 将用户名和密码放入token
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 获取身份对象
		Subject subject = SecurityUtils.getSubject();
		try {
			// 登陆验证
			subject.login(token);
			// 登陆成功
			mv.setViewName(InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/page/index");
		} catch (AuthenticationException e) {
			// 登陆失败
			mv.setViewName("login");
			mv.addObject("msg", "用户名或密码错误");
		}
		return mv;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@GetMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/page/login";
	}

}
