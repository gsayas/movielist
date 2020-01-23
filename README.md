# Movie App

## Local Project setup

### System Requirements:
* [Maven](https://maven.apache.org/install.html)
* [JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


### How to Build

```bash
git clone https://github.com/gsayas/movielist.git
cd /path/to/movielist-folder
mvn clean install
```

### How to Run the Program

```bash
java -jar {/path/to/movielist-folder}/target/movielist.jar
```
The app can be seen in your browser at http://localhost:8000/movies


### How to run tests
```bash
mvn test
```
*******************************************************************************************************************************

## Live Demo

Since I wanted to test out Github actions I created a basic CI/CD pipeline that on every push builds the app, runs the tests and deploys it to heroku.

* [Here](https://github.com/gsayas/movielist/actions?query=branch%3Amaster+is%3Asuccess) you can see the last successful executions, and check the tests runs without having to install the app locally.

* [Here](https://gsayas-back.herokuapp.com/movies) is the app running in Heroku. Please be aware that I'm using the free tier so it could take it a few seconds on the first request, since it has to start the cloud servers... Be patient :) !

## Some comments about the Design

+ To overcome the fact that the people field is broken, I load all the people returned by the API and map them to the movies in which they appeared. This happens in the MoviesUseCases class.
+ To make sure that the Ghibli API is not called on every page load, the app relies on an in-memory DB that stores a local version of the Ghibli API responses.
+ I coded this requirement into an integration test that measures the average response time and ensures it is not above certain threshold. On a real app it could be defined on SLAs, or could be an optimization to an identified bottleneck. (See MoviesUseCasesIntegrationTest.testServiceLayerResponseTimesWithLocalDAO)
+ The in-memory database is automatically updated every 45 seconds by a background process to ensure the users always see an updated version (DatabaseUpdater class).

## TODO

[] Add more tests (to JSONMapper and DatabaseUpdater)  
[] Add coverage and other metrics to the pipeline quality gate
