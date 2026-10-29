import model.Account;
import repository.Repository;

public class RepositoryDemo {

    public static void main(String[] args) {

        // Create a generic repository for Account objects
        Repository<Account> accountRepository = new Repository<>();

        // Create accounts
        Account a1 = new Account("AC0001", "Amit", 5000);
        Account a2 = new Account("AC0002", "Neha", 8000);
        Account a3 = new Account("AC0003", "Rahul", 12000);

        // Save multiple accounts
        accountRepository.saveAll(a1, a2, a3);

        System.out.println("All Accounts:");
        for (Account account : accountRepository.findAll()) {
            System.out.println(account);
        }

        // Find an account by ID
        System.out.println("\nFind Account AC0002:");
        Account found = accountRepository.findById("AC0002");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Account not found");
        }

        // Delete an account
        System.out.println("\nDelete AC0003:");
        System.out.println(
                "Deleted: " + accountRepository.delete("AC0003")
        );

        // Display accounts after deletion
        System.out.println("\nAccounts After Deletion:");
        for (Account account : accountRepository.findAll()) {
            System.out.println(account);
        }

        // Test the bounded generic average method
        Integer[] numbers = {10, 20, 30, 40};

        System.out.println("\nAverage: "
                + Repository.average(numbers));
    }
}
