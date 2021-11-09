## MongoDB and Java Basics

### MongoDB Java Driver
MongoDB Java Driver is an open source java library for interacting with MongoDB:  
https://github.com/mongodb/mongo-java-driver

You can add it to your project with Maven like this:

```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.3.4</version>
</dependency>
```

### Part #1 - Query Data
1. Insert a document again into the fruit collection from the shell.
2. Create a new Java project, add the dependency to the POM
3. In a new class static-void-main, add the code:
```java
try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
    MongoDatabase database = mongoClient.getDatabase("MongoDBWorkshop");
    MongoCollection<Document> collection = database.getCollection("fruit");
    Document doc = collection.find().first();
    if (doc != null) {
        System.out.println(doc.toJson());
    }
}
```
4. Run the code and see the fruit printed to the console:

```json
{"_id": {"$oid": "6189d4524228120076288c5f"}, "name": "Apple", "shape": "Round", "defects": 3, "perfect": false}
```

### Part #2
Add 
