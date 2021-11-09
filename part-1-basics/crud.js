// note that you have full intellisense in the shell - press tab tab for autocomplete
// type help for help

// Create a new Database
use MongoDBWorkshop

// Create a new Collection, and insert a Document into it.
db.fruit.insertOne({
    "name": "Apple",
    "shape": "Round",
    "defects": 3,
    "perfect": false
})

// Check out the document you just created
db.fruit.find()

// Update the document - add a field 'purchased' with the current date - two parameters (query, update statement)
db.fruit.updateOne(
    {
        _id: ObjectId("6189cff94228120076288c5e")
    },
    {
        $set: {purchased: new Date()}
    }
)

// Check out the updated document
db.fruit.find()

// Delete the document (replace '6189c6eb0ccddda1c38b2f57' with the _id from the find() results)
db.fruit.deleteOne({
    "_id": ObjectId("6189cff94228120076288c5e")
})

// See the document has gone
db.fruit.find()