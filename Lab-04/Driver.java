public class Driver {
    public static void main(String[] args) {

        String[] passwords = {
            "abc",
            "Abcd1234!",
            "password",
            "Password1"
        };

        for (String pw : passwords) {
            System.out.println(pw + " → " + PasswordChecker.strength(pw));
        }
    }
}
