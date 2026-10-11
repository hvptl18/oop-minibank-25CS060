import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import service.StatePersister;
import service.TransactionLog;
import service.ReportGenerator;

import java.nio.file.Path;

public class PersistenceDemo {

    public static void main(String[] args) {

        Path dataFile = Path.of("data", "accounts.dat");
        Path logsFolder = Path.of("logs");
        Path logFile = logsFolder.resolve("transactions.txt");
        Path reportFile = Path.of("reports", "daily-report.txt");

        Account[] accounts = {
                new SavingsAccount("Heer", 5000, 1000),
                new CurrentAccount("Rahul", 8000, 2000),
                new FixedDepositAccount("Priya", 15000)
        };

        try {

            // Save accounts
            StatePersister.save(accounts, dataFile);

            System.out.println("Accounts saved successfully.");

            // Load accounts
            Account[] loadedAccounts =
                    StatePersister.load(dataFile);

            System.out.println("\nReloaded Accounts:");

            for (Account account : loadedAccounts) {
                System.out.println(account);
            }

            // Append transactions
            TransactionLog.append(logFile, "DEPOSIT 500");
            TransactionLog.append(logFile, "WITHDRAW 200");
            TransactionLog.append(logFile, "DEPOSIT 1000");
            TransactionLog.append(logFile, "WITHDRAW 300");

            // Generate report
            String report =
                    ReportGenerator.generate(
                            logsFolder,
                            reportFile
                    );

            System.out.println("\n" + report);

            System.out.println(
                    "Report saved to: " + reportFile
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}
