package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.DT1;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.STUDIO_STUDENTS;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.STUDIO_2;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.InvalidParticipationException;
import jarvis.model.exceptions.StudentNotFoundException;

class StudioIntegrationTest {
    private final Studio STUDIO_3 = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT1, STUDIO_3.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT3, STUDIO_3.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Studios
        assertFalse(STUDIO_3.hasTimingConflict(STUDIO_2));
        assertFalse(STUDIO_2.hasTimingConflict(STUDIO_3));
        assertTrue(STUDIO_1.hasTimingConflict(STUDIO_3));
        assertTrue(STUDIO_3.hasTimingConflict(STUDIO_1));

        // Other lessons
        assertTrue(STUDIO_3.hasTimingConflict(MC_1));
        assertFalse(STUDIO_3.hasTimingConflict(CONSULT_1));
    }

    @Test
    void hasStudent() {
        assertTrue(STUDIO_3.hasStudent(ALICE));
        assertFalse(STUDIO_3.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", STUDIO_1.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", STUDIO_1.isPresent(HOON)); // Students not involved in studio are absent
    }

    @Test
    void markAsPresent() {
        STUDIO_3.markAsPresent(ALICE);
        assertEquals("Present", STUDIO_3.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        STUDIO_3.markAsPresent(ALICE);
        STUDIO_3.markAsAbsent(ALICE);
        assertEquals("Absent", STUDIO_3.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(STUDIO_3.isCompleted()); // Not completed by default
        STUDIO_3.markAsCompleted();
        assertTrue(STUDIO_3.isCompleted());
        STUDIO_3.markAsCompleted();
        assertTrue(STUDIO_3.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        STUDIO_3.markAsCompleted();
        assertTrue(STUDIO_3.isCompleted());
        STUDIO_3.markAsNotCompleted();
        assertFalse(STUDIO_3.isCompleted());
        STUDIO_3.markAsNotCompleted();
        assertFalse(STUDIO_3.isCompleted());
    }

    @Test
    void hasDesc() {
        Studio noDesc = new Studio(null, new TimePeriod(DT1, DT2), STUDIO_STUDENTS);
        assertTrue(STUDIO_3.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void setParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> STUDIO_3.setParticipationForStudent(null, 100)); // null student
        assertThrows(StudentNotFoundException.class,
                () -> STUDIO_3.setParticipationForStudent(HOON, 100)); // Student not in lesson
    }

    @Test
    void setParticipationForStudent_invalidParticipation_exceptionThrown() {
        assertThrows(InvalidParticipationException.class,
                () -> STUDIO_3.setParticipationForStudent(ALICE, -1));
        assertThrows(InvalidParticipationException.class,
                () -> STUDIO_3.setParticipationForStudent(ALICE, 501));
        assertThrows(InvalidParticipationException.class,
                () -> STUDIO_3.setParticipationForStudent(ALICE, 700));
    }

    @Test
    void testParticipation_validArguments() {
        assertEquals(0, STUDIO_3.getParticipationForStudent(ALICE)); // default participation is 0
        STUDIO_3.setParticipationForStudent(ALICE, 100);
        assertEquals(100, STUDIO_3.getParticipationForStudent(ALICE));
        STUDIO_3.setParticipationForStudent(ALICE, 200);
        assertEquals(200, STUDIO_3.getParticipationForStudent(ALICE));

        // Boundary values
        STUDIO_3.setParticipationForStudent(ALICE, 500);
        assertEquals(500, STUDIO_3.getParticipationForStudent(ALICE));
        STUDIO_3.setParticipationForStudent(ALICE, 0);
        assertEquals(0, STUDIO_3.getParticipationForStudent(ALICE));
    }

    @Test
    void testEquals() {
        Studio sameValues = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);
        Studio differentStudents = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), List.of(ALICE));

        Studio differentAttendance = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);
        differentAttendance.markAsPresent(ALICE);

        Studio differentParticipation = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);
        differentParticipation.setParticipationForStudent(ALICE, 100);

        Studio differentTime = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT3, DT4), STUDIO_STUDENTS);
        Studio differentDesc = new Studio(STUDIO_DESCRIPTION_2, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);
        Studio differentNotes = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);
        differentNotes.addOverallNote("Note 1");

        // same values -> returns true
        assertTrue(STUDIO_3.equals(sameValues));

        // same object -> returns true
        assertTrue(STUDIO_3.equals(STUDIO_3));

        // null -> returns false
        assertFalse(STUDIO_3.equals(null));

        // different type -> returns false
        assertFalse(STUDIO_3.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(STUDIO_3.equals(differentAttendance));

        // different students -> returns false
        assertFalse(STUDIO_3.equals(differentStudents));

        // different participation -> returns false
        assertFalse(STUDIO_3.equals(differentParticipation));

        // different time period -> returns false
        assertFalse(STUDIO_3.equals(differentTime));

        // different description -> returns false
        assertFalse(STUDIO_3.equals(differentDesc));

        // different notes -> returns true
        assertTrue(STUDIO_3.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.STUDIO, STUDIO_1.getLessonType());
    }
}