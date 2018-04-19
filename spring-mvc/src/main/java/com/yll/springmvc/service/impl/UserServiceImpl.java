package com.yll.springmvc.service.impl;
import com.yll.springmvc.entity.User;
import com.yll.springmvc.entity.UserExample;
import com.yll.springmvc.mapper.UserMapper;
import com.yll.springmvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Unsafe;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 18:04
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

//    @Cacheable(value = "userCache")
    @Override
    public List<User> getUserByName(String name) {
        logger.info("缓存中没有，查数据库.....");
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public Integer insertUser(User user) {
        int result = userMapper.insert(user);
//        try {
//            int i = 1 / 0;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        List<User> users = getUserByName("锅巴3");
        if (users != null && users.size() > 0) {
            System.out.println("size：" + users.size());
        }
        return result;
    }
}
