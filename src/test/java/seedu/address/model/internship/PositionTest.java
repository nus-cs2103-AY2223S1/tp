package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidPosition));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid name
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("^")); // only non-alphanumeric characters
        assertFalse(Position.isValidPosition("software engineer*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Position.isValidPosition("software engineer")); // alphabets only
        assertTrue(Position.isValidPosition("W3 Developer")); // alphanumeric characters
        assertTrue(Position.isValidPosition("Software Engineer")); // with capital letters
        assertTrue(Position.isValidPosition("Artificial Intelligence Developer")); // long positions
    }
}
