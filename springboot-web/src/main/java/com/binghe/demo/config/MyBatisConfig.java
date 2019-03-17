//package com.binghe.demo.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement// 开启事务
//@MapperScan("com.binghe.demo.dao")// 扫描dao接口所在的包
//public class MyBatisConfig {
//
//	@Autowired
//	private DataSourceDB1Config dataSourceConfig;
//	
//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception{
//		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//	    sessionFactory.setDataSource(dataSourceConfig.dataSource());
//	    //配置实体类默认路径
//	    sessionFactory.setTypeAliasesPackage("com.binghe.demo.pojo");// 实体对应包路径
//	    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//				.getResources("classpath:mybatis/mapper/*.xml"));
//	    // 开启驼峰命名转换
//	    sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//	    return sessionFactory.getObject();
//	}
//	
//	@Bean
//    public PlatformTransactionManager txManager() throws Exception {
//        return new DataSourceTransactionManager(dataSourceConfig.dataSource());
//    }
//}
