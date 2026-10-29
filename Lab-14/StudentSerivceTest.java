import model.Student;
import service.StudentService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void addingStudentIncreasesCount() {
        StudentService service = new StudentService();

        service.addStudent(new Student("S101", "Amit"));

        assertEquals(1, service.getStudentCount());
    }

    @Test
    void addingTwoStudentsIncreasesCountToTwo() {
        StudentService service = new StudentService();

        service.addStudent(new Student("S101", "Amit"));
        service.addStudent(new Student("S102", "Neha"));

        assertEquals(2, service.getStudentCount());
    }

    @Test
    void invalidActionThrowsException() {
        StudentService service = new StudentService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.performAction("INVALID")
        );
    }
}
