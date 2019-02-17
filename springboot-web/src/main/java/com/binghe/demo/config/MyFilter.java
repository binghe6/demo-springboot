package com.binghe.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

/**
 * 自定义过滤器
 * @author dongsw
 *
 */
@Order(1)
@WebFilter(filterName = "myFilter", urlPatterns = "/*")// 添加拦截器的名称和要拦截的url匹配规则
public class MyFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		System.out.println(" >>>>>>>>>>>>>> this is MyFilter,url :"
				+ request.getRequestURI());
		filterChain.doFilter(srequest, sresponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}