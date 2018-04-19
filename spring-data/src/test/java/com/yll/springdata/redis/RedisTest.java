package com.yll.springdata.redis;

import com.yll.springdata.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * demo with spring-data
 * 注意：jedis和spring-data-redis的版本要匹配，否则会报错误
 * NoSuchMethodDef：org.springframework.util.Assert.isTrue(ZLjava/util/function/Supplier;)V
 * @author：linlin.yang
 * @date：2017/10/18 19:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {RedisConfig.class})
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
public class RedisTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * ValueOperations  处理String类型的值
     * ListOperations   处理List类型的值
     * HashOperations   处理Hash类型的值
     * SetOperations    处理Set类型的值
     */
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, Object, Object> hashOps;
    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOps;

    public static final long TIMEOUT = 30;

    @Test
    public void testString() {
        //by redisTemplate
        redisTemplate.opsForValue().set("name", "simba");
        String name = (String) redisTemplate.opsForValue().get("name");
        logger.info("name：" + name);

        //by ValueOperations
        valueOps.set("name", "smallred");
        name = (String) valueOps.get("name");
        logger.info("name：" + name);

        redisTemplate.delete("name");
    }

    @Test
    public void testList() {
        listOps.rightPush("numbers", 1);
        listOps.rightPush("numbers", 2);
        listOps.rightPush("numbers", 3);
        List<Object> list = listOps.range("numbers", 0, 1);
        if (list != null) {
            for (Object i : list) {
                logger.info(i.toString());
            }
        }

        redisTemplate.delete("numbers");
    }

    @Test
    public void testHash() {
        hashOps.put("users", "user1", "simba");
        hashOps.put("users", "user2", "smallred");
        hashOps.put("users", "user3", "lucy");

        Map<Object, Object> users = hashOps.entries("users");
        if (users != null && users.size() > 0) {
            for (Map.Entry<Object, Object> entry : users.entrySet()) {
                logger.info(entry.getKey() + "：" + entry.getValue());
            }
        }

        redisTemplate.delete("users");
    }

    @Test
    public void testSet() {
        setOps.add("ids", 1);
        setOps.add("ids", 1);
        setOps.add("ids", 2);
        setOps.add("ids", 3);
        Set<Object> set = setOps.members("ids");
        if (set != null && set.size() > 0) {
            for (Object o : set) {
                logger.info(o.toString());
            }
        }

        redisTemplate.delete("ids");
    }

    @Test
    public void testCacheMap() {
        Map<String, User> users = (Map<String, User>) valueOps.get("users");
        if (users == null) {
            logger.info("缓存中不存在，准备写入缓存...");
            users = new HashMap<>();
            for (int i = 1; i <= 10; i++) {
                User user = new User();
                user.setName("user" + 1);
                user.setAge(26);
                user.setAddress("China");
                users.put("user" + i, user);
            }

            valueOps.set("users", users);
        } else {
            logger.info("缓存中存在，打印数据");
            print(users);
        }
    }

    @Test
    public void testSerializer() {
        StringRedisSerializer stringSerializer = (StringRedisSerializer) redisTemplate.getStringSerializer();
        JdkSerializationRedisSerializer jdkSerializer = (JdkSerializationRedisSerializer) redisTemplate.getDefaultSerializer();
        byte[] value = stringSerializer.serialize("hello");
        String s = stringSerializer.deserialize(value);
        logger.info(s);

        User user = new User();
        value = jdkSerializer.serialize(user);
        user = (User) jdkSerializer.deserialize(value);
        logger.info("name={},age={},address={}", user.getName(), user.getAge(), user.getAddress());
    }

    public static void print(Map<String, User> resultMap) {
        if (resultMap == null) {
            return;
        }

        for (Map.Entry<String, User> entry : resultMap.entrySet()) {
            User user = entry.getValue();
            logger.info("{}：name={},age={},address={}", entry.getKey(), user.getName(), user.getAge(), user.getAddress());
        }
    }

    /**
     * 自定义格式化输出，支持动态参数
     * @param format
     * @param argArray
     * @return
     */
    public static String format(String format, Object... argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        return ft.getMessage();
    }

}
