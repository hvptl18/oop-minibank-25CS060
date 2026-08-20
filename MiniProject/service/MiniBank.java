package service;

import model.*;
import static java.lang.System.out; // example of static import

public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Alice", 5000, 1000),
            new CurrentAccount("Bob", 2000, 5000),
            new FixedDepositAccount("Charlie", 10000)
        };

        for (Account acc : accounts) {
            out.println(acc.getOwnerName() + " | Interest Rate: " + acc.interestRate() +
                        " | Yearly Interest: " + acc.yearlyInterest(acc.getBalance()));
        }

        // WithdrawRule with anonymous class
        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.getBalance() >= amount;
            }
        };

        // WithdrawRule with lambda
        WithdrawRule rule2 = (account, amount) -> amount < 2000;

        out.println("Anonymous rule allows Alice withdraw 1000? " + rule1.allow(accounts[0], 1000));
        out.println("Lambda rule allows Bob withdraw 3000? " + rule2.allow(accounts[1], 3000));
    }
}
