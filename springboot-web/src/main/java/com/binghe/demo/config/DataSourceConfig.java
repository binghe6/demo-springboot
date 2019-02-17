package com.binghe.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置数据源
 * @author dongsw
 *
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	// 会自动从application.yml里读取此前缀的属性值，比如spring.datasource.binghe.url的值会自动通过setUrl设置值
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean()
    public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
    }
}
