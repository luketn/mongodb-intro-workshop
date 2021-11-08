# MongoDB Introductory Workshop
Welcome to MongoDB!

This workshop is a short introduction to MongoDB in general and how to work with it from Java.

### Tools Used in the Workshop
To participate on your local machine (optional) you will need the following tools installed:

1. Docker (to run MongoDB)
https://www.docker.com/products/docker-desktop  
e.g.  
`
docker run -d -it --rm --name mongodb-workshop -p 27017:27017 mongo:5.0.3
`

2. Robomongo (also named Robo3T) as a GUI to connect to MongoDB
https://robomongo.org/


3. IntelliJ (to write some sample code - community edition is fine)  
https://www.jetbrains.com/idea/


### Setup
To get your local machine set up, follow these steps:

1. Install the tools (see links above).
2. Run the following command on your shell:
```
docker run -d -it --rm --name mongodb-workshop -p 27017:27017 mongo:5.0.3
```

3. Open Robomongo
4. 




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


