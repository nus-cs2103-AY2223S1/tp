package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        String invalidYear = "";
        assertThrows(IllegalArgumentException.class, () -> new Year(invalidYear));
    }

    @Test
    public void isValidYear() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid year
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // spaces only
        assertFalse(Year.isValidYear("12")); // more than 1 number
        assertFalse(Year.isValidYear("p")); // non-numeric
        assertFalse(Year.isValidYear("-1")); // negative number
        assertFalse(Year.isValidYear("0")); // zero
        assertFalse(Year.isValidYear("7")); // number out of range
        assertFalse(Year.isValidYear("1.5")); // floating point

        // valid year
        assertTrue(Year.isValidYear("1")); // within range
        assertTrue(Year.isValidYear("6")); // within range
    }
}
