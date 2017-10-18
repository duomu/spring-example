package com.yll.springdata.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * @author：linlin.yang
 * @date：2017/10/18 14:05
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.yll.springdata.mongo.repository")//启动spring data的自动化mongo Repository生成功能
public class MongoConfig extends AbstractMongoConfiguration {
    /**
     * 指定数据库名字
     * @return
     */
    @Override
    protected String getDatabaseName() {
        return "mydb";
    }

    /**
     * 创建mongo客户端 提供用户认证
     * @return
     * @throws Exception
     */
    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createCredential("duomu", "mydb", "123456".toCharArray());
        return new MongoClient(new ServerAddress("192.168.229.132", 27017), Arrays.asList(credential));
    }

//    @Override
//    public MongoClient mongoClient() {
//        MongoCredential credential = MongoCredential.createCredential("duomu", "mydb", "123456".toCharArray());
//        return new MongoClient(new ServerAddress("192.168.229.132", 27017), Arrays.asList(credential));
//    }
}
