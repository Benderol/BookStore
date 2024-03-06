# GraphQL project with Docker

This project is an implementation of a GraphQL server using Java. It provides a basic GraphQL API for querying and mutating data.
Also it has Dockerfile for building project's image and docker-compose for connecting this project with MySQL DB.

## Prerequisites
Before running the project, ensure you have the following installed:
- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL(if you dont use Docker)
- Docker(if you plan to use Docker)

## Database setup
- Username: root
- Password: root
- Schema name: test

## Running the Application
### Without Docker
  To run the application without Docker, execute the following Maven command:

```bash
    mvn spring-boot:run
```

The GraphQL server will start running at `http://localhost:8080/graphiql`.

### With Docker
To run the application using Docker, make sure that Docker is running.


#### 1. Open command line at this project folder and build image of this application by entering this command:
```bash
    docker build -t graphql/dockerdemo:0.0.1 .
```
Image with name `graphql/dockerdemo:0.0.1` would be created

#### 2. Run the following command to start the services defined in docker-compose.yml:
```bash
    docker-compose up
```
The GraphQL server will be accessible at `http://localhost:8080/graphiql`.

#### 3. To check dockers MySQL use followwing commands:
```bash
    docker exec -it <mysql container id> bash
    mysql -u admin -p
    Enter password: root
```
Now you are inside db and can make Queries.

## Example GraphQL Queries and Mutations
```graphql
mutation create{
  createAuthor(author:{
    username:"Author",
    password:"password",
    role:ADMIN
  })
}
```
```graphql
mutation addBook{
  addBookToAuthor(username:"Author", book:{
    title:"Witch",
    category:FANTASY
  })
}
```
```graphql
mutation deleteBook{
  deleteAuthorsBook(username:"Dan", title:"Witch")
}

mutation deleteAuthor{
  deleteAuthor(username:"Dan")
}
```
```graphql
query Query{
  getAllAuthors{
    username
  }

  getAllBooks{
    title
  }

  getAuthorsBooks(username:"Author"){
    title
  }

  getBooksByCategory(category:FANTASY){
    title
  }

  getAuthorByBookTitle(title:"Witch"){
    username
  }
}
```
