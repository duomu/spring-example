package com.yll.springdata.redis;

import redis.clients.jedis.Jedis;

/**
 * @author：linlin.yang
 * @date：2018/5/12 18:57
 */
public class RedisClient {
    private static Jedis instance;

    private RedisClient() {
    }

    public static Jedis getInstance() {
        if (instance == null) {
            synchronized (RedisClient.class) {
                if (instance == null) {
                    instance = new Jedis("dev.com", 6379);
                    instance.auth("123456");
                }
            }
        }

        return instance;
    }
}
