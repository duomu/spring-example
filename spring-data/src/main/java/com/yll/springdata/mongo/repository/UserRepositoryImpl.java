package com.yll.springdata.mongo.repository;

import com.yll.springdata.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * UserRepositoryImpl的名字必须是UserRepository + Impl（默认）
 * @author：linlin.yang
 * @date：2017/10/18 16:43
 */
public class UserRepositoryImpl implements UserDao {
    @Autowired
    private MongoOperations mongo;

    @Override
    public List<User> findUsersByLessAge(Integer age) {
        Criteria where = Criteria.where("age").lte(age);
        Query query = Query.query(where);
        return mongo.find(query, User.class);
    }
}
