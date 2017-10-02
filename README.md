# TwitterApp
This is a springboot application which exposes below restful services. 
1. createUser - POST
2. UpdateUserFollows - POST
3. Get User by id - GET
4. Get user Followers list - GET
5. Get user Follows list - GET

6. Post Tweet by user - POST
7. Get User Timeline tweets - GET (service to get tweet timeline posted by user followed users)
8. Get tweet by id - GET
9. Get tweet by userid - GET

Used 3 databse servers. MongoDB, MySql, Redis. Used Swagger tool to see and run the services.


Requirements to run this application:-
1. Java8
2. Spring
3. Maven 3
4. MongoDB
5. Mysql
6. Redis

How to run the application
1. Start MongoDB server and create database twitterdb.
2. Create below collections with auto_index as false.
use twitterdb;
db.createCollection("User", {autoIndexId: false});
db.createCollection("Twitter", {autoIndexId: false});

3. Start Mysql server and create database twtrdb.
4. Execute below ddl on twtrdb
CREATE TABLE `userfollower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `follower` varchar(45) NOT NULL,
  `started` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
 
 5. Start Redis server (authuntentication is not used)
 6. Download / Pull the code
 7. Go to pom directory and run
  mvn spring-boot:run
   or
8. Build the application using mvn clean install, go to target folder and run 
java -jar <<app>>.jar
  
9. All database servers are started using default ports. If you are running databse servers in different ports and want to update username/password go to application.properties file in the code and update accordingly before you start running the application.
  




