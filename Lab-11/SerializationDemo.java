import java.io.*;

// Serializable class
class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    int rollNo;
    String name;
    double marks;

    // This field will not be serialized
    transient String password;

    Student(int rollNo, String name, double marks, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.password = password;
    }

    void display() {
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Password: " + password);
        System.out.println("--------------------");
    }
}

public class SerializationDemo {

    public static void main(String[] args) {

        String filename = "students.dat";

        Student[] students = {
            new Student(1, "Amit", 85.5, "amit123"),
            new Student(2, "Neha", 90.0, "neha456"),
            new Student(3, "Raj", 78.5, "raj789")
        };

        // Save objects to file
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(filename))) {

            out.writeObject(students);

            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving objects: " + e.getMessage());
            return;
        }

        // Load objects from file
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(filename))) {

            Student[] loadedStudents = (Student[]) in.readObject();

            System.out.println("\nLoaded Student Details:");

            for (Student s : loadedStudents) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading objects: " + e.getMessage());
        }
    }
}
