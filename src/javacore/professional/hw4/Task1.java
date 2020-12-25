package javacore.professional.hw4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1 {

    static class TaskManager {
        private final int targetTaskCount;
        private int taskCount;
        private int currentTaskId;

        TaskManager(int targetTaskCount) {
            this.targetTaskCount = targetTaskCount;
        }

        public synchronized int nextTaskId() {
            return taskCount++;
        }

        public synchronized void checkTurn(int taskId) throws InterruptedException {
            while (currentTaskId != taskId) {
                wait();
            }
        }

        public synchronized void switchTask() {
            currentTaskId = ++currentTaskId % targetTaskCount;
            notifyAll();
        }

        public synchronized Task newTask(char character) {
            if (taskCount >= targetTaskCount) {
                throw new RuntimeException("Task limit is exceeded");
            }
            return new Task(this, character);
        }
    }

    static class Task implements Runnable {
        private static final int MAX_PRINT_COUNT = 5;
        private final int id;
        private final char character;
        private final TaskManager manager;

        Task(TaskManager manager, char character) {
            this.manager = manager;
            this.id = manager.nextTaskId();
            this.character = character;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < MAX_PRINT_COUNT; i++) {
                    manager.checkTurn(id);
                    System.out.print(character);
                    manager.switchTask();
                }
            } catch (InterruptedException ignore) {
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        char[] chars = {'A', 'B', 'C'};
        TaskManager manager = new TaskManager(chars.length);
        for (char c : chars) {
            exec.execute(manager.newTask(c));
        }
        exec.shutdown();
    }
}
