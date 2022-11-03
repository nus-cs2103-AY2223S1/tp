package jarvis.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.InvalidParticipationException;
import jarvis.model.exceptions.StudentNotFoundException;
import jarvis.model.util.SampleStudentUtil;

class StudioIntegrationTest {
    private TreeSet<Student> students = new TreeSet<>(List.of(SampleStudentUtil.getSampleStudents()));
    private LocalDateTime dt1 = LocalDateTime.of(2022, 12, 12, 10, 0);
    private LocalDateTime dt2 = LocalDateTime.of(2022, 12, 12, 12, 0);
    private Studio studio1 = new Studio(null, new TimePeriod(dt1, dt2), students);

    private LocalDateTime dt3 = LocalDateTime.of(2022, 12, 12, 11, 0);
    private LocalDateTime dt4 = LocalDateTime.of(2022, 12, 12, 13, 0);
    private LocalDateTime dt5 = LocalDateTime.of(2022, 12, 13, 10, 0);
    private LocalDateTime dt6 = LocalDateTime.of(2022, 12, 13, 12, 0);



    @Test
    void startDateTime() {
        assertEquals(dt1, studio1.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(dt2, studio1.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        Studio studio2 = new Studio(null, new TimePeriod(dt3, dt4), students);
        Studio studio3 = new Studio(null, new TimePeriod(dt5, dt6), students);

        assertTrue(studio1.hasTimingConflict(studio2));
        assertTrue(studio2.hasTimingConflict(studio1));
        assertFalse(studio1.hasTimingConflict(studio3));
        assertFalse(studio3.hasTimingConflict(studio1));
    }

    @Test
    void hasStudent() {
        assertTrue(studio1.hasStudent(students.first()));

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        Studio studio2 = new Studio(null, new TimePeriod(dt1, dt2), students);
        assertFalse(studio2.hasStudent(studentToRemove));
    }

    @Test
    void markAsPresent() {
        Student student = students.first();
        studio1.markAsPresent(student);
        assertEquals("Present", studio1.isPresent(student));
    }

    @Test
    void markAsAbsent() {
        Student student = students.first();
        studio1.markAsPresent(student);
        assertEquals("Absent", studio1.isPresent(student));
    }

    @Test
    void isPresent_studentNotInLesson_returnAbsent() {
        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        Studio studio2 = new Studio(null, new TimePeriod(dt1, dt2), students);
        assertEquals("Absent", studio2.isPresent(studentToRemove));
    }

    @Test
    void markAsCompleted() {
        assertFalse(studio1.isCompleted()); // Not completed by default
        studio1.markAsCompleted();
        assertTrue(studio1.isCompleted());
        studio1.markAsCompleted();
        assertTrue(studio1.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        studio1.markAsCompleted();
        assertTrue(studio1.isCompleted());
        studio1.markAsNotCompleted();
        assertFalse(studio1.isCompleted());
        studio1.markAsNotCompleted();
        assertFalse(studio1.isCompleted());
    }

    @Test
    void hasDesc() {
        assertFalse(studio1.hasDesc());

        Studio studio2 = new Studio(new LessonDesc("Studio 2"), new TimePeriod(dt1, dt2), students);
        assertTrue(studio2.hasDesc());
    }

    @Test
    void setParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> studio1.setParticipationForStudent(null, 100)); // null student

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        Studio studio2 = new Studio(new LessonDesc("Studio 2"), new TimePeriod(dt1, dt2), students);
        assertThrows(StudentNotFoundException.class,
                () -> studio2.setParticipationForStudent(studentToRemove, 100));
    }

    @Test
    void setParticipationForStudent_invalidParticipation_exceptionThrown() {
        Student student = students.first();
        assertThrows(InvalidParticipationException.class,
                () -> studio1.setParticipationForStudent(student, -1));
        assertThrows(InvalidParticipationException.class,
                () -> studio1.setParticipationForStudent(student, 501));
        assertThrows(InvalidParticipationException.class,
                () -> studio1.setParticipationForStudent(student, 700));
    }

    @Test
    void testParticipation_validArguments() {
        Student student = students.first();
        assertEquals(0, studio1.getParticipationForStudent(student)); // default participation is 0
        studio1.setParticipationForStudent(student, 100);
        assertEquals(100, studio1.getParticipationForStudent(student));
        studio1.setParticipationForStudent(student, 200);
        assertEquals(200, studio1.getParticipationForStudent(student));

        // Boundary values
        studio1.setParticipationForStudent(student, 500);
        assertEquals(500, studio1.getParticipationForStudent(student));
        studio1.setParticipationForStudent(student, 0);
        assertEquals(0, studio1.getParticipationForStudent(student));
    }

    @Test
    void testEquals() {

    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.STUDIO, studio1.getLessonType());
    }
}