package com.yll.service;

import com.yll.springmvc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author：linlin.yang
 * @date：2017/10/13 14:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class JdbcTemplateTest {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateTest.class);

    @Autowired
    private DataSource dataSource;

    @Test
    public void testJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject("select count(*) from user",Integer.class);
        User user0 = jdbcTemplate.queryForObject("select * from user where id=1", new UserRowMapper());
        List<Map<String,Object>> userMap = jdbcTemplate.queryForList("SELECT * FROM user");
        List<User> users = jdbcTemplate.query("select * from user", new ResultSetExtractor<List<User>>() {
            public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<User> userList = new ArrayList<User>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setStatus(resultSet.getInt("status"));
                    userList.add(user);
                }
                return userList;
            }
        });

        //Lambda FI
//        List<User> users2=jdbcTemplate.query("select * from user",(resultSet->{
//            List<User> userList = new ArrayList<User>();
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setPassword(resultSet.getString("password"));
//                user.setStatus(resultSet.getInt("status"));
//                userList.add(user);
//            }
//            return userList;
//        }));

//        List<User> users2 = jdbcTemplate.query("select * from user", this::mapUser);

        List<User> userList = jdbcTemplate.query("SELECT * FROM USER", new UserRowMapper());
        logger.info("user count：" + (users == null ? 0 : users.size()));
        logger.info("userList count：" + (userList == null ? 0 : userList.size()));
//        logger.info("users2 count：" + (users2 == null ? 0 : users2.size()));
    }

    private List<User> mapUser(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setStatus(resultSet.getInt("status"));
            userList.add(user);
        }
        return userList;
    }

    private static final class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setStatus(resultSet.getInt("status"));
            return user;
        }
    }

}
