import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {

    public static void main(String[] args) {

        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 10 tasks
        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            executor.submit(() -> {

                System.out.println(
                        "Task " + taskId +
                        " is running on " +
                        Thread.currentThread().getName()
                );

                try {
                    // Sleep briefly
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Task " + taskId +
                        " completed by " +
                        Thread.currentThread().getName()
                );
            });
        }

        // No more tasks can be submitted
        executor.shutdown();

        try {

            // Wait for all tasks to finish
            if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("\nAll tasks completed.");
            } else {
                System.out.println("\nTasks did not finish in time.");
            }

        } catch (InterruptedException e) {

            System.out.println("Main thread was interrupted.");
            executor.shutdownNow();
        }
    }
}
