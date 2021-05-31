[![CircleCI](https://circleci.com/gh/boskodjokic/TypeqastAssignment.svg?style=svg)](https://circleci.com/gh/boskodjokic/TypeqastAssignment)

# Read Me

Simple application that is processing metering data.
Project is build with Spring-Boot and Java 11.
H2 (in-memory) database is used to add data persistence.
Data is loaded at startup from bootstrap loader class.
Also, CircleCI config file is added so after every commit, tests are run and checked.

### Building the application

Navigate to the folder of the project and first run in terminal:

~~~
mvn clean install
~~~

After that run the app with command:

~~~
mvn spring-boot:run
~~~

Application is running on http://localhost:8080/
To connect to database go to http://localhost:8080/h2-console
Username and password are set to default, so you can just connect.
For Swagger, navigate to http://localhost:8080/swagger-ui/#


### Running tests

Tests can be run either from IDE or from terminal.
For running tests from terminal, navigate to the project folder and run:

~~~
mvn test
~~~

### Running app inside Docker container

Navigate to the folder of the project and first run in terminal:

~~~
mvn clean install
~~~

After that, run:

~~~
docker build -f Dockerfile -t docker-app .
~~~

After the image is created, run:

~~~
docker run -p 8080:8080 docker-app
~~~

Application is running on http://localhost:8080/

To connect to database go to http://localhost:8080/h2-console and change JDBC URL to: jdbc:h2:mem:testdb
For Swagger, navigate to http://localhost:8080/swagger-ui/#

### Notes
If you don't have maven installed on the machine, maven wrapper is integrated in the project, so it can be run with <b>mvnw</b> instead of <b>mvn</b>.
