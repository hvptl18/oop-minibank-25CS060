import java.util.*;

public class UniqueVisitors {

    public static void main(String[] args) {

        // Login names with repeated users
        List<String> logins = Arrays.asList(
            "Amit", "Neha", "Raj", "Amit",
            "Priya", "Neha", "Karan", "Raj"
        );

        // HashSet stores unique users
        HashSet<String> uniqueUsers = new HashSet<>(logins);

        // LinkedHashSet preserves insertion order
        LinkedHashSet<String> orderedUsers =
                new LinkedHashSet<>(logins);

        // Print unique user count
        System.out.println(
            "Total unique users: " + uniqueUsers.size()
        );

        // Print first three distinct names
        System.out.println("First three unique visitors:");

        int count = 0;

        for (String user : orderedUsers) {

            if (count == 3) {
                break;
            }

            System.out.println(user);
            count++;
        }
    }
}
