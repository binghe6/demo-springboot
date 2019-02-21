package com.binghe.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.binghe.demo.interceptor.IdentityInterceptor;

/**
 * 拦截器配置
 * @author binghe
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new IdentityInterceptor())
//                .excludePathPatterns("/page/**")// 拦截器不检验的链接正则，多个链接规则用逗号分隔
//                 .addPathPatterns("/**");// 除了排除不校验的链接，其它链接都要经过此拦截器
    }

	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
	}
}
