## Data Modelling
MongoDB is a great database, and it is extremely easy to get up and running and build applications with it.

It is best-practice to start by creating the simplest model in MongoDB that you can. Create collections which broadly map to the [aggregate](https://martinfowler.com/bliki/DDD_Aggregate.html) domain objects in your Java application, and don't spend a lot of time over-optimizing upfront.

### Relationship Design - SQL -> Mongo
If you are migrating to MongoDB from a relational database management system (RDBMS), there are some conceptual changes to think about.

Here's a mapping from Tabular / RDBMS to Document / MongoDB concepts:

| Tabular / RDBMS | Document / MongoDB |
|-----------------|--------------------|
| Entity          | Entity             |
| Relationship    | Relationship       |
| Database        | Database           |
| Table           | Collection         |
| Column          | Field              |
| Row             | Document           |
| Join            | Embed or Link      |

Relational databases use a Tabular approach to storing data. Entities are created as Rows. An indexed list of Rows is a Table. Tables are logically grouped into Databases. Within tables information about the entities are organised into Columns. Relationships between entities are defined using special foreign key Columns. 

MongoDB uses a Document approach to storing data. Entities (and sometimes their aggregated child Entities) are stored as hierarchical Documents. An indexed list of Documents is a Collection are grouped into databases. Within documents information about the entities are organised as fields. Relationships between entities can be defined in one of two ways:
1. Embed: embed the child entity as a sub-document
```json
{
  "_id": 123,
  "description": "Two fruits!",
  "orderItems": [
    {"name": "banana", "price": 12.98},
    {"name": "kiwi", "price": 98.12}
  ]
}
```
2. Link: add a reference to the ID of a Document in another Collection
Order collection:
```json
{
  "_id": 1,
  "description": "Two fruits!"
}
```
OrderItem collection
```
{
  "_id": 1,
  "orderId": 1,
  "name": "banana", 
  "price": 12.98
}
{
  "_id": 2,
  "orderId": 1,
  "name": "kiwi", 
  "price": 98.12
}
```

The main difference here is that because the document is hierarchical and can have Sub-Documents (a field with type Object), you sometimes need to choose between _embedding_ a Document or set of Documents or _linking_ to them by their ID. At the beginning of the transition, favour embedding over linking - creating fewer collections requiring less lookups. Later on for performance reasons you might need to extract some sub-documents into separate collections. It's much easier to evolve the schema this way than trying to merge multiple collections into one later - so start simple and embedded. 

The great thing about MongoDB is that you can much more naturally represent Java object hierarchies in the database. No more 'ORM' or complex data access logic. Just serialize your object as a document and store it in a collection!

### What is a Document?


### Schemaless?
The document structure in MongoDB is sometimes thought of as 'schemaless' in the sense that as long as a document is valid BSON/JSON, you can store it in a collection alongside other documents even when these documents have inconsistent schemas. Whilst this is extremely flexible, of course in practice all useful data has structure. In MongoDB, rather than the schema being enforced by the database the responsibility for ensuring the integrity of the data structure in documents is up to the application logic. This actually gives you a lot of power to evolve schemas using application changes rather than having to co-ordinate schema updates to the database alongside the deployment of the application.

Just to complicate this slightly MongoDB does have an optional feature Data Validation, which can be used to enforce a schema on a collection:  
https://docs.mongodb.com/manual/core/schema-validation/

In general, you can think of MongoDB as having a flexible schema controlled by the application tier, rather than being totally schemaless.


### Deeper Dive
If you want to dive deeper into this topic I highly recommend running through this course:  
Data Modeling:  
https://university.mongodb.com/courses/M320/about    

### Jump In!
Beyond that the best way to learn data modelling is by practice. Jump in and build some things, see what works and doesn't. I've found in general design for MongoDB is quite intuitive and logical. Even better, because of the flexible schema, optimizations can be made by evolving schemas and indexes over time, so you don't have to get everything perfect upfront.
