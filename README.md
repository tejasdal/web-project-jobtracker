
# TrackMyJobs (Back-end application)
***
The purpose of TrackMyJobs is to help the users search their dream job and keep track of their job applications to help users manage their job application more effectively. Moreover, TrackMyJobs targets to help users manage and organize activities related to job application by using job boards, activities, notes, and notification which can keep them notified and easy to follow up with their job applications. TrackMyJobs also provide blog which will help users to find tips for job application. It is a feature that users can share their experience to others. 



* *Date Created*: 04 AUG 2020
* *Last Modification Date*: 07 AUG 2020

## Authors
***
##### Group 21

* Parth Bagaria     (pr270476@dal.ca)       B00839783
* Jan Chayopathum   (nw814986@dal.ca)       B00837398
* Anudish Jinturkar (an455254@dal.ca)       B00839915
* Roshan K. Patel   (roshan.patel23@dal.ca) B00853917
* Tejas Patel       (tejas.patel@dal.ca)    B00846296
* Zankrut B.Thakkar (zn834201@dal.ca)       B00856858


## Getting Started
***
This is a back-end application of TrackMyJobs that exposes REST APIs perform CRUD operation on the application database. More details on how to run this project in your local machine is describe in the following sections.

### Prerequisites
***
To run the back-end application of this project on your local machine for development or testing purposes, please make sure you have installed [Java OpenJDK 11](https://openjdk.java.net/install/). After installing it on your local machine, set the JAVA_HOME path in the system environment. Follow [this](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html) guide to install java and setting java home path. 
If you are not using any smart IDE, for example, IntelliJ IDEA, Eclipse, etc., then you have to manually install Gradle for dependency management. While these smart IDEs will do this extra work for you.
```
1. OpendJDK 11+
2. Gradle 5.2+
3. Spring Boot 2.3.2
4. IDE (Eclipse, IntelliJ) (Optional)
5. Internet connection (As our database is hosted on cloud)
```

### Installing
***
- Copy either SSH or HTTPS clone link of the git project to clone this git project on your local machine.
- Next, run the `git clone` command that clones files from a remote server to your local machine. (Make sure you have git installed.)
```
git clone https://github.com/parth1229/web-project-jobtracker.git
```
- After cloning the application from git, execute `./gradlew bootRun` command to run the spring boot application on your local machine. 
- Make sure that your machine has internet connection as gradle needs to download pre-define dependecies from the central repository, if they are not present in your local machine.
- You can also import this project in any IDE as a gradle project and click the run button in IDE to execute the application.
- The application will be hosted on 8080 port of your machine. Thus, you can access any REST end-points of the project using http://localhost:8080.
- Using Postman make an HTTP request on http://localhost:8080/blogs/ URL with `content-type` is equal to `application/json`. 

## Deployment
***
We have implemented continuous integration and continuous deployment (CI/CD) by hosting our code repositories on GitHub and deploying both applications on Heroku. After executing every `git push` command on master branch of the GitHub repositories, its respective Heroku application will build and deployed again with the latest changes.

The back-end Spring Boot application of TrackMyJob is hosted on Heroku app server and it can be accessed using the following URL:
https://app-jobtracker.herokuapp.com/

## Built With
***
The back-end Spring Boot application of TrackMyJob is built with the following technologies:
* [Heroku](https://www.heroku.com/) - Production Server to host the application on the Internet
* [Spring Boot](https://spring.io/) - The application framework for Java Platform
* [Gradle](https://gradle.org/) - Dependency management tool for Java Platform application
* [AWS RDS](https://aws.amazon.com/rds/) - Database as a Service by Amazon

## Folder Structure
***
In the back-end application our team decided to group the packages based on feature. This organize simillar business logic in a one group. However, the back-end application is also divided into three layers which are:
```
1. Controller layer
2. Service layer
3. Persistence layer
```
Each package represents a feature and it consists of these three layers. Controller layer of a feature contains schema of REST APIs expose to the clients for this feature. Service layer of a feature contains business logic to manipulate the data according to business requirements. Persistence layer contains functions that will communicate with the application database and perform CRUD operations.

## Access Information
***
Our website is hosted on Heroku app server and the following is the URL to access it:
```
https://track-my-jobs.herokuapp.com/
```
,and our back-end Spring Boot application is also hosted on Heroku app server and it can be accessed using the following URL:
```
https://app-jobtracker.herokuapp.com/
```

The following link provides access to the GitHub repository of our front-end Angular application:
```
https://github.com/Zankrut97/grp21-trackmyjobs.git
```
,and the following link provides access to the GitHub repository of our back-end Spring application:
```
https://github.com/parth1229/web-project-jobtracker.git
```
We have used the MySQL database of AWS RDS to host our application database on the cloud. 
```
web-group-21.cvrfhckf0rla.us-east-1.rds.amazonaws.com
```
is the link to access the database. The full credentials are provided on request. 

## Reference
***
[1] Tejas Patel, "CSCI 5709 ASSIGNMENT 4." Dalhousie University, [online document], 2020. [Accessed 06-Jul-2020]

