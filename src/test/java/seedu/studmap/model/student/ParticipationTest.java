package seedu.studmap.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ParticipationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participation(null, null));
    }

    @Test
    public void constructor_invalidClassName_throwsIllegalArgumentException() {
        String invalidClassName = "!";
        assertThrows(IllegalArgumentException.class, () -> new Participation(invalidClassName,
                Participation.Status.PARTICIPATED));
    }

    @Test
    public void isValidParticipationName() {
        // null class name
        assertThrows(NullPointerException.class, () -> Participation.isValidParticipationName(null));

        // invalid class names
        assertFalse(Participation.isValidParticipationName("")); // empty string
        assertFalse(Participation.isValidParticipationName("asd!")); // contains forbidden symbols
        assertFalse(Participation.isValidParticipationName("aasd*")); // contains forbidden symbols
        assertFalse(Participation.isValidParticipationName("vafe?")); // contains forbidden symbols

        // valid class names
        assertTrue(Participation.isValidParticipationName("T01"));
        assertTrue(Participation.isValidParticipationName("T01_2-"));
        assertTrue(Participation.isValidParticipationName("L3-4"));
    }
}
