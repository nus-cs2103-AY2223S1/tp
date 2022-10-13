package seedu.address.model.person;

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
        assertFalse(Position.isValidPosition("Fullst@ck Engineer")); // non-alpha numeric
        assertFalse(Position.isValidPosition("Fullstack Engineer & Devel0per*")); // non-alpha numeric

        // valid position
        assertTrue(Position.isValidPosition("developer")); // 1 word
        assertTrue(Position.isValidPosition("software developer")); // 2 words
        assertTrue(Position.isValidPosition("software developer and tester")); // more than 2 words
        assertTrue(Position.isValidPosition("software devel0per")); // 2 words

    }

}
