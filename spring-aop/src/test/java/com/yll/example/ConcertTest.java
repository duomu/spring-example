package com.yll.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：linlin.yang
 * @date：2017/9/27 18:39
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)//注解配置
//@ContextConfiguration("classpath:springContext.xml")//xml配置
public class ConcertTest {
    @Autowired
    private Performance performance;

    @Test
    public void testPerform() {
        String name = "duomu";
        performance.perform(name);
    }
}
