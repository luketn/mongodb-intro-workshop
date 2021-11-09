package com.mycodefu.mongodb.workshop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class EntryPoint {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("MongoDBWorkshop");
            MongoCollection<Document> collection = database.getCollection("fruit");
            Document doc = collection.find().first();
            if (doc != null) {
                System.out.println(doc.toJson());
            }
        }
    }
}
