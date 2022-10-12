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
        assertFalse(Reps.isValidReps("abc")); // not an integer
        assertFalse(Reps.isValidReps("-1")); // negative integer
        assertFalse(Reps.isValidReps("-1.5")); // negative decimal
        assertFalse(Reps.isValidReps("0")); // zero
        assertFalse(Reps.isValidReps("1.5")); // positive decimal
        assertFalse(Reps.isValidReps("1000")); // positive above 3 digits

        // valid Reps
        assertTrue(Reps.isValidReps("1")); // single digit
        assertTrue(Reps.isValidReps("15")); // double digit
        assertTrue(Reps.isValidReps("123")); // triple digit
    }
}
