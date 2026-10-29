import model.Account;
import repository.Repository;
import service.BankService;

import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Repository<Account> repository = new Repository<>();
        BankService bankService = new BankService(repository);

        while (true) {
            System.out.println("\n===== MINIBANK =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Account Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid menu number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Account number: ");
                        String id = sc.nextLine();

                        System.out.print("Owner name: ");
                        String name = sc.nextLine();

                        System.out.print("Initial balance: ");
                        long initial = Long.parseLong(sc.nextLine());

                        bankService.openAccount(id, name, initial);
                        System.out.println("Account opened successfully.");
                        break;

                    case 2:
                        System.out.print("Account number: ");
                        id = sc.nextLine();

                        System.out.print("Deposit amount: ");
                        long deposit = Long.parseLong(sc.nextLine());

                        bankService.deposit(id, deposit);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.print("Account number: ");
                        id = sc.nextLine();

                        System.out.print("Withdrawal amount: ");
                        long withdrawal = Long.parseLong(sc.nextLine());

                        bankService.withdraw(id, withdrawal);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 4:
                        System.out.print("Source account: ");
                        String source = sc.nextLine();

                        System.out.print("Destination account: ");
                        String destination = sc.nextLine();

                        System.out.print("Transfer amount: ");
                        long amount = Long.parseLong(sc.nextLine());

                        bankService.transfer(source, destination, amount);
                        System.out.println("Transfer successful.");
                        break;

                    case 5:
                        System.out.print("Account number: ");
                        id = sc.nextLine();

                        System.out.println(bankService.statement(id));
                        break;

                    case 6:
                        for (Account account : bankService.listAccounts()) {
                            System.out.println(account);
                        }
                        break;

                    case 7:
                        System.out.println("Exiting MiniBank.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
}
