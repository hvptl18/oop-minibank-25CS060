package service;

import model.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class AccountStore {

    private final HashMap<String, Account> accounts =
            new HashMap<>();

    private final ArrayList<String> transactionHistory =
            new ArrayList<>();

    public void add(Account account) {

        accounts.put(
                account.getAccountNumber(),
                account
        );

        transactionHistory.add(
                "Account created: "
                        + account.getAccountNumber()
        );
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public ArrayList<Account> sortedByBalance() {

        ArrayList<Account> sorted =
                new ArrayList<>(accounts.values());

        sorted.sort(
                Comparator.comparingLong(Account::getBalance)
        );

        return sorted;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public int size() {
        return accounts.size();
    }
}
