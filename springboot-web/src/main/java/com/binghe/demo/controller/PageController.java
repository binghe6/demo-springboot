package com.binghe.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
