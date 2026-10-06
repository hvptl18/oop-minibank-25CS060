package service;

import model.Account;
import model.SavingsAccount;

public class SafeTransferDemo {

    public static void safeTransfer(
            Account from,
            Account to,
            long amount) {

        Account first;
        Account second;

        if (from.getAccountNumber()
                .compareTo(to.getAccountNumber()) < 0) {

            first = from;
            second = to;

        } else {

            first = to;
            second = from;
        }

        synchronized (first) {

            synchronized (second) {

                try {

                    from.withdraw(amount);
                    to.deposit(amount);

                    System.out.println(
                            "Safe transfer completed: ₹"
                                    + amount
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Transfer failed: "
                                    + e.getMessage()
                    );
                }
            }
        }
    }

    public static void main(String[] args) {

        Account accountA =
                new SavingsAccount(
                        "A",
                        5000,
                        1000
                );

        Account accountB =
                new SavingsAccount(
                        "B",
                        5000,
                        1000
                );

        Thread thread1 =
                new Thread(() ->
                        safeTransfer(
                                accountA,
                                accountB,
                                500
                        )
                );

        Thread thread2 =
                new Thread(() ->
                        safeTransfer(
                                accountB,
                                accountA,
                                700
                        )
                );

        thread1.start();
        thread2.start();

        try {

            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Account A balance: "
                        + accountA.getBalance()
        );

        System.out.println(
                "Account B balance: "
                        + accountB.getBalance()
        );
    }
}
