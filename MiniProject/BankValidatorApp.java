import java.util.regex.*;
import java.util.*;

class Validator {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PAN_PATTERN   = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern IFSC_PATTERN  = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }
    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean isValidPan(String pan) {
        return PAN_PATTERN.matcher(pan).matches();
    }
    public static boolean isValidIfsc(String ifsc) {
        return IFSC_PATTERN.matcher(ifsc).matches();
    }
}

enum TransactionType {
    DEPOSIT, WITHDRAW, TRANSFER
}

record Command(TransactionType type, String accountNumber, long amount) {}

class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.split("\\s+");
        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        String accountNumber = parts[1];
        long amount = Long.parseLong(parts[2]);
        return new Command(type, accountNumber, amount);
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
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

    public void deposit(long amount) { balance += amount; }
    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Statement\n");
        sb.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        sb.append("Owner: ").append(account.getOwnerName()).append("\n");
        sb.append("Balance: ").append(account.getBalance()).append("\n");
        return sb.toString();
    }
}

public class BankValidatorApp {
    public static void main(String[] args) {
        System.out.println("Mobile valid? " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile invalid? " + Validator.isValidMobile("12345"));

        System.out.println("Email valid? " + Validator.isValidEmail("heer@example.com"));
        System.out.println("Email invalid? " + Validator.isValidEmail("heer@wrong"));

        System.out.println("PAN valid? " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("PAN invalid? " + Validator.isValidPan("AB12"));

        System.out.println("IFSC valid? " + Validator.isValidIfsc("HDFC0001234"));
        System.out.println("IFSC invalid? " + Validator.isValidIfsc("1234"));

        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Command Type: " + cmd.type());
        System.out.println("Command Account: " + cmd.accountNumber());
        System.out.println("Command Amount: " + cmd.amount());

        Account acc = new Account("Heer", 1000);
        acc.deposit(500);
        System.out.println(StatementFormatter.buildStatement(acc));
    }
}
