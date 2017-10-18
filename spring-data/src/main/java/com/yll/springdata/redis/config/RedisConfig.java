package com.yll.springdata.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author：linlin.yang
 * @date：2017/10/18 19:44
 */
@Configuration
//@Component
public class RedisConfig {
    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setHostName("192.168.229.132");
        jcf.setPort(6379);
        jcf.setPassword("123456");
        return jcf;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory jcf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jcf);
        return redisTemplate;
    }
}
