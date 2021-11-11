# MongoDB Introductory Workshop
Welcome to MongoDB!

This workshop is a short introduction to MongoDB in general and how to work with it from Java.

### Tools Used in the Workshop
To participate on your local machine (optional) you will need the following tools installed:

1. Docker (to run MongoDB)
https://www.docker.com/products/docker-desktop  

2. IntelliJ (to write some sample code - community edition is fine)  
https://www.jetbrains.com/idea/


### Setup
To get your local machine set up, follow these steps:

1. Install the tools (see links above).
2. Run the following command on your shell:
```
docker run -d -it --rm --name mongodb-workshop -p 27017:27017 mongo:5.0.3
```
![a screenshot of the iTerm2 terminal showing the docker run output - the id of the running container daemon process](https://user-images.githubusercontent.com/1756555/140839586-6438f075-2c8c-48c2-8516-3650b21b31e3.png)

3. Connect to MongoDB using the Mongo Shell on the docker container:
```
docker exec -it mongodb-workshop mongosh
```
![a screenshot of the iTerm2 terminal showing the docker exec output - the mongo shell running from inside the docker process](https://user-images.githubusercontent.com/1756555/140839669-09980d99-89fc-4c01-b99d-558abb7f5d25.png)


At this point you have MongoDB running locally and a shell ready to explore MongoDB with and run through the workshop.

### Workshop

Part #1: [Basics](/part-1-basics/README.md)

Part #2: [Java Basics](/part-2-java-basics/README.md)

Part #3: [Data Modelling](/part-3-data-modelling/README.md)


### Additional Reading

MongoDB University Courses:
1. Basics:  
https://university.mongodb.com/courses/M001/about 
2. Diagnostic Thinking:  
https://university.mongodb.com/courses/M112/about
3. MongoDB for Java Developers:  
https://university.mongodb.com/courses/M220J/about
4. Data Modeling:  
https://university.mongodb.com/courses/M320/about


