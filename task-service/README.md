
  

# Scheduled Task with Threading

  

This is a simple microservice that runs a background task every 5 minutes using Spring Boot's scheduling capabilities and Java's `ExecutorService` for managing threads.

  

## Table of Contents

- [Overview](#overview)

- [Main files](#main-files)

- [How the Thread Works](#how-the-thread-works)

- [How to Run](#how-to-run)

- [Configuration](#configuration)

- [Shutdown](#shutdown)

- [Dependencies](#dependencies)

  

## Overview

  

This project demonstrates the use of:

- **Scheduled Tasks**: Using Spring's `@Scheduled` annotation to run a task periodically.

- **Threading with `ExecutorService`**: To manage and execute tasks in separate threads.

- **Task Scheduling**: The task is scheduled to run every 5 minutes, executing in the background without blocking the main thread.

  

## Main Files

- `MyMicroserviceApplication.java`: The entry point of the Spring Boot application.

- `service/BackgroundTaskService.java`: Contains the scheduled task logic that runs every 5 minutes.

- `tasks/TaskExecutorService.java`: Manages the thread pool using Java's `ExecutorService`.

- `tasks/SimpleTask.java`: Represents the task that runs in a separate thread.

  

## How the Thread Works

  

### Step-by-Step Explanation

  

1. **Scheduling the Task**:

- In `BackgroundTaskService.java`, the `@Scheduled` annotation is used to trigger the `runScheduledTask()` method every 5 minutes.


2. **Running the Task in a Separate Thread**:

- The scheduled task is executed using `TaskExecutorService`, which manages a pool of threads through `ExecutorService`.

- When the `runScheduledTask()` method is triggered, a `SimpleTask` instance is created and submitted to the thread pool for execution.

  

Example:

```java

SimpleTask task = new SimpleTask("Scheduled Task");

taskExecutorService.executeTask(task);

```

  

3. **Executor Service**:

- The `TaskExecutorService` creates a fixed thread pool with 5 threads, using `Executors.newFixedThreadPool(5)`.

- Tasks submitted to this service run in parallel without blocking the main application thread.

  

```java

private final ExecutorService executorService = Executors.newFixedThreadPool(5);

```

  

4. **Graceful Shutdown**:

- When the application context is stopped (e.g., during a shutdown), the `TaskExecutorService` ensures that the thread pool is properly shut down to avoid resource leakage. This is done using the `@PreDestroy` annotation in `TaskExecutorService`.

  

Example:

```java

@PreDestroy

public void shutdownExecutor() {

executorService.shutdown(); // Gracefully shuts down the thread pool

}

```

  

### Summary of Flow

1. Spring Boot application starts.

2. Every 5 minutes, the `@Scheduled` method in `BackgroundTaskService` triggers.

3. A new task (`SimpleTask`) is submitted to the thread pool managed by `TaskExecutorService`.

4. The task runs in the background without blocking the main thread.

5. When the application is shut down, the thread pool is gracefully shut down.

  

## How to Run

  

### Prerequisites

- Java 8 or higher

- Maven or Gradle (depending on your build system)

- Spring Boot

  

### Steps to Run:

1. Clone the repository:

```bash

git clone https://github.com/your-repo/my-microservice.git

cd my-microservice

```

  

2. Build the project:

```bash

mvn clean install

```

or

```bash

./gradlew build

```

  

3. Run the Spring Boot application:

```bash

mvn spring-boot:run

```

or

```bash

java -jar target/my-microservice-0.0.1-SNAPSHOT.jar

```

  

4. Check the logs. You should see the scheduled task being executed every 5 minutes.

  

## Configuration

  

You can control the interval at which the task is run using the `@Scheduled` annotation. Currently, it's configured to run every 5 minutes using the following configuration:

  

```java

@Scheduled(fixedRate = 300000) // 5 minutes in milliseconds

```

  

Alternatively, you can use a cron expression:

  

```java

@Scheduled(cron = "0 */5 * * * *") // Every 5 minutes

```

  

### Optional Configuration via `application.properties`

You can configure the execution interval in `application.properties`:

  

```properties

# application.properties

task.execution.interval=300000 # 5 minutes in milliseconds

```

  

Then update the `@Scheduled` annotation in `BackgroundTaskService` to read this value:

  

```java

@Scheduled(fixedRateString = "${task.execution.interval}")

```

  

## Shutdown

  

When the application is stopped, the thread pool is gracefully shut down to ensure no tasks are left hanging. This is handled using the `@PreDestroy` annotation in `TaskExecutorService`:

  

```java

@PreDestroy

public void shutdownExecutor() {

executorService.shutdown(); // Clean shutdown of thread pool

}

```

  

## Dependencies

  

This project uses the following dependencies (included in `pom.xml`):

- **Spring Boot Starter**: For core Spring functionality.

- **Spring Boot Starter Scheduling**: For scheduling tasks using `@Scheduled`.

- **Java Concurrency (ExecutorService)**: For managing the thread pool.

  

To install the dependencies, simply run:

  

```bash

mvn clean install

```

  

or

  

```bash

./gradlew build

```

  

## License

  

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.