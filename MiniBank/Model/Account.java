package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import java.util.Objects;

public abstract class Account
        implements Transactable, InterestBearing {

    private static long accountCounter = 1;

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    public Account(String ownerName, long openingBalance) {

        this.accountNumber =
                String.format(
                        "AC%04d",
                        accountCounter++
                );

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
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;
    }

    @Override
    public boolean withdraw(long amount)
            throws InsufficientFundsException,
            InvalidAmountException {

        if (amount <= 0) {

            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (!canWithdraw(amount)) {

            long shortfall = amount - balance;

            if (shortfall < 0) {
                shortfall = 0;
            }

            throw new InsufficientFundsException(
                    "Insufficient funds.",
                    shortfall
            );
        }

        balance -= amount;

        return true;
    }

    public void transfer(Account to, long amount)
            throws BankException {

        try {

            this.withdraw(amount);
            to.deposit(amount);

        } catch (BankException e) {

            throw e;

        } finally {

            System.out.println(
                    "Transfer operation completed."
            );
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
    public String toString() {

        return "Account{" +
                "accountNumber='" +
                accountNumber + '\'' +
                ", ownerName='" +
                ownerName + '\'' +
                ", balance=" +
                balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;

        return accountNumber.equals(
                account.accountNumber
        );
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountNumber);
    }
}
