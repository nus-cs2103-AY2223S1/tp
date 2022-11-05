package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        // invalid positions
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only

        // valid positions
        assertTrue(Position.isValidPosition("Chief Executive Officer"));
        assertTrue(Position.isValidPosition("General"));
        assertTrue(Position.isValidPosition("UI/UX Designer"));
        assertTrue(Position.isValidPosition("Team 3 Lead"));
        assertTrue(Position.isValidPosition("!@#$%^&*()0987654321"));
    }

}
