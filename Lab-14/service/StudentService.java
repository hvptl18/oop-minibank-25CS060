package service;

import model.Student;
import java.util.ArrayList;

public class StudentService {

    private final ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void performAction(String action) {
        if (!"ADD".equalsIgnoreCase(action)
                && !"LIST".equalsIgnoreCase(action)) {
            throw new IllegalArgumentException("Invalid action");
        }
    }
}
