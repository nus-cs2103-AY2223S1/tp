package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));
    }

    @Test
    public void isValidName() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid positions
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only

        // valid positions
        assertTrue(Position.isValidPosition("Software Engineer")); // alphabet with capitalisation
        assertTrue(Position.isValidPosition("data analyst")); // alphabets only
        assertTrue(Position.isValidPosition("12345")); // numbers only
        assertTrue(Position.isValidPosition("2nd analyst")); // alphanumeric characters
        assertTrue(Position.isValidPosition("-")); // one character
        assertTrue(Position.isValidPosition("Machine Learning and AI Engineer")); // long position
    }
}
