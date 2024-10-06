package com.example.task_service.tasks;

public class SimpleTask implements Runnable {

    private final String taskName;

    public SimpleTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing task: " + taskName);
            Thread.sleep(2000);  // Simulating long-running task
            System.out.println("Completed task: " + taskName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

