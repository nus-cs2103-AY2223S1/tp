package gim.model.exercise;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SetsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sets(null));
    }

    @Test
    public void constructor_invalidSets_throwsIllegalArgumentException() {
        String invalidSets = "";
        assertThrows(IllegalArgumentException.class, () -> new Sets(invalidSets));
    }

    @Test
    public void isValidSets() {
        // null sets
        assertThrows(NullPointerException.class, () -> Sets.isValidSets(null));

        // invalid sets
        assertFalse(Sets.isValidSets("")); // empty string
        assertFalse(Sets.isValidSets(" ")); // spaces only
        assertFalse(Sets.isValidSets("abc")); // not an integer
        assertFalse(Sets.isValidSets("-1")); // negative integer

        // valid sets
        assertTrue(Sets.isValidSets("3")); // single digit
        assertTrue(Sets.isValidSets("10")); // double digit
        assertTrue(Sets.isValidSets("100")); // triple digit
    }
}
