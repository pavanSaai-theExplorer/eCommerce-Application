# eCommerce Microservices Application

This repository contains the implementation of an e-commerce platform built using a microservices architecture. The application leverages Spring Boot, Spring Cloud Gateway, Kafka, and Feign Clients to create a scalable and modular system for managing products, orders, inventory, and notifications.

## Features

- **Product Service**: Manages product creation and retrieval.
- **Order Service**: Handles order placement and communicates with the Inventory Service to verify product availability.
- **Inventory Service**: Provides real-time stock checks for products.
- **Notification Service**: Sends email notifications upon successful order placement.
- **API Gateway**: Routes API requests to the appropriate services and includes circuit breaker functionality.

## Architecture Overview

![Architecture Overview](https://github.com/user-attachments/assets/d18205f7-d5ad-46ca-8f2d-d9a9803218b5)

The application consists of the following services:

1. **Product Service**: Manages product information.
2. **Order Service**: Handles order placement and interacts with the Inventory Service.
3. **Inventory Service**: Tracks product stock and verifies availability.
4. **Notification Service**: Listens for order events and sends email confirmations.
5. **API Gateway**: Serves as a proxy, routing requests to the appropriate services and handling circuit breaker functionality.

The services communicate through:

- **Kafka**: For asynchronous messaging (e.g., order placement events).
- **Feign Clients**: For synchronous communication between services (e.g., checking inventory).

## Technologies Used

- **Spring Boot**: For building the microservices.
- **Spring Cloud Gateway**: For routing and API gateway functionality.
- **Kafka**: For event-driven messaging.
- **Feign Client**: For service-to-service communication.
- **Spring Data JPA**: For data persistence.
- **Spring Mail**: For sending email notifications.

## Setup & Installation

### Prerequisites

- Java 17 (or later)
- Maven
- Docker (optional for other setups)

### Clone the repository

```bash
git clone https://github.com/pavanSaai-theExplorer/eCommerce-Application.git
cd eComeCommerce-Application
```

### Running the services

1. **Start the API Gateway**:
   - The API Gateway is responsible for routing requests and applying circuit breaker functionality to the services.
   - It forwards requests to the appropriate services based on defined routes.
   - The API Gateway will be available at `http://localhost:9000`.

2. **Start the individual services**:
   Each service can be started independently, for example:
   - Product Service: `http://localhost:8080`
   - Order Service: `http://localhost:8081`
   - Inventory Service: `http://localhost:8082`
   - Notification Service: Runs in the background as a Kafka listener.

   You can run each service by starting its main application class (e.g., `ProductServiceApplication.java`, `OrderServiceApplication.java`, etc.).

Alternatively, if you have a `docker-compose.yml` file, you can use Docker Compose to start all services at once:

```bash
docker-compose up
```
