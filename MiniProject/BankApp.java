import java.util.*;

class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        return "CUST" + (++customerCounter);
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getCustomerId() { return customerId; }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static long accountCounter = 0;

    private static String generateAccountNumber() {
        return String.format("AC%04d", ++accountCounter);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
}

public class BankApp {
    public static void main(String[] args) {
        Account[] accounts = {
            new Account("Alice", 1000),
            new Account("Bob", 500),
            new Account("Charlie")
        };

        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].deposit(300);
        accounts[2].withdraw(100);

        for (Account acc : accounts) {
            System.out.println(acc.getOwnerName() + " (" + acc.getAccountNumber() + 
                               ") Balance: " + acc.getBalance());
        }
    }
}
