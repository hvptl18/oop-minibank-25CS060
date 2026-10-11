import model.Account;
import model.SavingsAccount;
import util.AnnotationValidator;

import java.lang.reflect.Field;

public class AnnotationDemo {

    public static void main(String[] args) {

        // Test 1: Valid account
        Account account1 =
                new SavingsAccount("Heer", 5000, 1000);

        System.out.println("Validating valid account:");

        String[] errors1 =
                AnnotationValidator.validate(account1);

        if (errors1.length == 0) {
            System.out.println("No validation errors.");
        } else {
            for (String error : errors1) {
                System.out.println(error);
            }
        }

        // Test 2: Account with a negative balance
        System.out.println("\nValidating account with negative balance:");

        try {
            Account account2 =
                    new SavingsAccount("Test", 1000, 0);

            // Set a negative balance for testing
            Field balanceField =
                    Account.class.getDeclaredField("balance");

            balanceField.setAccessible(true);
            balanceField.setLong(account2, -500);

            // Validate the account
            String[] errors2 =
                    AnnotationValidator.validate(account2);

            if (errors2.length == 0) {
                System.out.println("No validation errors.");
            } else {
                for (String error : errors2) {
                    System.out.println(error);
                }
            }

        } catch (ReflectiveOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
