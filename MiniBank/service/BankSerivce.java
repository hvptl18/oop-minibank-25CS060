package service;

import model.Account;
import repository.Repository;

import java.util.ArrayList;

public class BankService {

    private final Repository<Account> repository;

    public BankService(Repository<Account> repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                    "Repository cannot be null"
            );
        }

        this.repository = repository;
    }

    public synchronized Account openAccount(
            String accountNumber,
            String ownerName,
            long initialBalance) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account number cannot be empty"
            );
        }

        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Owner name cannot be empty"
            );
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative"
            );
        }

        if (repository.findById(accountNumber) != null) {
            throw new IllegalArgumentException(
                    "Account already exists"
            );
        }

        Account account = new Account(
                accountNumber,
                ownerName,
                initialBalance
        );

        return repository.save(account);
    }

    public synchronized void deposit(String accountNumber, long amount) {
        Account account = getRequiredAccount(accountNumber);

        account.deposit(amount);
        repository.save(account);
    }

    public synchronized void withdraw(String accountNumber, long amount) {
        Account account = getRequiredAccount(accountNumber);

        account.withdraw(amount);
        repository.save(account);
    }

    public synchronized void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            long amount) {

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException(
                    "Source and destination must be different"
            );
        }

        Account source = getRequiredAccount(fromAccountNumber);
        Account destination = getRequiredAccount(toAccountNumber);

        // Withdraw first; if it fails, destination is unchanged.
        source.withdraw(amount);

        try {
            destination.deposit(amount);
        } catch (RuntimeException e) {
            // Restore the source if destination deposit fails.
            source.deposit(amount);
            throw e;
        }

        repository.save(source);
        repository.save(destination);
    }

    public synchronized String statement(String accountNumber) {
        Account account = getRequiredAccount(accountNumber);

        return account.toString();
    }

    public synchronized ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    private Account getRequiredAccount(String accountNumber) {
        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found: " + accountNumber
            );
        }

        return account;
    }
}
