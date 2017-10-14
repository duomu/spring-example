package com.yll.springmvc.service;


import com.yll.springmvc.entity.User;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 17:53
 */
public interface IUserService {

    User getUserById(Integer userId);
    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    List<User> getUserByName(String name);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer insertUser(User user);

}
