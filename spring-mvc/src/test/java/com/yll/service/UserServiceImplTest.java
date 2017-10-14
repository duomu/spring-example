package com.yll.service;

import com.yll.springmvc.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class UserServiceImplTest {
    private static Logger logger = Logger.getLogger(UserServiceImplTest.class);
    @Autowired
    private IUserService userService;

    @Test
    public void testGetUserCount(){
        logger.info(userService.getUserByName("user1"));
    }


}
