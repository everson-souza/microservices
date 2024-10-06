
  

# Microservices

  

This project demonstrates the creation of a basic microservice using Spring Boot. It follows the concepts described in [Let's Build a Microservice with Spring Boot](https://medium.com/ms-club-of-sliit/lets-build-a-microservice-with-spring-boot-faf39b968857).



## Table of Contents

- [Overview](#overview)

- [Project Structure](#project-structure)

- [How It Works](#how-it-works)

- [Components](#components)

- [Prerequisites](#prerequisites)

- [Setup and Run](#setup-and-run)

- [Testing](#testing)

- [License](#license)

  

## Overview

  
The project consists of three main components:

- **API Gateway**: Acts as a single entry point for client requests and forwards them to the appropriate service.

- **Eureka Service Discovery**: Manages service discovery, allowing microservices to register and discover each other dynamically at runtime. The main idea is to have a central registry of all the services and their current status. 

- **Microservices**: Small, autonomous unit designed to perform a specific function within a larger application. Each service is independently deployable, often responsible for a single business capability, and communicates with other services via APIs (usually RESTful). Services manage their own databases and are loosely coupled, which allows for better scalability, flexibility, and resilience.

  

## Project Structure

```

├── service-registry/

├── cloud-gateway/

├── school-service/

├── student-service/

├── task-service/

└── README.md

```

## How It Works

  

### 1. Eureka Server

- Starts and waits for services to register themselves.

- Each service instance (e.g., `school-service` and `student-service`) registers with Eureka so it can be discovered by others.

- The Eureka server provides a registry where microservices can query other services.

  

### 2. Microservices (School and Student)

-  **Microservices** are small, modular services that register with the Eureka server for discovery.

- Each microservice communicates with others using REST and Eureka discovery, without needing hardcoded URLs.

  

### 3. API Gateway

-  **Spring Cloud Gateway** is the entry point for all client requests.

- The Gateway fetches service information from Eureka and forwards client requests to the appropriate microservice.

- Configured to route based on service name, so no need for the client to know the exact URL or port of the services.

  

---
  

## How to Run

  
## Prerequisites

  

- Java 8 or higher

- Maven

- Spring Boot
  

---

  

## Setup and Run

  

1.  **Clone the Repository**

```bash
git clone https://github.com/everson-souza/microservice.git
```

2.  **Start Eureka Server**

3.  **Start Microservices**

4.  **Start API Gateway**

5.  **Access the Services**

- Eureka Dashboard: `http://localhost:8761`

- API Gateway: `http://localhost:8080/school/**` or `http://localhost:8080/student/**`

- Each individual service: `http://localhost:8082/school/**` or `http://localhost:8081/student/**`

---

  

## Testing

  

-  **Eureka Dashboard**: Access `http://localhost:8761` to see registered services.

-  **API Gateway**: Send a request using Postman to the API Gateway, which will route it to the corresponding microservice. Or directly to the microservice using the corresponding URL.


```bash

curl  http://localhost:8080/school

curl  http://localhost:8080/student

```  
---
  

## License

  

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.