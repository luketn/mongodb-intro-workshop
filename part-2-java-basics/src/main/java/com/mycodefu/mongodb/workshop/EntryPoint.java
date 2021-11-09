package com.mycodefu.mongodb.workshop;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mycodefu.mongodb.workshop.data.DefectDetails;
import com.mycodefu.mongodb.workshop.data.Fruit;
import com.mycodefu.mongodb.workshop.data.Severity;
import com.mycodefu.mongodb.workshop.data.Color;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.time.Instant;
import java.util.Arrays;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Projections.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.pojo.Conventions.*;

public class EntryPoint {
    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).conventions(Arrays.asList(SET_PRIVATE_FIELDS_CONVENTION, OBJECT_ID_GENERATORS, ANNOTATION_CONVENTION)).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("MongoDBWorkshop").withCodecRegistry(pojoCodecRegistry);;
            MongoCollection<Fruit> collection = database.getCollection("fruit", Fruit.class);
            System.out.println("Initial collection first fruit: " + collection.find().first());

            // Insert a banana
            InsertOneResult banana = collection.insertOne(Fruit.of("Banana", Color.Yellow, 0));
            System.out.println("Inserted banana: " + collection.find(eq("_id", banana.getInsertedId())).first());

            // Update the banana
            collection.updateOne(eq("_id", banana.getInsertedId()), set("purchased", Instant.now()));
            System.out.println("Updated banana: " + collection.find(eq("_id", banana.getInsertedId())).projection(excludeId()).first());

            // Delete the banana
            collection.deleteOne(eq("_id", banana.getInsertedId()));
            System.out.println("Deleted banana?: " + collection.find(eq("_id", banana.getInsertedId())).first());

            //Add a kiwi
            Fruit kiwiFruit = Fruit
                    .of("kiwi", Color.Green, 1)
                    .addDefectDetails(
                            DefectDetails.of("Rot", Severity.BitterPit)
                    )
                    .purchase();
            InsertOneResult kiwi = collection.insertOne(kiwiFruit);

            System.out.println("Inserted kiwi (sub-document!): " + collection.find(eq("_id", kiwi.getInsertedId())).first());
        }
    }
}
