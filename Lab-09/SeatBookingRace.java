class SeatBooking {

    private int seatsLeft = 5;
    private int successfulBookings = 0;

    // Without synchronization
    public void bookUnsafe(String name) {

        if (seatsLeft > 0) {

            // Small delay to make the race condition easier to observe
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            seatsLeft--;
            successfulBookings++;

            System.out.println(name + " booked a seat.");
        }
    }

    // With synchronization
    public synchronized void bookSafe(String name) {

        if (seatsLeft > 0) {

            seatsLeft--;
            successfulBookings++;

            System.out.println(name + " booked a seat.");
        }
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}

public class SeatBookingRace {

    public static void main(String[] args) throws InterruptedException {

        // ---------------- UNSYNCHRONIZED ----------------

        SeatBooking unsafeBooking = new SeatBooking();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            final int id = i + 1;

            threads[i] = new Thread(() -> {
                unsafeBooking.bookUnsafe("User-" + id);
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nWithout synchronization:");
        System.out.println(
                "Successful bookings: "
                        + unsafeBooking.getSuccessfulBookings()
        );
        System.out.println(
                "Seats left: "
                        + unsafeBooking.getSeatsLeft()
        );


        // ---------------- SYNCHRONIZED ----------------

        SeatBooking safeBooking = new SeatBooking();

        for (int i = 0; i < 10; i++) {

            final int id = i + 1;

            threads[i] = new Thread(() -> {
                safeBooking.bookSafe("User-" + id);
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nWith synchronization:");
        System.out.println(
                "Successful bookings: "
                        + safeBooking.getSuccessfulBookings()
        );
        System.out.println(
                "Seats left: "
                        + safeBooking.getSeatsLeft()
        );
    }
}
