# Movie search application
 
This app is built to search movies and actors from IMDB datasets.

**Prerequisites:** 
[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), [Maven](https://maven.apache.org/download.cgi), [Node.js](https://nodejs.org/) and [Docker](https://www.docker.com/get-started).

## Getting Started

Download below mentioned [IMDB datasets](https://datasets.imdbws.com/), then create a folder named "data" in side resources(`backend/src/main/resources`) and place the downloaded files into that folder.

1) name.basics.tsv.gz
2) title.basics.tsv.gz
3) title.principals.tsv.gz 

To start the PostgreSQL service, cd into the `backend` folder and run:

```bash
docker-compose up
```

# Two ways to start the application

1-) Serving front end code as static content in the backend application. 

Note that, for this approach, no need of Maven and Node dependencies, as these are installed locally by below command.  

Run below command in the project root directory to build and bundle application.
```bash
./mvnw clean package
```
Run below command to start the application.
```bash
java -jar -Dspring.profiles.active=dev backend/target/movie-search-backend-0.0.1-SNAPSHOT.jar
```

The application can be accessed through `http://localhost:8080`

2-) Run frontend and backend separately

To run the server, cd into the `backend` folder and run:
 
```bash
mvn spring-boot:run
```

To run the client, cd into the `frontend` folder and run:
 
```bash
npm install && npm start
```

The application can be accessed through `http://localhost:4200`

## Data access

Endpoints can be accessed through [swagger UI](http://localhost:8080/swagger-ui.html)
 
IMDB data import can be done by triggering following endpoint: `/api/upload/run-batch-jobs`
This step takes more than an hour to import all the data.

After this step, we can play with the below requirements from UI.
 
 Requirement #1:
 Typecasting: Given a query by the user, where he/she provides an actor/actress name, the system should determine if that person has become typecasted (at least half of their work is one genre).
 
 Requirement #2:
 Find the coincidence: Given a query by the user, where the input is two actors/actresses names, the application replies with a list of movies or TV shows that both people have shared.
 
 Requirement #3:
 Six degrees of Kevin Bacon: Given a query by the user, you must provide what’s the degree of separation between the person (e.g. actor or actress) the user has entered and Kevin Bacon.
 
 
## Todo

1. More validation needs to be added to the frontend as well as backend.
2. Front end code added only for user interaction and can be improved.
2. For requirement 3, we can use the graph database for better performance.
3. Unit tests need to be added for the batch, repository, domain, exception, and utility packages.
4. Duplicate names are not supported yet, the application will pick the first one randomly.