### Data Modelling

MongoDB is a great database, and it is extremely easy to get up and running and build applications with it.

What is not so trivial is arriving at a well-thought-out, high performance data model for your application.

There are often different ways to design documents and collections, each with different trade-offs.

#### First Principle
The first principle I always follow in modelling MongoDB documents and indexes is:
- Optimize for the _core_ client queries

The ideal scenario should be that a single call from a client (say a RESTful API call from a browser) would translate into a single MongoDB query which would fetch a document or documents having everything required to return to the client. The query fields would all be included in an index, meaning that no scanning of the collection was required to determine the document(s) to return.

For example, let's say that you have an application where there is a home page. On the home page is a list of the 10 most recent news items which need to be displayed for the company of the user. You can create an index which covers a query:

Query:
```javascript
db.news
    .find({companyId: 123}, {_id: 1, date: 1, description: 1})
    .sort({date: -1})
    .limit(10)
```
Index:
```json
{
  "companyId": 1,
  "date": -1
}
```
 
This search and sort will be entirely covered by the index, and no scan of the news collection will be required to determine the documents to fetch.  

Using the projection clause of the find(), the fields to return are limited to only those required by the UI. This means that MongoDB will only read 10 documents and return the description field from them following the index lookup.

When a user clicks one of the news items on the home page to view the detail, a second call would be made by the client to fetch the individual news document by its ID.

Thinking further about the news document structure itself, you would prefer that the document contained everything needed to display the news to the user, however care should be taken that the document doesn't grow indefinitely and slow over time. For example, the news may have a 'like' feature, which allows users to like the news story. You might not want to store the details of every like that occurred within the document, preferring a count which increments when likes are added and decrements when they are removed:

news collection document:
```json
{
  "_id": ObjectId("12345"),
  "date": 12736123712,
  "description": "Some great news",
  "detail": "all the actual news data",
  "likes": 2
}
```

news_likes collection document:
```json
{
  "_id": ObjectId("12345"),
  "newsId": ObjectId("12345"),
  "personId": ObjectId("12345"),
  "date": 123123887
},
{
  "_id": ObjectId("123456"),
  "newsId": ObjectId("12345"),
  "personId": ObjectId("54321"),
  "date": 8237422312
}
...
```

As opposed to using a sub-document:

news collection document:
```json
{
  "_id": ObjectId("12345"),
  "date": 12736123712,
  "description": "Some great news",
  "detail": "all the actual news data",
  "likes": [
    {
      "personId": ObjectId("12345"),
      "date": 123123887
    },
    {
      "personId": ObjectId("54321"),
      "date": 8237422312
    }
  ]
}
```

The trade-off here is that:   
a) In the sub-document approach writes are faster because you only have to update one collection.
Reads get slower over time because the likes array may grow indefinitely the more people like the news.  

b) In the case of two-collections, writes are slower because you have to update the count in the news collection and the like in the news_likes collection, however reads are always fast no matter how many likes there are.

A subtlety to the performance of this is that when MongoDB updates an integer value likes property, it never has to move the document around as it has not changed size. If you add elements to a sub-document array MongoDB may well have to be moved - an expensive operation.

You might have to duplicate some data across many documents during this optimization, meaning that writes will be slower whilst you apply that update to all of them. In general this gives the application much better performance if most of the time the client is reading that collection.


#### SQL to Mongo Design Thinking
There is a temptation when coming from a SQL background to create one Collection per Table, with flat Documents. This turns out to be very inefficient in MongoDB, requiring the application to make many calls to the database to fulfill a use-case.

In general, joins in MongoDB are not desirable. If you're translating a SQL database schema to MongoDB look for clusters of tables which are often (or always) joined on by the application in order to return a result. These could well become a single Document with Sub-Documents for the joined tables. 

There is a balance to be struck here. Most often it wouldn't be the case that you 

#### Schemaless?
The document structure in MongoDB is sometimes thought of as 'schemaless' in the sense that as long as a document is valid BSON/JSON, you can store it in a collection alongside other documents even when these documents have inconsistent schemas. Whilst this is extremely flexible, of course in practice all data has structure. In MongoDB, rather than the schema being enforced by the database the responsibility for ensuring the integrity of the data structure in documents is up to the application logic. This actually gives you a lot of power to evolve schemas using application changes rather than having to co-ordinate schema updates to the database alongside the deployment of the application.  

Just to complicate this slightly MongoDB does have an optional feature Data Validation, which can be used to enforce a schema on a collection:  
https://docs.mongodb.com/manual/core/schema-validation/

In general, you can think of MongoDB as having a flexible schema controlled by the application tier, rather than being totally schemaless.

It's a good idea to maintain a version number string in every document, to allow easy evolution of schemas over time:

```json
{
  "_id": ObjectId("12345"),
  "_version": "0.0.1"
}
```

This way, if you want to change the schema you can use this version to identify which schema the document was created with and perform any required transformations.

In the most cases, schema evolution can be backwards compatible and just additive. In the rare cases where you want to completely change or remove an existing field you can bump the version number and add application logic to detect and perform the transform. 


#### No Relationships?
In the majority of cases, no hard relationships are created between collections of documents. 

Of course in practice entities in the data model often refer to one-another.

This can be accomplished by having an ID reference to a document in another collection, and having the application make a further query to retrieve it. 

There are even ways to 'join' collections in a single query using the '$lookup' aggregation query, although it should not be a very commonly used tool if the document model matches the use-cases well.
