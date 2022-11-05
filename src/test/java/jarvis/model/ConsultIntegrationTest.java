package jarvis.model;

import static jarvis.testutil.TypicalLessons.CONSULT_2;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.CONSULT_STUDENTS;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.STUDIO_2;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ConsultIntegrationTest {

    private final Consult CONSULT_3 = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT2, CONSULT_3.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT4, CONSULT_3.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Consults
        assertFalse(CONSULT_3.hasTimingConflict(CONSULT_2));
        assertFalse(CONSULT_2.hasTimingConflict(CONSULT_3));
        assertTrue(CONSULT_3.hasTimingConflict(CONSULT_1));
        assertTrue(CONSULT_1.hasTimingConflict(CONSULT_3));

        // Other lessons
        assertTrue(CONSULT_3.hasTimingConflict(STUDIO_2));
        assertFalse(CONSULT_3.hasTimingConflict(MC_1));
    }

    @Test
    void hasStudent() {
        assertTrue(CONSULT_3.hasStudent(ALICE));
        assertFalse(CONSULT_3.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", CONSULT_3.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", CONSULT_3.isPresent(HOON)); // Students not involved in the consult are absent
    }

    @Test
    void markAsPresent() {
        CONSULT_3.markAsPresent(ALICE);
        assertEquals("Present", CONSULT_3.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        CONSULT_3.markAsPresent(ALICE);
        CONSULT_3.markAsAbsent(ALICE);
        assertEquals("Absent", CONSULT_3.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(CONSULT_3.isCompleted()); // Not completed by default
        CONSULT_3.markAsCompleted();
        assertTrue(CONSULT_3.isCompleted());
        CONSULT_3.markAsCompleted();
        assertTrue(CONSULT_3.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        CONSULT_3.markAsCompleted();
        assertTrue(CONSULT_3.isCompleted());
        CONSULT_3.markAsNotCompleted();
        assertFalse(CONSULT_3.isCompleted());
        CONSULT_3.markAsNotCompleted();
        assertFalse(CONSULT_3.isCompleted());
    }

    @Test
    void hasDesc() {
        Consult noDesc = new Consult(null, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        assertTrue(CONSULT_3.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void testEquals() {
        Consult sameValues = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        Consult differentStudents = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), List.of(HOON));

        Consult differentAttendance = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        differentAttendance.markAsPresent(ALICE);

        Consult differentTime = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        Consult differentDesc = new Consult(CONSULT_DESCRIPTION_2, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        Consult differentNotes = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        differentNotes.addOverallNote("Note 1");

        // same values -> returns true
        assertTrue(CONSULT_3.equals(sameValues));

        // same object -> returns true
        assertTrue(CONSULT_3.equals(CONSULT_3));

        // null -> returns false
        assertFalse(CONSULT_3.equals(null));

        // different type -> returns false
        assertFalse(CONSULT_3.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(CONSULT_3.equals(differentAttendance));

        // different students -> returns false
        assertFalse(CONSULT_3.equals(differentStudents));

        // different time period -> returns false
        assertFalse(CONSULT_3.equals(differentTime));

        // different description -> returns false
        assertFalse(CONSULT_3.equals(differentDesc));

        // different notes -> returns true
        assertTrue(CONSULT_3.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.CONSULT, CONSULT_3.getLessonType());
    }
}