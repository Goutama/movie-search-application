# Movie search application
 
This app is built to search movies and actors from IMDB datasets.

**Prerequisites:** 
[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), [Maven](https://maven.apache.org/download.cgi), [Node.js](https://nodejs.org/) and [Docker](https://www.docker.com/get-started).

## Getting Started

Download below [IMDB datasets](https://datasets.imdbws.com/) and place them into following location: `backend/src/main/resources/data`

1) name.basics.tsv.gz
2) title.basics.tsv.gz
3) title.principals.tsv.gz

To start the postgreSQL service , cd into the `backend` folder and run:

```bash
docker-compose up
```

#Two ways to start application

1-) Serving front end code as static content in the backend application. 

Note that, for this approach no need of Maven and Node dependencies, as these are installed locally by below command.  

Run below command in the project root directory to build and bundle application.
```bash
./mvnw clean package
```
Run below command to run application.
```bash
java -jar -Dspring.profiles.active=dev backend/target/movie-search-backend-0.0.1-SNAPSHOT.jar
```

Application can be accessed through `http://localhost:8080`

2-) Run frontend and backend separately

To run the server, cd into the `backend` folder and run:
 
```bash
mvn spring-boot:run
```

To run the client, cd into the `frontend` folder and run:
 
```bash
npm install && npm start
```

Application can be accessed through `http://localhost:4200`

## Data access

End points can be accessed through [swagger UI](http://localhost:8080/swagger-ui.html)
 
IMDB data import can be done by triggering following end point: `/api/upload/run-batch-jobs`
This step takes more than a hour to import all the data.

After this step, we can play with below requirements from UI.
 
 Requirement #1:
 Typecasting: Given a query by the user, where he/she provides an actor/actress name, the system should determine if that person has become typecasted (at least half of their work is one genre).
 
 Requirement #2:
 Find the coincidence: Given a query by the user, where the input is two actors/actresses names, the application replies with a list of movies or TV shows that both people have shared.
 
 Requirement #3:
 Six degrees of Kevin Bacon: Given a query by the user, you must provide what’s the degree of separation between the person (e.g. actor or actress) the user has entered and Kevin Bacon.
 
 
## Todo

1. More validation needs to be added to frontend as well as backend.
2. Front end code added only for display result and can be improved.
2. For requirement 3, we can use graph database for better performance.
3. Unit tests needs to be added for batch, repository, domain, exception and utility packages.
4. Batch processes can be improved to handle restart functionality.





