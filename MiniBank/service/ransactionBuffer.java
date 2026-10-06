package service;

public class TransactionBuffer {

    private final String[] buffer;

    private int count = 0;
    private int putIndex = 0;
    private int getIndex = 0;

    public TransactionBuffer(int size) {

        buffer = new String[size];
    }

    public synchronized void produce(
            String transaction)
            throws InterruptedException {

        while (count == buffer.length) {

            wait();
        }

        buffer[putIndex] = transaction;

        putIndex =
                (putIndex + 1) % buffer.length;

        count++;

        System.out.println(
                "Produced: " + transaction
        );

        notify();
    }

    public synchronized String consume()
            throws InterruptedException {

        while (count == 0) {

            wait();
        }

        String transaction =
                buffer[getIndex];

        getIndex =
                (getIndex + 1) % buffer.length;

        count--;

        System.out.println(
                "Consumed: " + transaction
        );

        notify();

        return transaction;
    }
}
