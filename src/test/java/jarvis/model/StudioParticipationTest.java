package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.HOON;
import static jarvis.testutil.TypicalStudents.getTypicalStudents;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.InvalidParticipationException;
import jarvis.model.exceptions.StudentNotFoundException;

class StudioParticipationTest {
    private final Set<Student> students = new TreeSet<>(getTypicalStudents());
    private final StudioParticipation studioParticipation = new StudioParticipation(students);

    @Test
    void setParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> studioParticipation.setParticipationForStudent(null, 100)); // null student

        assertThrows(StudentNotFoundException.class,
                        () -> studioParticipation.setParticipationForStudent(HOON, 100));
    }

    @Test
    void setParticipationForStudent_invalidParticipation_exceptionThrown() {
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(ALICE, -1));
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(ALICE, 501));
        assertThrows(InvalidParticipationException.class,
                () -> studioParticipation.setParticipationForStudent(ALICE, 700));
    }

    @Test
    void getParticipationForStudent_invalidStudent_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> studioParticipation.getParticipationForStudent(null)); // null student

        assertThrows(StudentNotFoundException.class,
                () -> studioParticipation.getParticipationForStudent(HOON));
    }

    @Test
    void testParticipation_validArguments() {
        assertEquals(0, studioParticipation.getParticipationForStudent(ALICE)); // default participation is 0
        studioParticipation.setParticipationForStudent(ALICE, 100);
        assertEquals(100, studioParticipation.getParticipationForStudent(ALICE));
        studioParticipation.setParticipationForStudent(ALICE, 200);
        assertEquals(200, studioParticipation.getParticipationForStudent(ALICE));

        // Boundary values
        studioParticipation.setParticipationForStudent(ALICE, 500);
        assertEquals(500, studioParticipation.getParticipationForStudent(ALICE));
        studioParticipation.setParticipationForStudent(ALICE, 0);
        assertEquals(0, studioParticipation.getParticipationForStudent(ALICE));
    }

    @Test
    void getAllStudents() {
        assertEquals(students, studioParticipation.getAllStudents());
    }

    @Test
    void testEquals() {
        StudioParticipation sameValues = new StudioParticipation(students);

        StudioParticipation differentParticipation = new StudioParticipation(students);
        differentParticipation.setParticipationForStudent(ALICE, 350);

        students.add(HOON);
        StudioParticipation differentStudents = new StudioParticipation(students);

        // same values -> returns true
        assertTrue(studioParticipation.equals(sameValues));

        // same object -> returns true
        assertTrue(studioParticipation.equals(studioParticipation));

        // null -> returns false
        assertFalse(studioParticipation.equals(null));

        // different type -> returns false
        assertFalse(studioParticipation.equals(5));

        // same students but different participation values -> returns false
        assertFalse(studioParticipation.equals(differentParticipation));

        // different students -> returns false
        assertFalse(studioParticipation.equals(differentStudents));
    }
}