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
db.news.find({companyId: 123}, {_id: 1, date: 1, description: 1}).sort({date: -1}).limit(10)
```
Index:
```json
{
  "companyId": 1,
  "date": -1
}
```
 
This search and sort will be entirely covered by the index, and no scan of the news collection will be required to determine the documents to fetch.  

Using the projection clause of the find(), the fields to return are limited to only those required by the UI. This means that MongoDB will only read 10 documents and return the description field from them following the index lookup.π


#### The Greater Good
What you want to do in MongoDB document modelling is to optimize the schema so that the most important clients get the best experience. This might well come at the cost of some other use-cases. For example, you might have to duplicate some data as a sub-document of many documents, meaning that writes will be slower whilst you apply that update to all of them. In general this gives the application much better performance if most of the time the client is reading.

#### SQL to Mongo Design Thinking
There is a temptation when coming from a SQL background to create one Collection per Table, with flat Documents. This turns out to be very inefficient in MongoDB, requiring the application to make many calls to the database to fulfill a use-case.

In general, joins in MongoDB are not desirable. If you're translating a SQL database schema to MongoDB look for clusters of tables which are often (or always) joined on by the application in order to return a result. These could well become a single Document with Sub-Documents for the joined tables. 

There is a balance to be struck here. Most often it wouldn't be the case that you 

#### Schemaless?
The document structure in MongoDB is sometimes thought of as 'schemaless' in the sense that as long as a document is valid BSON/JSON, you can store it in a collection alongside other documents even when these documents have inconsistent schemas. Whilst this is extremely flexible, of course in practice all data has structure. In MongoDB, rather than the schema being enforced by the database the responsibility for ensuring the integrity of the data structure in documentes is up to the application logic. This actually gives you a lot of power to evolve schemas using application changes rather than having to co-ordinate schema updates to the database alongside the deployment of the application.
Just to complicate this slightly MongoDB does have an optional feature Data Validation, which can be used to enforce a schema on a collection:
https://docs.mongodb.com/manual/core/schema-validation/

#### No Relationships?
In the majority of cases, no hard relationships are created between collections of documents. 

Of course in practice entities in the data model often refer to one-another.

This can be accomplished by having an ID reference to a document in another collection, and having the application make a further query to retrieve it. 

There are even ways to 'join' collections in a single query using the '$lookup' aggregation query, although it should not be a very commonly used tool if the document model matches the use-cases well.