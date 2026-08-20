abstract class Account {
    private final String accountNumber;
    private String ownerName;
    protected long balance;
    private static long counter = 0;

    private static String generateAccountNumber() {
        return String.format("AC%04d", ++counter);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(long amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", Owner=" + ownerName + ", Balance=" + balance + "]";
    }
}

class SavingsAccount extends Account {
    private long minBalance;

    public SavingsAccount(String ownerName, long openingBalance, long minBalance) {
        super(ownerName, openingBalance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return (balance - amount) >= minBalance;
    }
}

class CurrentAccount extends Account {
    private long overdraftLimit;

    public CurrentAccount(String ownerName, long openingBalance, long overdraftLimit) {
        super(ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return (balance - amount) >= -overdraftLimit;
    }
}

class FixedDepositAccount extends Account {
    public FixedDepositAccount(String ownerName, long openingBalance) {
        super(ownerName, openingBalance);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false; // locked deposit
    }
}

public class BankTypesApp {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Alice", 5000, 1000),
            new CurrentAccount("Bob", 2000, 5000),
            new FixedDepositAccount("Charlie", 10000)
        };

        for (Account acc : accounts) {
            System.out.println(acc + " | Interest Rate: " + acc.interestRate());
            if (acc instanceof FixedDepositAccount) {
                System.out.println("Special handling: Fixed deposits cannot be withdrawn early.");
            }
        }
    }
}
