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

### Part #2 - Serialize to POJOs
Add a POJO for the Fruit class, and initialize MongoDB to convert it.

1. Update the code to include a POJO codec and use a Java type instead of the BSON Document when querying the database:
```java
CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
    MongoDatabase database = mongoClient.getDatabase("MongoDBWorkshop").withCodecRegistry(pojoCodecRegistry);;
    MongoCollection<Fruit> collection = database.getCollection("fruit", Fruit.class);
    Fruit fruit = collection.find().first();
    if (fruit != null) {
        System.out.println(fruit.toString());
    }
}
```
2. Run the code and see the fruit printed to the console:

```json
Fruit{id='6189d4524228120076288c5f', name='Apple', shape=Round, defects=3, perfect=false, purchased=2021-11-09T02:03:49.110Z}
```


Hint: Use Guava's collection helpers to simplify iterator to list conversions.


### Part #3 - Expand to full CRUD in Java
Refer to the worked example (or the documentation below) and build a full CRUD in Java:

Example Code: [EntryPoint.java](./src/main/java/com/mycodefu/mongodb/workshop/EntryPoint.java)

#### Documentation
Reads:  
https://mongodb.github.io/mongo-java-driver/4.3/driver/tutorials/perform-read-operations/  

Writes:  
https://mongodb.github.io/mongo-java-driver/4.3/driver/tutorials/perform-write-operations/  
