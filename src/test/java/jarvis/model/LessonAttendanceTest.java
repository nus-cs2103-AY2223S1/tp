package jarvis.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.NoStudentsInLessonException;
import jarvis.model.exceptions.StudentNotFoundException;
import jarvis.model.util.SampleStudentUtil;

class LessonAttendanceTest {

    private final TreeSet<Student> students = new TreeSet<>(List.of(SampleStudentUtil.getSampleStudents()));
    private final LessonAttendance lessonAttendance = new LessonAttendance(students);

    @Test
    public void constructor_invalidStudents_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new LessonAttendance(null));

        assertThrows(NoStudentsInLessonException.class, () -> new LessonAttendance(new TreeSet<>()));
    }

    @Test
    public void markAsPresent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.markAsPresent(null)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonAttendance differentStudents = new LessonAttendance(students);
        assertThrows(StudentNotFoundException.class, () -> differentStudents.markAsPresent(studentToRemove));
    }

    @Test
    public void markAsAbsent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.markAsAbsent(null)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonAttendance differentStudents = new LessonAttendance(students);
        assertThrows(StudentNotFoundException.class, () -> differentStudents.markAsAbsent(studentToRemove));
    }

    @Test
    public void isPresent_invalidStudent() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.isPresent(null)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonAttendance differentStudents = new LessonAttendance(students);
        assertFalse(differentStudents.isPresent(studentToRemove)); // Returns false if student is not in the lesson
    }

    @Test
    public void markAsPresent_validStudent() {
        Student student = students.first();
        assertFalse(lessonAttendance.isPresent(student)); // Students are absent by default
        lessonAttendance.markAsPresent(student); // Mark absent student as present
        assertTrue(lessonAttendance.isPresent(student)); // Student is present
        lessonAttendance.markAsPresent(student); // Mark present student as present
        assertTrue(lessonAttendance.isPresent(student)); // Student is still present
    }

    @Test
    public void markAsAbsent_validStudent() {
        Student student = students.first();
        lessonAttendance.markAsPresent(student);
        assertTrue(lessonAttendance.isPresent(student)); // Student is present
        lessonAttendance.markAsAbsent(student); // Mark present student as absent
        assertFalse(lessonAttendance.isPresent(student)); // Student is now absent
        lessonAttendance.markAsAbsent(student); // Mark absent student as absent
        assertFalse(lessonAttendance.isPresent(student)); // Student is still absent
    }

    @Test
    public void getAllStudents() {
        assertEquals(students, lessonAttendance.getAllStudents());
    }

    @Test
    public void testEquals() {
        LessonAttendance sameValues = new LessonAttendance(students);

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonAttendance differentStudents = new LessonAttendance(students);

        LessonAttendance differentAttendance = new LessonAttendance(students);
        Student presentStudent = students.first();
        differentAttendance.markAsPresent(presentStudent);

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