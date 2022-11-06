package jarvis.model;

import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.CONSULT_2;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.CONSULT_STUDENTS;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.STUDIO_2;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ConsultIntegrationTest {

    private final Consult consult = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT3, consult.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT4, consult.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Consults
        assertFalse(consult.hasTimingConflict(CONSULT_2));
        assertFalse(CONSULT_2.hasTimingConflict(consult));
        assertTrue(consult.hasTimingConflict(CONSULT_1));
        assertTrue(CONSULT_1.hasTimingConflict(consult));

        // Other lessons
        assertTrue(consult.hasTimingConflict(STUDIO_2));
        assertFalse(consult.hasTimingConflict(MC_1));
    }

    @Test
    void hasStudent() {
        assertTrue(consult.hasStudent(ALICE));
        assertFalse(consult.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", consult.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", consult.isPresent(HOON)); // Students not involved in the consult are absent
    }

    @Test
    void markAsPresent() {
        consult.markAsPresent(ALICE);
        assertEquals("Present", consult.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        consult.markAsPresent(ALICE);
        consult.markAsAbsent(ALICE);
        assertEquals("Absent", consult.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(consult.isCompleted()); // Not completed by default
        consult.markAsCompleted();
        assertTrue(consult.isCompleted());
        consult.markAsCompleted();
        assertTrue(consult.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        consult.markAsCompleted();
        assertTrue(consult.isCompleted());
        consult.markAsNotCompleted();
        assertFalse(consult.isCompleted());
        consult.markAsNotCompleted();
        assertFalse(consult.isCompleted());
    }

    @Test
    void hasDesc() {
        Consult noDesc = new Consult(null, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        assertTrue(consult.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void testEquals() {
        Consult sameValues = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        Consult differentStudents = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), List.of(HOON));

        Consult differentAttendance = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        differentAttendance.markAsPresent(ALICE);

        Consult differentTime = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT2, DT4), CONSULT_STUDENTS);
        Consult differentDesc = new Consult(CONSULT_DESCRIPTION_2, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        Consult differentNotes = new Consult(CONSULT_DESCRIPTION_1, new TimePeriod(DT3, DT4), CONSULT_STUDENTS);
        differentNotes.addOverallNote("Note 1");

        // same values -> returns true
        assertTrue(consult.equals(sameValues));

        // same object -> returns true
        assertTrue(consult.equals(consult));

        // null -> returns false
        assertFalse(consult.equals(null));

        // different type -> returns false
        assertFalse(consult.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(consult.equals(differentAttendance));

        // different students -> returns false
        assertFalse(consult.equals(differentStudents));

        // different time period -> returns false
        assertFalse(consult.equals(differentTime));

        // different description -> returns false
        assertFalse(consult.equals(differentDesc));

        // different notes -> returns true
        assertTrue(consult.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.CONSULT, consult.getLessonType());
    }
}
