package com.example.task_service.tasks;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TaskExecutorService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);  // Thread pool with 5 threads

    public void executeTask(Runnable task) {
        executorService.submit(task);
    }

    @PreDestroy
    public void shutdownExecutor() {
        System.out.println("Shutting down ExecutorService");
        executorService.shutdown();  // Clean shutdown of thread pool
    }
}
