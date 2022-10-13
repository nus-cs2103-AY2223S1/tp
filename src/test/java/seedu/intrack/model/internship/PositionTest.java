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
        // null address
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid addresses
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only

        // valid addresses
        assertTrue(Position.isValidPosition("Software Engineer"));
        assertTrue(Position.isValidPosition("-")); // one character
        assertTrue(Position.isValidPosition("Machine Learning & AI Engineer")); // long position
    }
}
