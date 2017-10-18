package com.yll.springdata.redis;

import com.yll.springdata.redis.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/10/18 19:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
public class RedisTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;

    @Test
    public void test() {
        //操作String
        redisTemplate.opsForValue().set("name", "simba");
        String name = (String) redisTemplate.opsForValue().get("name");
        logger.info("name：" + name);

        //操作list
        redisTemplate.opsForList().rightPush("ids", 1);
        redisTemplate.opsForList().rightPush("ids", 2);
        redisTemplate.opsForList().rightPush("ids", 2);
        List<Object> ids = redisTemplate.opsForList().range("ids", 0, 10);
        if (ids != null && ids.size() != 0) {
            for (Object o : ids) {
                logger.info(o + " ");
            }
        }
    }
}
