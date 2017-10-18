package com.yll.springdata.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * demo without spring-data
 * @author：linlin.yang
 * @date：2017/10/18 17:59
 */
public class RedisMainTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisMainTest.class);

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.229.132", 6379);
        jedis.auth("123456");
        jedis.set("name", "duomu");
        String name = jedis.get("name");
        logger.info("name：" + name);
    }
}
