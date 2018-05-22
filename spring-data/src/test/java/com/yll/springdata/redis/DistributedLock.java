//package com.yll.springdata.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.exceptions.JedisException;
//
//import java.util.UUID;
//
///**
// * @author：linlin.yang
// * @date：2018/5/12 21:03
// */
//public class DistributedLock {
//    private Jedis jedis;
//    private String key;
//    private String val;
//    private int expire;
//    private int timeout;
//
//    private static final int DEFAULT_EXPIRE_TIME = 1;//单位秒
//
//    public DistributedLock(String key) {
//        this.key = key;
//        this.val = UUID.randomUUID().toString();
//        this.expire = DEFAULT_EXPIRE_TIME;
//    }
//
//    public DistributedLock(String key, int expire) {
//        this.key = key;
//        this.val = UUID.randomUUID().toString();
//        this.expire = expire;
//    }
//
//    public DistributedLock(Jedis jedis, String key, String val, int expire, int timeout) {
//        this.jedis = jedis;
//        this.key = key;
//        this.val = val;
//        this.expire = expire;
//        this.timeout = timeout;
//    }
//
//    public DistributedLock(Jedis jedis, String key, int expire, int timeout) {
//        this.jedis = jedis;
//        this.key = key;
//        this.expire = expire;
//        this.timeout = timeout;
//    }
//
//    private boolean lock(String key, int expire, int timeout) {
//        String retVal = null;
//        try {
//            String val = UUID.randomUUID().toString();
//
//
//            //在有限时间内尝试获取锁，超时获取不到锁则放弃
//            long endTime = System.currentTimeMillis() + timeout * 1000;
//            while (System.currentTimeMillis() < endTime) {
//                if (jedis.setnx(key, val) == 1) {
//                    jedis.expire(key, expire);
//                    return true;
//                }
//
//                //若没有获取到锁，判断锁的value和当前
//
//                //检查是否设置了超时时间
//                if (jedis.ttl(key) == -1) {
//                    jedis.expire(key, expire);
//                }
//            }
//
//        } catch (JedisException e) {
//            e.printStackTrace();
//        }finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//
//        return retVal;
//    }
//
//    public void unlock(String key, String val) {
//        Jedis jedis = jedisPool.getResource();
//        jedis.del(key);
//    }
//}
