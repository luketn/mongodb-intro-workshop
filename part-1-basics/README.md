## MongoDB Basics

### MongoDB
MongoDB is an open source database written in C++:  
https://github.com/mongodb/mongo

It is completely free, extremely well maintained and mature.

It is a 'NoSQL' Document Database, meaning it does not store data in relational tables.

It allows you to read, write and index Documents, which are hierarchical data structures stored in a binary representation of JSON (BSON) - an open standard:  
https://bsonspec.org/  
https://docs.mongodb.com/manual/reference/bson-types/  

BSON is functionally equivalent to JSON, so you can always visualise your data and easily serialize it to and from JSON strings.

```json
{
  "this": {
    "is": [
      "a",
      "document",
      "having",
      {"field": "value"},
      "pairs"
    ]
  }
}
```

Data is organised into:
- Databases
  - Collections
    - Documents


### MongoDB the Company
The business model of the company which has grown up around this open source database is to sell support and services for MongoDB as well as a cloud hosting platform named Atlas. 

Company website:  
https://www.mongodb.com/

Atlas Cloud Hosting:  
https://www.mongodb.com/atlas

You can sign up for a free Atlas cloud database and use it as the back end for your own projects if you like!

### First exercise - Create Read Update Delete (CRUD) in the Shell
If you've completed the Setup steps you should have a shell open and connected to your local Docker MongoDB. 

Run through the steps in crud.js to create a database, and then CRUD a document. 

Code: [crud.js](./crud.js)

Further reading - shell methods on collections:  
https://docs.mongodb.com/manual/reference/method/js-collection/
