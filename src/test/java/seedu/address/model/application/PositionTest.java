package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
    public void isValidPosition() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid position
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("^")); // only non-alphanumeric characters
        assertFalse(Position.isValidPosition("software_engineer")); // contains non-alphanumeric characters

        // valid position
        assertTrue(Position.isValidPosition("software engineer")); // alphabets only
        assertTrue(Position.isValidPosition("12345")); // numbers only
        assertTrue(Position.isValidPosition("business analyst p2")); // alphanumeric characters
        assertTrue(Position.isValidPosition("AI Engineer")); // with capital letters
        assertTrue(Position.isValidPosition("Web Developer and UI Designer")); // long Positions
    }
}
