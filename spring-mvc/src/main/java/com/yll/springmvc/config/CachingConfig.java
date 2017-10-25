package com.yll.springmvc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author：linlin.yang
 * @date：2017/10/19 14:07
 */
@Configuration
@EnableCaching//启用缓存
public class CachingConfig {

    /**
     * 声明缓存管理器
     *
     * @return
     */
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager();
//    }

    /**
     * 使用最流行的缓存供应商
     *
     * @param cacheManager
     * @return
     */
//    @Bean
//    public EhCacheCacheManager cacheManager(@Qualifier("cacheManager") CacheManager cacheManager) {
//        return new EhCacheCacheManager(cacheManager);
//    }
//
//    @Bean(name = "cacheManager")
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        return factoryBean;
//    }

    /**
     * redis缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory jcf) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jcf);
        return redisTemplate;
    }

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setHostName("test.redis.com");
        jcf.setPort(6379);
        jcf.setPassword("123456");
        return jcf;
    }
}
