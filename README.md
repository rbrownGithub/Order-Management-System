# Order Management System

This project is an Order Management System built with Spring Boot. It provides RESTful APIs to perform CRUD operations on orders and products, using an H2 in-memory database for testing purposes.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [Testing](#testing)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)
- [License](#license)

## Project Overview

This project is an implementation of an Order Management System that provides CRUD operations for managing orders and products. The application is built using Spring Boot and uses JPA for database interactions.

## Features

- CRUD operations for orders and products.
- Unit and integration tests using JUnit and Spring Boot Test.
- In-memory database (H2) for testing.

## Technologies Used

- **Java 17**
- **Spring Boot 2.7.13**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (for testing)
- **JUnit 5** (Jupiter) for testing
- **Maven** for build automation
- **Lombok** for reducing boilerplate code

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** (version 3.6 or higher)
- **Git** (to clone the repository)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/order-management-system.git
   cd order-management-system

Build the project using Maven
mvn clean install

To run the application, use the following Maven command:
mvn spring-boot:run

To run the unit and integration tests, execute the following Maven command:
mvn test

Ensure all tests pass with a "Process finished with exit code 0" message.

API Endpoints
Endpoint	HTTP Method	Description
/orders	GET	Retrieve all orders
/orders/{id}	GET	Retrieve a specific order by ID
/orders	POST	Create a new order
/orders/{id}	PUT	Update an existing order by ID
/orders/{id}	DELETE	Delete an order by ID
/products	GET	Retrieve all products
/products/{id}	GET	Retrieve a specific product by ID
/products	POST	Create a new product
/products/{id}	PUT	Update an existing product by ID
/products/{id}	DELETE	Delete a product by ID
Error Handling
The application uses custom exceptions to handle specific error cases:

OrderNotFoundException: Thrown when an order is not found by its ID.
InvalidOrderException: Thrown when an order contains invalid data (e.g., negative total amount).

License
This project is licensed under the MIT License. See the LICENSE file for more details.

