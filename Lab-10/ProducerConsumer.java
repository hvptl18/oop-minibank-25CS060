import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {

    private Queue<Integer> buffer = new LinkedList<>();

    private final int capacity = 3;

    // Add item to buffer
    public synchronized void produce(int item)
            throws InterruptedException {

        // Wait if buffer is full
        while (buffer.size() == capacity) {
            wait();
        }

        buffer.add(item);

        System.out.println(
                "Produced: " + item +
                " | Buffer: " + buffer
        );

        // Notify consumer
        notify();
    }

    // Remove item from buffer
    public synchronized int consume()
            throws InterruptedException {

        // Wait if buffer is empty
        while (buffer.isEmpty()) {
            wait();
        }

        int item = buffer.remove();

        System.out.println(
                "Consumed: " + item +
                " | Buffer: " + buffer
        );

        // Notify producer
        notify();

        return item;
    }
}

public class ProducerConsumer {

    public static void main(String[] args)
            throws InterruptedException {

        SharedBuffer buffer = new SharedBuffer();

        // Producer thread
        Thread producer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    buffer.produce(i);

                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }

        });

        // Consumer thread
        Thread consumer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    buffer.consume();

                    Thread.sleep(150);
                }

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }

        });

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for both threads to finish
        producer.join();
        consumer.join();

        System.out.println("\nProduction and consumption completed.");
    }
}
