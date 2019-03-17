package com.binghe.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置数据源
 * @author dongsw
 *
 */
@Configuration
@MapperScan(basePackages = "com.binghe.demo.dao.db2", sqlSessionTemplateRef  = "db2SqlSessionTemplate")// 扫描dao接口所在的包
public class DataSourceDB2Config {
	
	// 会自动从application.yml里读取此前缀的属性值，比如spring.datasource.url的值会自动通过setUrl设置值
    @ConfigurationProperties(prefix="spring.datasource.db2")
    @Bean(name="db2DataSource")
    public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
    }
    
    @Bean(name="db2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	    sessionFactory.setDataSource(dataSource);
	    //配置实体类默认路径
	    sessionFactory.setTypeAliasesPackage("com.binghe.demo.pojo.db2");// 实体对应包路径
	    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybatis/mapper/db2/*.xml"));
	    // 开启驼峰命名转换
	    sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
	    return sessionFactory.getObject();
	}
	
	@Bean(name="db2TransactionManager")
    public DataSourceTransactionManager txManager(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
