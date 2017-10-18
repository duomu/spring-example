package com.yll.springdata.mongo.repository;

import com.yll.springdata.mongo.entity.User;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/10/18 16:42
 */
public interface UserDao {
    List<User> findUsersByLessAge(Integer age);
}
