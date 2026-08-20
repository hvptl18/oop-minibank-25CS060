import java.util.Scanner;

public class TollBooth {

    record Vehicle(String number, String type) {}

    public static void main(String[] args) {

        int totalToll = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter vehicle number: ");
            String number = sc.nextLine();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type: ");
            String type = sc.nextLine().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll += toll;

            switch (vehicle.type()) {
                case "bike" -> bike++;
                case "car" -> car++;
                case "truck" -> truck++;
            }
        }

        String mostFrequent;

        if (car >= bike && car >= truck) {
            mostFrequent = "car";
        } else if (bike >= truck) {
            mostFrequent = "bike";
        } else {
            mostFrequent = "truck";
        }

        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);

        sc.close();
    }
}
