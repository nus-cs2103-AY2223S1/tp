package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.DT1;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.STUDIO_2;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.STUDIO_STUDENTS;
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
    private final Studio studio = new Studio(STUDIO_DESCRIPTION_1, new TimePeriod(DT1, DT3), STUDIO_STUDENTS);

    @Test
    void startDateTime() {
        assertEquals(DT1, studio.startDateTime());
    }

    @Test
    void endDateTime() {
        assertEquals(DT3, studio.endDateTime());
    }

    @Test
    void hasTimingConflict() {
        // Studios
        assertFalse(studio.hasTimingConflict(STUDIO_2));
        assertFalse(STUDIO_2.hasTimingConflict(studio));
        assertTrue(STUDIO_1.hasTimingConflict(studio));
        assertTrue(studio.hasTimingConflict(STUDIO_1));

        // Other lessons
        assertTrue(studio.hasTimingConflict(MC_1));
        assertFalse(studio.hasTimingConflict(CONSULT_1));
    }

    @Test
    void hasStudent() {
        assertTrue(studio.hasStudent(ALICE));
        assertFalse(studio.hasStudent(HOON));
    }

    @Test
    void isPresent() {
        assertEquals("Absent", STUDIO_1.isPresent(ALICE)); // Student is absent by default
        assertEquals("Absent", STUDIO_1.isPresent(HOON)); // Students not involved in studio are absent
    }

    @Test
    void markAsPresent() {
        studio.markAsPresent(ALICE);
        assertEquals("Present", studio.isPresent(ALICE));
    }

    @Test
    void markAsAbsent() {
        studio.markAsPresent(ALICE);
        studio.markAsAbsent(ALICE);
        assertEquals("Absent", studio.isPresent(ALICE));
    }

    @Test
    void markAsCompleted() {
        assertFalse(studio.isCompleted()); // Not completed by default
        studio.markAsCompleted();
        assertTrue(studio.isCompleted());
        studio.markAsCompleted();
        assertTrue(studio.isCompleted());
    }

    @Test
    void markAsNotCompleted() {
        studio.markAsCompleted();
        assertTrue(studio.isCompleted());
        studio.markAsNotCompleted();
        assertFalse(studio.isCompleted());
        studio.markAsNotCompleted();
        assertFalse(studio.isCompleted());
    }

    @Test
    void hasDesc() {
        Studio noDesc = new Studio(null, new TimePeriod(DT1, DT2), STUDIO_STUDENTS);
        assertTrue(studio.hasDesc());
        assertFalse(noDesc.hasDesc());
    }

    @Test
    void setParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class, () ->
                studio.setParticipationForStudent(null, 100)); // null student
        assertThrows(StudentNotFoundException.class, () ->
                studio.setParticipationForStudent(HOON, 100)); // Student not in lesson
    }

    @Test
    void setParticipationForStudent_invalidParticipation_exceptionThrown() {
        assertThrows(InvalidParticipationException.class, () ->
                studio.setParticipationForStudent(ALICE, -1));
        assertThrows(InvalidParticipationException.class, () ->
                studio.setParticipationForStudent(ALICE, 501));
        assertThrows(InvalidParticipationException.class, () ->
                studio.setParticipationForStudent(ALICE, 700));
    }

    @Test
    void testParticipation_validArguments() {
        assertEquals(0, studio.getParticipationForStudent(ALICE)); // default participation is 0
        studio.setParticipationForStudent(ALICE, 100);
        assertEquals(100, studio.getParticipationForStudent(ALICE));
        studio.setParticipationForStudent(ALICE, 200);
        assertEquals(200, studio.getParticipationForStudent(ALICE));

        // Boundary values
        studio.setParticipationForStudent(ALICE, 500);
        assertEquals(500, studio.getParticipationForStudent(ALICE));
        studio.setParticipationForStudent(ALICE, 0);
        assertEquals(0, studio.getParticipationForStudent(ALICE));
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
        assertTrue(studio.equals(sameValues));

        // same object -> returns true
        assertTrue(studio.equals(studio));

        // null -> returns false
        assertFalse(studio.equals(null));

        // different type -> returns false
        assertFalse(studio.equals(5));

        // same students but different attendance values -> returns false
        assertFalse(studio.equals(differentAttendance));

        // different students -> returns false
        assertFalse(studio.equals(differentStudents));

        // different participation -> returns false
        assertFalse(studio.equals(differentParticipation));

        // different time period -> returns false
        assertFalse(studio.equals(differentTime));

        // different description -> returns false
        assertFalse(studio.equals(differentDesc));

        // different notes -> returns true
        assertTrue(studio.equals(differentNotes));
    }

    @Test
    void getLessonType() {
        assertEquals(LessonType.STUDIO, STUDIO_1.getLessonType());
    }
}
