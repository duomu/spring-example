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

    @Cacheable(value = "userCache")
    @Override
    public List<User> getUserByName(String name) {
        logger.info("缓存中没有，查数据库.....");
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(example);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }
}
