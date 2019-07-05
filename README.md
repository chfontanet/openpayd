# openpayd
========
I have developed my test in Java 8.
Is a Spring Boot application with maven, with the following endPoints:

- **GET** [all clients]: Returns a list of clients with HttpStatus *200 (OK)*.
- **GET(clientId)** [client]: Returns the client specified with HttpStatus *200 (OK)*, or a HttpStatus *404 (Not found)* if the client does not exists in
Database.
- **POST** [client]: Returns the client created with HttpStatus *201 (Created)*. If any of the required values are not informed, you will get a *400 (Bad request)*.
- **GET(clientId)** [accounts]: Returns a list of accounts for the specified client with HttpStatus *200 (OK)* or a *404 (Not Found)* if the client does not exist in Database.
- **POST** [account]: Returns the account created with HttpStatus *201 (Created)* or a *404 (Not Found)* if the client does not exist in Database. If any of the required values are not informed, you will get a *400 (Bad request)*.
- **GET(accountId)** [transactions]: Returns a list of transactions for the specified account with HttpStatus *200 (OK)* or a *404 (Not Found)* if the account does not exist in Database.
- **PUT** [transaction]: Returns the transaction created with HttpStatus *200 (OK)*, a *400 (Bad request)* if the amount is zero or negative, or a *404 (Not Found)* if any of the two accounts does not exist in Database. If any of the required values are not informed, you will get a *400 (Bad request)*.

The application is organised in 4 folders, **exceptions**, with the specific exceptions throwing the correct HttpStatus, **persistence** with model and repository classes, **rest** with the controllers and DTO, and **service** with the logic of the application.

To start the application, first we need start the docker with the postgres image:
```docker run --name docker-postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres```
Once is up and running, we can start the application. The tables will be created in postgres when starting the application:
```mvn spring-boot:run```

If you want to connect to database to check the tables or the values, these are the parameters needed:
```database: postgres
port: 5432
username: postgres
password: password```

In the root of the project there is the file _Openpayd.postman_collection.json_ with test scenarios to consume the service.
Since the ID's can be different, in the URL's and some bodies in POST and PUT is necessary to modify the parameters with the ID created. The parameters have the format `{client_1}` or `{client_2_Account_1}` to identify the necessary value. All of them are longs.
I did not have time to create environment variables in Postman to avoid this.
