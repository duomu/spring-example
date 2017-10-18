package com.yll.springdata.jpa.repository;

import com.yll.springdata.jpa.config.JpaMysqlConfig;
import com.yll.springdata.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/10/16 10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {JpaH2Config.class})
@ContextConfiguration(classes = {JpaMysqlConfig.class})
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByName() {
        List<User> users = userRepository.findByName("duomu");
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info("user：" + u.getName() + "，" + u.getPassword());
            }
        }
    }

    @Test
    public void testInsertUser() {
        User user = new User("duomu","123",1);
        user.setName("duomu");
        user.setPassword("123");
        user.setStatus(1);
        user = userRepository.save(user);

        if (user!=null){
            LOGGER.info("user：" + user.getName() + "，" + user.getPassword());
        }
    }
}
