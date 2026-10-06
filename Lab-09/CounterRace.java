class Counter {
    private int count = 0;

    // Without synchronization
    public void incrementUnsafe() {
        count++;
    }

    // With synchronization
    public synchronized void incrementSafe() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class CounterRace {

    static final int THREADS = 10;
    static final int INCREMENTS = 100000;

    public static void main(String[] args) throws InterruptedException {

        // ---------------- UNSYNCHRONIZED ----------------
        Counter unsafeCounter = new Counter();

        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    unsafeCounter.incrementUnsafe();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Without synchronization:");
        System.out.println("Expected: " + (THREADS * INCREMENTS));
        System.out.println("Actual: " + unsafeCounter.getCount());


        // ---------------- SYNCHRONIZED ----------------
        Counter safeCounter = new Counter();

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    safeCounter.incrementSafe();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nWith synchronization:");
        System.out.println("Expected: " + (THREADS * INCREMENTS));
        System.out.println("Actual: " + safeCounter.getCount());
    }
}
