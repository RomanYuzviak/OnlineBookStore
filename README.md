# Online Book Store
This is a Spring Boot project with REST API implementation of basic online store functions.
It has functionality for admin and customer, where first one can manage 
books, categories and orders, while customer can retrieve all necessary 
information about goods, add them to shopping cart and form an order. The project
made with actual technology stack and followed clean-code practices, so you
can fork it and develop your application.

___

### Technologies

* Java 17
* Spring Framework (Boot, Security, Data, WebMVC)
* Hibernate
* Liquibase
* MySql 8
* Lombok
* MapStruct
* Swagger

___ 

### Features

For users:

* Convenient authorisation by login and password for users and admins
* JSON format used for requests and responses
* Important information is stored after deleting 

For developers:

* Well-structured packages hierarchy with SOLID principals followed
* Secure authentication granted by using Spring Security and JWT Auth
* Simple and platform-independent DB management with Liquibase
    
___

### Getting started

Clone this repo to your local than install JDK 17, MySql 8 and Maven
if they haven`t already installed yet. 

#### Bulid

Navigate to root directory and run
from there `mvn clean package` for poject build.

#### Run

After the build is successful, you can
use the following command to run the Spring Boot application:
`mvn spring-boot:run`.

Your application will be available on port 8080. Congrats!

___

## Endpoints available

#### Authentication (/auth)

| Request type | Endpoint    | Role  | Description                  |
|--------------|-------------|-------|------------------------------|
| POST         | /register   | ALL   | Registration of a new user   |
| POST         | /login      | ALL   | Auth with login and password |

#### Books Management

| Request type | Endpoint     | Role  | Description             |
|--------------|--------------|-------|-------------------------|
| GET          | /books       | ALL   | Get all available books |
| GET          | /books/{id}  | ALL   | Get a book by its ID    |
| POST         | /books       | ADMIN | Create a new book       |
| PUT          | /books/{id}  | ADMIN | Update book properties  |
| DELETE       | /books/{id}  | ADMIN | Delete book by ID       |

#### Categories Management

| Request type | Endpoint              | Role  | Description                           |
|--------------|-----------------------|-------|---------------------------------------|
| GET          | /categories           | ALL   | Get all categories                    |
| GET          | /categories/{id}      | ALL   | Get a category by its ID              |
| GET          | /categories{id}/books | ALL   | Get all books from specified category |
| POST         | /categories           | ADMIN | Create a new category                 |
| PUT          | /categories/{id}      | ADMIN | Update category properties            |
| DELETE       | /categories/{id}      | ADMIN | Delete category by ID                 |

#### Shopping cart Management

| Request type | Endpoint               | Role | Description                              |
|--------------|------------------------|------|------------------------------------------|
| GET          | /cart                  | USER | Get user`s cart                          |
| POST         | /cart                  | USER | Add a new cart item                      |
| PUT          | /cart/cart-items/{id}  | ALL  | Update amount of books in specified item |
| DELETE       | /cart/cart-items/{id}  | ALL  | Delete cart item by ID                   |

#### Orders Management

| Request type | Endpoint               | Role  | Description                             |
|--------------|------------------------|-------|-----------------------------------------|
| GET          | /orders                | USER  | Get all user`s orders                   |
| GET          | /orders/{id}/items     | ALL   | Get all order items by ID               |
| GET          | /orders{id}/items/{id} | ALL   | Get specified item from specified order |
| POST         | /orders                | USER  | Create a new order                      |
| PATCH        | /orders/{id}           | ADMIN | Update an order by ID                   |
