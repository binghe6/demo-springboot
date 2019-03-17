package com.binghe.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.binghe.demo.interceptor.IdentityInterceptor;

/**
 * 拦截器配置
 * 此处不要继承WebMvcConfigurationSupport类，在springboot2.x版本使用WebMvcConfigurationSupport会使web自动配置失效，读取不到静态资源等文件
 * 要配置拦截器直接实现WebMvcConfigurer接口
 * @author binghe
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IdentityInterceptor())
                .excludePathPatterns("/page/**")// 拦截器不检验的链接正则，多个链接规则用逗号分隔
                 .addPathPatterns("/**");// 除了排除不校验的链接，其它链接都要经过此拦截器
    }

//	@Override
//	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
//	}
//	
//	/**
//	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
//	 */
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/");
//        super.addResourceHandlers(registry);
//    }
}
