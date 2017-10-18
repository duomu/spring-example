package com.yll.springdata.mongo.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;

/**
 * demo without spring-data
 * @author：linlin.yang
 * @date：2017/10/17 20:26
 */
public class MongoMainTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoMainTest.class);

    public static void main(String[] args) {
        MongoCredential credential = MongoCredential.createCredential("duomu", "mydb", "123456".toCharArray());
        MongoClient mongo = new MongoClient(new ServerAddress("192.168.229.132", 27017), Arrays.asList(credential));
        MongoDatabase database = mongo.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("user");
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            LOGGER.info("" + it.next());
            i++;
        }
        //创建集合
//        database.createCollection("students");
        LOGGER.info("Collection created successfully");

    }

}
