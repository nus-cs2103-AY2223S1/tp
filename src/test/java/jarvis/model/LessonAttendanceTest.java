package jarvis.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jarvis.model.util.SampleStudentUtil;

class LessonAttendanceTest {

    private final Set<Student> students = Set.of(SampleStudentUtil.getSampleStudents());
    private final Student student = SampleStudentUtil.getSampleStudents()[0];
    private final LessonAttendance lessonAttendance = new LessonAttendance(students);

    @Test
    void markAsPresent() {
        assertFalse(lessonAttendance.isPresent(student)); // Students are absent by default
        lessonAttendance.markAsPresent(student); // Mark absent student as present
        assertTrue(lessonAttendance.isPresent(student)); // Student is present
        lessonAttendance.markAsPresent(student); // Mark present student as present
        assertTrue(lessonAttendance.isPresent(student)); // Student is still present
    }

    @Test
    void markAsAbsent() {
        lessonAttendance.markAsPresent(student);
        assertTrue(lessonAttendance.isPresent(student)); // Student is present
        lessonAttendance.markAsAbsent(student); // Mark present student as absent
        assertFalse(lessonAttendance.isPresent(student)); // Student is now absent
        lessonAttendance.markAsAbsent(student); // Mark absent student as absent
        assertFalse(lessonAttendance.isPresent(student)); // Student is still absent
    }

    @Test
    void getAllStudents() {
        assertTrue(lessonAttendance.getAllStudents().equals(students));
    }

    @Test
    void testEquals() {
        LessonAttendance sameValues = new LessonAttendance(students);

        Set<Student> studentsCopy = new HashSet<>(students);
        studentsCopy.remove(student);
        LessonAttendance differentStudents = new LessonAttendance(studentsCopy);

        LessonAttendance differentAttendance = new LessonAttendance(students);
        differentAttendance.markAsPresent(student);


        // same values -> returns true
        assertTrue(lessonAttendance.equals(sameValues));

        // same object -> returns true
        assertTrue(lessonAttendance.equals(lessonAttendance));

        // null -> returns false
        assertFalse(lessonAttendance.equals(null));

        // different type -> returns false
        assertFalse(lessonAttendance.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(lessonAttendance.equals(differentAttendance));

        // different students -> returns false
        assertFalse(lessonAttendance.equals(differentStudents));
    }
}