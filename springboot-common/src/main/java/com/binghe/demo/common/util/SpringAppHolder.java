package com.binghe.demo.common.util;

import org.springframework.context.ApplicationContext;

/**
 * applicationContext工具
 * @author dongsw
 *
 */
public class SpringAppHolder {
    private static ApplicationContext applicationContext;

    public static void set(ApplicationContext applicationContext) {
        SpringAppHolder.applicationContext = applicationContext;
    }

    /**
     * 从applicationContext中获取spring管理的对象
     * @param tClass
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
