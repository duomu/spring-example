package com.yll.springdata.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * demo without spring-data
 * @author：linlin.yang
 * @date：2017/10/18 17:59
 */
public class RedisMainTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisMainTest.class);

    public static void main(String[] args) {
//        testSimple();
        testTransaction();
    }

    public static void testSimple() {
        Jedis jedis = RedisClient.getInstance();
        jedis.set("name", "simba");
        String name = jedis.get("name");
        logger.info("name：" + name);
        jedis.close();
    }

    /**
     * redis的事务可以保证一个client发起的事务中的命令可以原子性的执行，执行过程中不会受其他client的影响。
     */
    public static void testTransaction() {
        Jedis jedis = RedisClient.getInstance();
        jedis.watch("name");//乐观锁，若监视到name值发生变化，则本事务执行失败，返回给客户端一个空回复
        Transaction tx = jedis.multi();
        tx.set("name", "simba2");
        List<Object> results = tx.exec();
        if (results != null) {
            for (Object o : results) {
                System.out.println(o);
            }
        }

        System.out.println(jedis.get("name"));

        jedis.close();
    }

    private static JedisPool pool = null;

    public static void testDistributedLock() {
        Jedis jedis = RedisClient.getInstance();
    }
}
