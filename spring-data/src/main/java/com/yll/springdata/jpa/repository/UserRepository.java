package com.yll.springdata.jpa.repository;

import com.yll.springdata.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/10/14 11:00
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);

}
