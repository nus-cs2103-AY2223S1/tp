package gim.model.exercise;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RepsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reps(null));
    }

    @Test
    public void constructor_invalidRep_throwsIllegalArgumentException() {
        String invalidRep = "";
        assertThrows(IllegalArgumentException.class, () -> new Reps(invalidRep));
    }

    @Test
    public void isValidRep() {
        // null Reps
        assertThrows(NullPointerException.class, () -> Reps.isValidReps(null));

        // invalid Reps
        assertFalse(Reps.isValidReps("")); // empty string
        assertFalse(Reps.isValidReps(" ")); // spaces only
        assertFalse(Sets.isValidSets("abc")); // not an integer
        assertFalse(Sets.isValidSets("-1")); // negative integer

        // valid Reps
        assertTrue(Reps.isValidReps("0")); // single digit
        assertTrue(Reps.isValidReps("15")); // double digit
        assertTrue(Reps.isValidReps("123")); // triple digit
    }
}
