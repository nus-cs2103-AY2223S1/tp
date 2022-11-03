package jarvis.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.InvalidParticipationException;
import jarvis.model.exceptions.StudentNotFoundException;
import jarvis.model.util.SampleStudentUtil;

class StudioParticipationTest {
    private final TreeSet<Student> students = new TreeSet<>(List.of(SampleStudentUtil.getSampleStudents()));
    private final StudioParticipation studioParticipation = new StudioParticipation(students);

    @Test
    void setParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> studioParticipation.setParticipationForStudent(null, 100)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        StudioParticipation differentStudents = new StudioParticipation(students);
        assertThrows(StudentNotFoundException.class,
                () -> differentStudents.setParticipationForStudent(studentToRemove, 100));
    }

    @Test
    void setParticipationForStudent_invalidParticipation_exceptionThrown() {
        Student student = students.first();
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(student, -1));
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(student, 501));
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(student, 700));
    }

    @Test
    void getParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> studioParticipation.getParticipationForStudent(null)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        StudioParticipation differentStudents = new StudioParticipation(students);
        assertThrows(StudentNotFoundException.class,
                () -> differentStudents.getParticipationForStudent(studentToRemove));
    }

    @Test
    void testParticipation_validArguments() {
        Student student = students.first();
        assertEquals(0, studioParticipation.getParticipationForStudent(student)); // default participation is 0
        studioParticipation.setParticipationForStudent(student, 100);
        assertEquals(100, studioParticipation.getParticipationForStudent(student));
        studioParticipation.setParticipationForStudent(student, 200);
        assertEquals(200, studioParticipation.getParticipationForStudent(student));

        // Boundary values
        studioParticipation.setParticipationForStudent(student, 500);
        assertEquals(500, studioParticipation.getParticipationForStudent(student));
        studioParticipation.setParticipationForStudent(student, 0);
        assertEquals(0, studioParticipation.getParticipationForStudent(student));
    }

    @Test
    void getAllStudents() {
        assertEquals(students, studioParticipation.getAllStudents());
    }

    @Test
    void testEquals() {
        StudioParticipation sameValues = new StudioParticipation(students);

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        StudioParticipation differentStudents = new StudioParticipation(students);

        StudioParticipation differentParticipation = new StudioParticipation(students);
        Student student = students.first();
        differentParticipation.setParticipationForStudent(student, 350);

        // same values -> returns true
        assertTrue(studioParticipation.equals(sameValues));

        // same object -> returns true
        assertTrue(studioParticipation.equals(studioParticipation));

        // null -> returns false
        assertFalse(studioParticipation.equals(null));

        // different type -> returns false
        assertFalse(studioParticipation.equals(5));

        // same students but different participation values -> returns false
        assertFalse(studioParticipation.equals(differentParticipation));

        // different students -> returns false
        assertFalse(studioParticipation.equals(differentStudents));
    }
}