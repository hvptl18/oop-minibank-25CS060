import model.Student;
import service.StudentService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Student Count");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    service.addStudent(new Student(id, name));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    for (Student student : service.getAllStudents()) {
                        System.out.println(student);
                    }
                    break;

                case 3:
                    System.out.println(
                            "Student count: " + service.getStudentCount()
                    );
                    break;

                case 4:
                    System.out.println("Exiting application.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }
    }
}
