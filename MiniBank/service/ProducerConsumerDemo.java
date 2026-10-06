package service;

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        TransactionBuffer buffer =
                new TransactionBuffer(3);

        Thread producer =
                new Thread(() -> {

                    try {

                        for (int i = 1; i <= 10; i++) {

                            buffer.produce(
                                    "TXN" + i
                            );
                        }

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                    }
                });

        Thread consumer =
                new Thread(() -> {

                    try {

                        for (int i = 1; i <= 10; i++) {

                            buffer.consume();
                        }

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                    }
                });

        producer.start();
        consumer.start();

        try {

            producer.join();
            consumer.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Producer-consumer completed."
        );
    }
}
