package gim.model.exercise;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RepTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rep(null));
    }

    @Test
    public void constructor_invalidRep_throwsIllegalArgumentException() {
        String invalidRep = "";
        assertThrows(IllegalArgumentException.class, () -> new Rep(invalidRep));
    }

    @Test
    public void isValidRep() {
        // null Rep
        assertThrows(NullPointerException.class, () -> Rep.isValidRep(null));

        // invalid Reps
        assertFalse(Rep.isValidRep("")); // empty string
        assertFalse(Rep.isValidRep(" ")); // spaces only
        assertFalse(Sets.isValidSets("abc")); // not an integer
        assertFalse(Sets.isValidSets("-1")); // negative integer

        // valid Reps
        assertTrue(Rep.isValidRep("0")); // single digit
        assertTrue(Rep.isValidRep("15")); // double digit
        assertTrue(Rep.isValidRep("123")); // triple digit
    }
}
