import java.util.Objects;

class Customer implements Cloneable {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        return "CUST" + (++customerCounter);
    }

    public Customer(String name, String email, String mobile, Address address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
        this.address = address;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getCustomerId() { return customerId; }
    public Address getAddress() { return address; }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
    }
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
        if (amount > 0) balance += amount;
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

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", Owner=" + ownerName + ", Balance=" + balance + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Account acc1 = new Account("Alice", 1000);
        Account acc2 = new Account("Bob", 500);
        Account acc3 = new Account("Charlie", 300);

        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        System.out.println("acc1 equals acc2? " + acc1.equals(acc2));
        System.out.println("acc1 equals acc1? " + acc1.equals(acc1));

        if (acc1 instanceof Account) {
            System.out.println("acc1 is an Account object");
        }

        Customer.Address addr = new Customer.Address("123 Street", "Borsad", "388540");
        Customer cust1 = new Customer("Heer", "heer@example.com", "9876543210", addr);
        Customer cust2 = cust1.clone();

        System.out.println("Customer1 ID: " + cust1.getCustomerId() + ", City: " + cust1.getAddress().getCity());
        System.out.println("Customer2 (clone) ID: " + cust2.getCustomerId() + ", City: " + cust2.getAddress().getCity());
    }
}
