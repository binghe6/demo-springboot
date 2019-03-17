package com.binghe.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.binghe.demo.pojo.db1.SysUser;

/**
 * 页面跳转
 * @author dongsw
 *
 */
@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 跳转到指定页面
	 * @param page
	 * @return
	 */
	@GetMapping("/{page}")
	public ModelAndView toPage(@PathVariable("page") String page) {
		ModelAndView mv = new ModelAndView(page);
		mv.addObject("flag", 1);
		return mv;
	}
	
	@GetMapping("/toSelected")
	public ModelAndView toSelected() {
		ModelAndView mv = new ModelAndView("selected");
		SysUser user = SysUser.of().setId(1).setUsername("binghe").setMobile("123");
		SysUser user2 = SysUser.of().setId(2).setUsername("dongsun").setMobile("456");
		List<SysUser> userList = new ArrayList<SysUser>();
		userList.add(user2);
		userList.add(user);
		mv.addObject("userList", userList);
		return mv;
	}
}
