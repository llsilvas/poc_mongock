package com.example.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DatabaseChanglog {

    @ChangeSet(order = "001", id = "changeWithMongoDb", author = "mongock")
    public void changeWithMongoDb(MongoDatabase db){

        MongoCollection<Document> user = db.getCollection("user");
        Document doc = new Document("testName", "example").append("test", "1");
        user.insertOne(doc);
    }
}
