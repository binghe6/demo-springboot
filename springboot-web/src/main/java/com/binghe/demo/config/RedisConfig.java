//package com.binghe.demo.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * 配置redis
// * @author dongsw
// *
// */
//@Configuration
//public class RedisConfig {
//    @Value("${spring.redis.host}")
//    private  String host;
//    @Value("${spring.redis.port}")
//    private  int port;
//    @Value("${spring.redis.password}")
//    private  String password;
//    @Value("${spring.redis.timeout}")
//    private  int timeout;
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.redis.pool")
//    public JedisPoolConfig jedisPoolConfig() {
//    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//    	return jedisPoolConfig;
//    }
//    
//    @Bean
//    public JedisPool redisPoolFactory(JedisPoolConfig jedisPoolConfig) {
//    	JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//    	return jedisPool;
//    }
//}
