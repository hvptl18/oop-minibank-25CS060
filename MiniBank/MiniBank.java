import model.*;
import util.*;
import service.TransactionProcessor;
import exception.*;

import java.util.Scanner;

public class MiniBank {

    public record BankInfo(String name, String branch) {}

    public enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankInfo bankInfo = new BankInfo("MiniBank", "CHARUSAT Branch");

        System.out.println("=================================");
        System.out.println(bankInfo);
        System.out.println("=================================");

        Account[] accounts = {
                new SavingsAccount("Heer", 5000, 1000),
                new CurrentAccount("Rahul", 3000, 2000),
                new FixedDepositAccount("Priya", 10000)
        };

        // Testing account operations
        try {
            accounts[0].deposit(2000);

            accounts[0].withdraw(1000);

            System.out.println("\nAccount Details:");
            for (Account account : accounts) {
                System.out.println(account);
            }

        } catch (BankException e) {
            System.out.println("Bank Error: " + e.getMessage());
        }

        // Validator testing
        System.out.println("\n--- Validator Testing ---");

        System.out.println(
                "Valid Mobile: " +
                Validator.isValidMobile("9876543210")
        );

        System.out.println(
                "Invalid Mobile: " +
                Validator.isValidMobile("12345")
        );

        System.out.println(
                "Valid Email: " +
                Validator.isValidEmail("heer@gmail.com")
        );

        System.out.println(
                "Invalid Email: " +
                Validator.isValidEmail("heer@")
        );

        System.out.println(
                "Valid PAN: " +
                Validator.isValidPan("ABCDE1234F")
        );

        System.out.println(
                "Invalid PAN: " +
                Validator.isValidPan("ABC123")
        );

        System.out.println(
                "Valid IFSC: " +
                Validator.isValidIfsc("SBIN0001234")
        );

        System.out.println(
                "Invalid IFSC: " +
                Validator.isValidIfsc("12345")
        );

        // Command parser testing
        System.out.println("\n--- Command Parser ---");

        Command command =
                CommandParser.parse("DEPOSIT AC0001 500");

        System.out.println("Type: " + command.type());
        System.out.println("Account: " + command.accountNumber());
        System.out.println("Amount: " + command.amount());

        // Statement
        System.out.println("\n--- Account Statement ---");
        System.out.println(
                StatementFormatter.buildStatement(accounts[0])
        );

        // Polymorphism
        System.out.println("--- Interest Rates ---");

        for (Account account : accounts) {

            System.out.println(
                    account.getClass().getSimpleName()
                    + " : "
                    + account.interestRate()
                    + "%"
            );

            if (account instanceof SavingsAccount savings) {
                System.out.println(
                        "Savings minimum balance: "
                        + savings.getMinBalance()
                );
            }
        }

        // WithdrawRule using anonymous class
        WithdrawRule rule1 = new WithdrawRule() {

            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        System.out.println(
                "\nAnonymous class withdraw rule: "
                + rule1.allow(accounts[0], 500)
        );

        // WithdrawRule using lambda
        WithdrawRule rule2 =
                (account, amount) -> account.canWithdraw(amount);

        System.out.println(
                "Lambda withdraw rule: "
                + rule2.allow(accounts[0], 500)
        );

        // Customer
        Customer customer =
                new Customer(
                        "Heer Patel",
                        "heer@gmail.com",
                        "9876543210",
                        new Customer.Address(
                                "University Road",
                                "Anand",
                                "388001"
                        )
                );

        System.out.println("\n--- Customer ---");
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Mobile: " + customer.getMobile());
        System.out.println(
                "City: " + customer.getAddress().getCity()
        );

        // Clone
        try {
            Customer clonedCustomer = customer.clone();

            System.out.println(
                    "Cloned Customer ID: "
                    + clonedCustomer.getCustomerId()
            );

        } catch (CloneNotSupportedException e) {
            System.out.println("Clone failed.");
        }

        // Account equals and instanceof
        Account account1 =
                new SavingsAccount("Test", 1000, 500);

        Account account2 =
                new SavingsAccount("Test", 1000, 500);

        System.out.println("\n--- equals() and instanceof ---");

        System.out.println(
                "Account 1 equals Account 2: "
                + account1.equals(account2)
        );

        System.out.println(
                "account1 instanceof Account: "
                + (account1 instanceof Account)
        );

        // Transaction Processor
        System.out.println("\n--- Transaction Processor ---");

        TransactionProcessor processor =
                new TransactionProcessor();

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            processor.submit(() -> {

                try {

                    if (taskId % 2 == 0) {
                        accounts[0].deposit(100);
                        System.out.println(
                                "Task " + taskId +
                                " deposited ₹100"
                        );
                    } else {

                        accounts[0].withdraw(50);

                        System.out.println(
                                "Task " + taskId +
                                " withdrew ₹50"
                        );
                    }

                } catch (BankException e) {

                    System.out.println(
                            "Task " + taskId +
                            " failed: " +
                            e.getMessage()
                    );
                }
            });
        }

        processor.stop();

        // Menu
        runMenu(scanner);

        scanner.close();
    }

    private static void runMenu(Scanner scanner) {

        boolean running = true;

        while (running) {

            System.out.println("\n========== MiniBank ==========");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1 -> System.out.println(
                        "Open Account — to be implemented"
                );

                case 2 -> System.out.println(
                        "Deposit — to be implemented"
                );

                case 3 -> System.out.println(
                        "Withdraw — to be implemented"
                );

                case 4 -> System.out.println(
                        "Transfer — to be implemented"
                );

                case 5 -> {

                    running = false;

                    System.out.println(
                            "Thank you for using MiniBank. Goodbye!"
                    );
                }

                default -> System.out.println(
                        "Invalid choice."
                );
            }
        }
    }
}
