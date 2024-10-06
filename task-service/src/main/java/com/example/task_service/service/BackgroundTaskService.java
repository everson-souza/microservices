package com.example.task_service.service;

import com.example.task_service.tasks.SimpleTask;
import com.example.task_service.tasks.TaskExecutorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BackgroundTaskService {

    private final TaskExecutorService taskExecutorService;

    @Value("${task.execution.interval}")
    private long taskExecutionInterval;

    public BackgroundTaskService(TaskExecutorService taskExecutorService) {
        this.taskExecutorService = taskExecutorService;
    }

    // Run the task using the configured interval from properties
    @Scheduled(fixedRateString = "${task.execution.interval}")
    public void runScheduledTask() {
        SimpleTask task = new SimpleTask("Scheduled Task");
        taskExecutorService.executeTask(task);
        System.out.println("Scheduled task executed at: " + System.currentTimeMillis());
    }
}
