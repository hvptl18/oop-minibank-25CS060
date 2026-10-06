package service;

public class DeadlockDemo {

    private static final Object ACCOUNT_A =
            new Object();

    private static final Object ACCOUNT_B =
            new Object();

    public static void main(String[] args) {

        Thread thread1 =
                new Thread(() -> {

                    synchronized (ACCOUNT_A) {

                        System.out.println(
                                "Thread 1 locked Account A"
                        );

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        synchronized (ACCOUNT_B) {

                            System.out.println(
                                    "Thread 1 locked Account B"
                            );
                        }
                    }
                });

        Thread thread2 =
                new Thread(() -> {

                    synchronized (ACCOUNT_B) {

                        System.out.println(
                                "Thread 2 locked Account B"
                        );

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        synchronized (ACCOUNT_A) {

                            System.out.println(
                                    "Thread 2 locked Account A"
                            );
                        }
                    }
                });

        thread1.start();
        thread2.start();

        System.out.println(
                "This example demonstrates opposite lock ordering."
        );
    }
}
