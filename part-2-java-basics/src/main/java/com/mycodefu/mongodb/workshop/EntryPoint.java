package com.mycodefu.mongodb.workshop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.time.Instant;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class EntryPoint {
    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("MongoDBWorkshop").withCodecRegistry(pojoCodecRegistry);;
            MongoCollection<Fruit> collection = database.getCollection("fruit", Fruit.class);
            System.out.println(collection.find().first());

            // Insert a banana
            InsertOneResult banana = collection.insertOne(Fruit.of("Banana", Shape.Oblong, 0));
            System.out.println(collection.find(eq("_id", banana.getInsertedId())).first());

            // Update the banana
            collection.updateOne(eq("_id", banana.getInsertedId()), Updates.set("purchased", Instant.now()));
            System.out.println(collection.find(eq("_id", banana.getInsertedId())).first());

            // Delete the banana
            collection.deleteOne(eq("_id", banana.getInsertedId()));
            System.out.println(collection.find(eq("_id", banana.getInsertedId())).first());
        }
    }
}
