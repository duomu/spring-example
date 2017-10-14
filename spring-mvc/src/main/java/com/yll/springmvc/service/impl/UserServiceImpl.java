package com.yll.springmvc.service.impl;
import com.yll.springmvc.entity.User;
import com.yll.springmvc.entity.UserExample;
import com.yll.springmvc.mapper.UserMapper;
import com.yll.springmvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 18:04
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getUserByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(example);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }
}
