import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import service.AccountStore;

public class CollectionsDemo {

    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        Account a1 =
                new SavingsAccount("Heer", 5000, 1000);

        Account a2 =
                new CurrentAccount("Rahul", 8000, 2000);

        Account a3 =
                new FixedDepositAccount("Priya", 15000);

        Account a4 =
                new SavingsAccount("Amit", 3000, 500);

        store.add(a1);
        store.add(a2);
        store.add(a3);
        store.add(a4);

        store.addTransaction("Deposit: AC0001: 500");
        store.addTransaction("Withdrawal: AC0002: 200");

        System.out.println("All Accounts:");
        for (Account account : store.getAll()) {
            System.out.println(account);
        }

        System.out.println("\nAccounts Sorted by Balance:");

        for (Account account : store.sortedByBalance()) {
            System.out.println(account);
        }

        System.out.println("\nSearch Account:");

        Account found = store.get(a1.getAccountNumber());

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\nTransaction History:");

        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
