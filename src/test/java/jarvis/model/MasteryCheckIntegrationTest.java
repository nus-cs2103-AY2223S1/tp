package jarvis.model;

import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_STUDENTS;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.MC_2;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class MasteryCheckIntegrationTest {

    private final MasteryCheck MC_3 = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
            MASTERY_CHECK_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT2, MC_3.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT4, MC_3.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Mastery Checks
        assertFalse(MC_3.hasTimingConflict(MC_1));
        assertFalse(MC_1.hasTimingConflict(MC_3));
        assertTrue(MC_3.hasTimingConflict(MC_2));
        assertTrue(MC_2.hasTimingConflict(MC_3));

        // Other lessons
        assertTrue(MC_3.hasTimingConflict(CONSULT_1));
        assertFalse(MC_3.hasTimingConflict(STUDIO_1));
    }

    @Test
    void hasStudent() {
        assertTrue(MC_3.hasStudent(ALICE));
        assertFalse(MC_3.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", MC_3.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", MC_3.isPresent(HOON)); // Students not involved in mastery check are absent
    }

    @Test
    void markAsPresent() {
        MC_3.markAsPresent(ALICE);
        assertEquals("Present", MC_3.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        MC_3.markAsPresent(ALICE);
        MC_3.markAsAbsent(ALICE);
        assertEquals("Absent", MC_3.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(MC_3.isCompleted()); // Not completed by default
        MC_3.markAsCompleted();
        assertTrue(MC_3.isCompleted());
        MC_3.markAsCompleted();
        assertTrue(MC_3.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        MC_3.markAsCompleted();
        assertTrue(MC_3.isCompleted());
        MC_3.markAsNotCompleted();
        assertFalse(MC_3.isCompleted());
        MC_3.markAsNotCompleted();
        assertFalse(MC_3.isCompleted());
    }

    @Test
    void hasDesc() {
        MasteryCheck noDesc = new MasteryCheck(null, new TimePeriod(DT2, DT4), MASTERY_CHECK_STUDENTS);
        assertTrue(MC_3.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void testEquals() {
        MasteryCheck sameValues = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentStudents = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
                List.of(ALICE));

        MasteryCheck differentAttendance = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
                MASTERY_CHECK_STUDENTS);
        differentAttendance.markAsPresent(ALICE);

        MasteryCheck differentTime = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentDesc = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_2, new TimePeriod(DT2, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentNotes = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
                MASTERY_CHECK_STUDENTS);
        differentNotes.addOverallNote("Note 1");

        // same values -> returns true
        assertTrue(MC_3.equals(sameValues));

        // same object -> returns true
        assertTrue(MC_3.equals(MC_3));

        // null -> returns false
        assertFalse(MC_3.equals(null));

        // different type -> returns false
        assertFalse(MC_3.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(MC_3.equals(differentAttendance));

        // different students -> returns false
        assertFalse(MC_3.equals(differentStudents));

        // different time period -> returns false
        assertFalse(MC_3.equals(differentTime));

        // different description -> returns false
        assertFalse(MC_3.equals(differentDesc));

        // different notes -> returns true
        assertTrue(MC_3.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.MASTERY_CHECK, MC_3.getLessonType());
    }
}