package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static jarvis.testutil.TypicalStudents.getTypicalStudents;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.NoStudentsInLessonException;
import jarvis.model.exceptions.StudentNotFoundException;

class LessonAttendanceTest {

    private final Set<Student> students = new TreeSet<>(getTypicalStudents());
    private final LessonAttendance lessonAttendance = new LessonAttendance(students);

    @Test
    public void constructor_invalidStudents_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new LessonAttendance(null));

        assertThrows(NoStudentsInLessonException.class, () -> new LessonAttendance(new ArrayList<>()));
    }

    @Test
    public void markAsPresent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.markAsPresent(null)); // null student

        assertThrows(StudentNotFoundException.class, () -> lessonAttendance.markAsPresent(HOON));
    }

    @Test
    public void markAsAbsent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.markAsAbsent(null)); // null student

        assertThrows(StudentNotFoundException.class, () -> lessonAttendance.markAsAbsent(HOON));
    }

    @Test
    public void isPresent_invalidStudent() {
        assertThrows(NullPointerException.class, () -> lessonAttendance.isPresent(null)); // null student

        assertFalse(lessonAttendance.isPresent(HOON)); // Returns false if student is not in the lesson
    }

    @Test
    public void markAsPresent_validStudent() {
        assertFalse(lessonAttendance.isPresent(ALICE)); // Students are absent by default
        lessonAttendance.markAsPresent(ALICE); // Mark absent student as present
        assertTrue(lessonAttendance.isPresent(ALICE)); // Student is present
        lessonAttendance.markAsPresent(ALICE); // Mark present student as present
        assertTrue(lessonAttendance.isPresent(ALICE)); // Student is still present
    }

    @Test
    public void markAsAbsent_validStudent() {
        lessonAttendance.markAsPresent(ALICE);
        assertTrue(lessonAttendance.isPresent(ALICE)); // Student is present
        lessonAttendance.markAsAbsent(ALICE); // Mark present student as absent
        assertFalse(lessonAttendance.isPresent(ALICE)); // Student is now absent
        lessonAttendance.markAsAbsent(ALICE); // Mark absent student as absent
        assertFalse(lessonAttendance.isPresent(ALICE)); // Student is still absent
    }

    @Test
    public void getAllStudents() {
        assertEquals(students, lessonAttendance.getAllStudents());
    }

    @Test
    public void testEquals() {
        LessonAttendance sameValues = new LessonAttendance(students);

        LessonAttendance differentAttendance = new LessonAttendance(students);
        differentAttendance.markAsPresent(ALICE);

        students.add(HOON);
        LessonAttendance differentStudents = new LessonAttendance(students);



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