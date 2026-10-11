import model.Account;
import model.SavingsAccount;
import util.AnnotationValidator;

public class AnnotationDemo {

    public static void main(String[] args) {

        Account account =
                new SavingsAccount(
                        "Heer",
                        5000,
                        1000
                );

        System.out.println("Validating account:");

        String[] errors =
                AnnotationValidator.validate(account);

        if (errors.length == 0) {

            System.out.println("No validation errors.");

        } else {

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
