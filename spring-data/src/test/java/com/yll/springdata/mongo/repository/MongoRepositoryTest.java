package com.yll.springdata.mongo.repository;

import com.yll.springdata.mongo.config.MongoConfig;
import com.yll.springdata.mongo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/10/17 19:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class MongoRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoRepositoryTest.class);
    @Autowired
    private MongoOperations mongo;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindCollection() {
        long count = mongo.getCollection("user").count();
        LOGGER.info("count：" + count);

        User user = new User();
        user.setName("user1");
        user.setAge(20);
        user.setAddress("Shandong");
        mongo.save(user);

        LOGGER.info("count：" + mongo.getCollection("user").count());

        List<User> users = mongo.findAll(User.class);
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info(u.getId() + " " + u.getName() + " " + u.getAge() + " " + u.getAddress());
            }
        }

        LOGGER.info("find by query");

        users = mongo.find(Query.query(Criteria.where("name").is("duomu")), User.class);
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info(u.getId() + " " + u.getName() + " " + u.getAge() + " " + u.getAddress());
            }
        }
    }

    @Test
    public void testRepository() {
        LOGGER.info("count：" + userRepository.count());

        User user = new User();
        user.setName("linlin");
        userRepository.save(user);

        LOGGER.info("count：" + userRepository.count());

        List<User> users = userRepository.findByName("duomu");
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info(u.getId() + " " + u.getName() + " " + u.getAge() + " " + u.getAddress());
            }
        }

        LOGGER.info("-----------findByAge-----------");

        users = userRepository.findByAge(26);
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info(u.getId() + " " + u.getName() + " " + u.getAge() + " " + u.getAddress());
            }
        }

        LOGGER.info("-----------findByAddress-----------");

        user = userRepository.findByAddress("beijing");
        if (user != null) {
            LOGGER.info(user.getId() + " " + user.getName() + " " + user.getAge() + " " + user.getAddress());
        }

        LOGGER.info("-----------findUsersByLessAge-----------");

        users = userRepository.findUsersByLessAge(26);
        if (users != null && users.size() > 0) {
            for (User u : users) {
                LOGGER.info(u.getId() + " " + u.getName() + " " + u.getAge() + " " + u.getAddress());
            }
        }
    }
}
