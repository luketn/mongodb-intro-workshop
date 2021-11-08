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

2. IntelliJ (to write some sample code - community edition is fine)  
https://www.jetbrains.com/idea/


### Setup
To get your local machine set up, follow these steps:

1. Install the tools (see links above).
2. Run the following command on your shell:
```
docker run -d -it --rm --name mongodb-workshop -p 27017:27017 mongo:5.0.3
```
![image](https://user-images.githubusercontent.com/1756555/140829241-6ebe8b0e-4e9b-4eb0-9c98-2dad628aa776.png)

3. Connect to MongoDB using the Mongo Shell on the docker container:
```
docker exec -it mongodb-workshop mongosh
```
![image](https://user-images.githubusercontent.com/1756555/140830257-5b78da83-174a-4b7f-b0e5-e0af46427348.png)

At this point you have MongoDB running locally and a shell ready to explore MongoDB with and run through the workshop.


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


