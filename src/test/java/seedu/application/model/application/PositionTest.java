package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

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

    @Test
    public void compareTo() {
        Position designerPosition = new Position("website designer");
        Position webDevPosition = new Position("Web Dev");
        Position web3DevPosition = new Position("Web3 Dev");
        Position uppercaseWPosition = new Position("W");
        Position lowercaseWPosition = new Position("w");

        assertEquals(0, designerPosition.compareTo(new Position("website designer"))); // Same position name
        assertTrue(designerPosition.compareTo(new Position("graphic designer")) > 0); // Different position names
        assertTrue(web3DevPosition.compareTo(designerPosition) < 0); // Numbers compare less than alphabets
        assertTrue(web3DevPosition.compareTo(webDevPosition) > 0); // Spaces compare less than other characters
        assertEquals(0, uppercaseWPosition.compareTo(lowercaseWPosition)); // Comparison is case-insensitive
        assertTrue(lowercaseWPosition.compareTo(designerPosition) < 0); // One name is prefix of the other
    }
}
