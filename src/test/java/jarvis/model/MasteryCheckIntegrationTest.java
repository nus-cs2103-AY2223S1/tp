package jarvis.model;

import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_STUDENTS;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.MC_2;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class MasteryCheckIntegrationTest {

    private final MasteryCheck masteryCheck = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
            MASTERY_CHECK_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT3, masteryCheck.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT4, masteryCheck.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Mastery Checks
        assertFalse(masteryCheck.hasTimingConflict(MC_1));
        assertFalse(MC_1.hasTimingConflict(masteryCheck));
        assertTrue(masteryCheck.hasTimingConflict(MC_2));
        assertTrue(MC_2.hasTimingConflict(masteryCheck));

        // Other lessons
        assertTrue(masteryCheck.hasTimingConflict(CONSULT_1));
        assertFalse(masteryCheck.hasTimingConflict(STUDIO_1));
    }

    @Test
    void hasStudent() {
        assertTrue(masteryCheck.hasStudent(ALICE));
        assertFalse(masteryCheck.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", masteryCheck.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", masteryCheck.isPresent(HOON)); // Students not involved in the mastery check are absent
    }

    @Test
    void markAsPresent() {
        masteryCheck.markAsPresent(ALICE);
        assertEquals("Present", masteryCheck.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        masteryCheck.markAsPresent(ALICE);
        masteryCheck.markAsAbsent(ALICE);
        assertEquals("Absent", masteryCheck.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(masteryCheck.isCompleted()); // Not completed by default
        masteryCheck.markAsCompleted();
        assertTrue(masteryCheck.isCompleted());
        masteryCheck.markAsCompleted();
        assertTrue(masteryCheck.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        masteryCheck.markAsCompleted();
        assertTrue(masteryCheck.isCompleted());
        masteryCheck.markAsNotCompleted();
        assertFalse(masteryCheck.isCompleted());
        masteryCheck.markAsNotCompleted();
        assertFalse(masteryCheck.isCompleted());
    }

    @Test
    void hasDesc() {
        MasteryCheck noDesc = new MasteryCheck(null, new TimePeriod(DT3, DT4), MASTERY_CHECK_STUDENTS);
        assertTrue(masteryCheck.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void testEquals() {
        MasteryCheck sameValues = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentStudents = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
                List.of(ALICE));

        MasteryCheck differentAttendance = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
                MASTERY_CHECK_STUDENTS);
        differentAttendance.markAsPresent(ALICE);

        MasteryCheck differentTime = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT2, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentDesc = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_2, new TimePeriod(DT3, DT4),
                MASTERY_CHECK_STUDENTS);
        MasteryCheck differentNotes = new MasteryCheck(MASTERY_CHECK_DESCRIPTION_1, new TimePeriod(DT3, DT4),
                MASTERY_CHECK_STUDENTS);
        differentNotes.addOverallNote("Note 1");

        // same values -> returns true
        assertTrue(masteryCheck.equals(sameValues));

        // same object -> returns true
        assertTrue(masteryCheck.equals(masteryCheck));

        // null -> returns false
        assertFalse(masteryCheck.equals(null));

        // different type -> returns false
        assertFalse(masteryCheck.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(masteryCheck.equals(differentAttendance));

        // different students -> returns false
        assertFalse(masteryCheck.equals(differentStudents));

        // different time period -> returns false
        assertFalse(masteryCheck.equals(differentTime));

        // different description -> returns false
        assertFalse(masteryCheck.equals(differentDesc));

        // different notes -> returns true
        assertTrue(masteryCheck.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.MASTERY_CHECK, masteryCheck.getLessonType());
    }
}
