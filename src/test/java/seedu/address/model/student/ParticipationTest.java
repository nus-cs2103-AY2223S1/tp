package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ParticipationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidParticipation_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Participation(""));
        assertThrows(IllegalArgumentException.class, () -> new Participation(" "));
    }

    @Test
    public void isValidEmail() {
        // null participation
        assertThrows(NullPointerException.class, () -> Participation.isValidParticipation(null));

        // invalid participation
        assertFalse(Participation.isValidParticipation("")); // empty string
        assertFalse(Participation.isValidParticipation(" ")); // spaces only
        assertFalse(Participation.isValidParticipation("string")); // any string
        assertFalse(Participation.isValidParticipation("1.2")); // float
        assertFalse(Participation.isValidParticipation("5.0")); // float
        assertFalse(Participation.isValidParticipation("-2")); // negative integer

        // valid participation
        assertTrue(Participation.isValidParticipation("1000000000")); // big integer
        assertTrue(Participation.isValidParticipation("0")); // zero
    }
}
