package com.yll.springdata.mongo.repository;

import com.yll.springdata.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/10/18 16:32
 */
public interface UserRepository extends MongoRepository<User, String>, UserDao {
    List<User> findByName(String name);

    List<User> findByAge(Integer age);

    User findByAddress(String address);

}
