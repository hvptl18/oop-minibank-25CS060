package model;
import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import java.io.Serializable;
import java.util.Objects;
@Id
private final String accountNumber;

@MaxLength(50)
private String ownerName;

@Positive(message = "Balance must be greater than zero")
private long balance;
public abstract class Account
        implements Transactable, InterestBearing,
        Comparable<Account>, Serializable {

    private static final long serialVersionUID = 1L;

    private static long accountCounter = 1;

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    public Account(String ownerName, long openingBalance) {
        this.accountNumber =
                String.format("AC%04d", accountCounter++);

        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    @Override
    public void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be positive."
            );
        }

        balance = Math.addExact(balance, amount);
    }

    @Override
    public boolean withdraw(long amount)
            throws InsufficientFundsException,
            InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be positive."
            );
        }

        if (!canWithdraw(amount)) {
            long shortfall = amount - balance;

            throw new InsufficientFundsException(
                    "Insufficient funds.",
                    Math.max(0, shortfall)
            );
        }

        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount)
            throws BankException {

        try {
            withdraw(amount);
            to.deposit(amount);

        } catch (BankException e) {
            throw e;

        } finally {
            System.out.println("Transfer operation completed.");
        }
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void closeAccount() {
        active = false;
    }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(
                other.accountNumber
        );
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account other)) {
            return false;
        }

        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
