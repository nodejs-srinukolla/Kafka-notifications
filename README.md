# Notification Applications

This application is a POC for the Push Notification and Real Time Notification handling in an Angular 5 Web Application.


## Features!

  - **Real Time Notifications:** Using Socket Connection between NodeJS Server and Angular 5 Application.
  [![N|Solid](https://raw.githubusercontent.com/ERS-HCL/Notifications/master/Angular5App/src/assets/img/Realm%20Time%20Notification.png)](#)
  
- **Push Notifications:** Using Google FCM registers the Application's Service Worker on Chrome to enable Push Notifications.
  [![N|Solid](https://raw.githubusercontent.com/ERS-HCL/Notifications/master/Angular5App/src/assets/img/Push%20Notification.png)](#)

## Components

The Application consists of the following components:
  - Spring Boot Application (:8080)
  - Apache Kafka (:9092)
  - Apache ZooKeeper (:2181)
  - NodeJS server (:3000)
  - Angular 5 Application (:8081)
  - MongoDB (Required in Replication) (:27017)
  

## Architecture Diagram
[![N|Solid](https://github.com/ERS-HCL/Notifications/blob/master/Angular5App/src/assets/img/Notification_Architecture.png?raw=true)](#)

## Tech

Frameworks and Softwares used in the application are :

* [Spring Boot] - a rapid application development platform
* [Spring Kafka] - supports development of Kafka-based messaging solutions.
* [Spring Data MongoDB] - provides integration with the MongoDB document database
* [Apache Kafka] - a fast, scalable, durable, and fault-tolerant publish-subscribe messaging system
* [Apache ZooKeeper] - a centralized open-source server for maintaining and managing configuration information, naming conventions and synchronization for distributed cluster environment.
* [MongoDB] - a NoSQL database
* [node.js] - evented I/O for the backend
* [express] - fast node.js network app framework [@tjholowaychuk]
* [Mongoose] - a MongoDB object modeling tool designed to work in an asynchronous environment
* [Angular] - HTML enhanced for web apps!

   
## Installation

### Application Prerequisite :

- [Node.js] - v8+ to run.
- [Java] - v8+  
- [Apache Kafka] - Running on :9092
- [Apache ZooKeeper] - Running on :2181
- [MongoDB] - Running in **replication** on :27017

### Scripts 

***_Start MongoDB in Replication:_**

```shh
sudo /usr/bin/mongod --replSet rs0 --quiet --config /etc/mongod.conf &
```

***_Create MongoDB Database and required collections:_***
```sh
$ use notification-db
$ db.createCollection("messages")
$ db.createCollection("notifications")
$ db.createCollection("subscribers")
```

***_Create Kafka Topic:_***
```sh
/bin/kafka-topics.sh --create \
    --zookeeper <hostname>:<port> \
    --topic <topic-name> \
    --partitions <number-of-partitions> \
    --replication-factor <number-of-replicating-servers>

#example:
sh /bin/kafka-topics.sh --create \
    --zookeeper localhost:2181 \
    --replication-factor 1 \
    --partitions 1 \
    --topic notif-topic
	
#check the kafka topic list
sh /bin/kafka-topics.sh --list --zookeeper localhost:2181

#Test with Console Producer and Consumer
sh /bin/kafka-console-producer.sh --broker-list localhost:9092 --topic notif-topic
sh /bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic notif-topic --from-beginning
```


**_Build and start the Spring Boot Application:_**
_Before build configure **SpringBootApp\src\main\resources\application.yml** for kafka and mongoDB host address._
```sh
$ cd SpringBootApp
$ mvn clean install
$ cd target
$ nohup java -jar notification-0.0.1-SNAPSHOT.jar &
```

**_Start the NodeJS Express Application:_**

```sh
$ cd NodejsServer

$ npm run server 
#OR
$ node server.js &
$ disown
```

**_Start the Angular 5 Application:_**
_Before build configure **NodejsServer\config\database.config.js** for mongoDB host address and database name and replica set name_
```sh
$ cd Angular5App

$ npm install @angular/cli -g 
$ npm install bootstrap4-plus-jquery --save 
$ npm install jquery --save 
$ npm install ngx-bootstrap --save 
$ npm install socket.io-client --save 
$ npm install @types/socket.io-client
$ npm install -g http-server

$ ng build --prod
$ cp ngsw-worker.js dist/
$ cd dist 

$ npm run start_http 
#OR
$ http-server -c-1 --proxy http://localhost:3000 &
$ disown
```

### Test
Hit the below REST API from Postman - REST Client 
```sh
POST http://localhost:8080/messages
Content-Type: application/json

Request Body:
{
"title" : "Event Approval Pending!", 
"text" : "Event 123 pending for you approval", 
"url" : "https://www.someurl.com/event/123"
}
```


### Todos

 - Create Authentication/User based Notifications
 - Use Docker for environment setup

### License
MIT

### Author

Sushant Verma (sushant.v@hcl.com)


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Spring Boot]: <https://spring.io/projects/spring-boot>
   [Spring Kafka]: <https://spring.io/projects/spring-kafka>
   [Spring Data MongoDB]: <https://projects.spring.io/spring-data-mongodb/>
   [Apache Kafka]: <http://kafka.apache.org/>
   [Apache ZooKeeper]: <https://zookeeper.apache.org/>
   [MongoDB]: <https://www.mongodb.com/>
   [node.js]: <http://nodejs.org>
   [express]: <http://expressjs.com>
   [Mongoose]: <http://mongoosejs.com/>
   [Angular]: <https://angular.io/>
   [Java]: <http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html>
   [Maven]: <https://maven.apache.org/>
   
   
