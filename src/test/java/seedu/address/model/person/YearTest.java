package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        //EP > 4
        assertThrows(IllegalArgumentException.class, () -> new Year("5"));
        assertThrows(IllegalArgumentException.class, () -> new Year("6"));
        //EP < 1
        assertThrows(IllegalArgumentException.class, () -> new Year("0"));
        assertThrows(IllegalArgumentException.class, () -> new Year("-1"));
    }

    @Test
    public void isValidYear() {

        // invalid phone numbers
        //EP > 4
        assertFalse(Year.isValidYear("5")); // boundary value
        assertFalse(Year.isValidYear("6"));
        //EP < 1
        assertFalse(Year.isValidYear("0")); // boundary value
        assertFalse(Year.isValidYear("-1"));
        //EP Empty strings and alphabets
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // spaces only
        assertFalse(Year.isValidYear("S")); // Alphabet
        assertFalse(Year.isValidYear("Sa")); // Alphabets

        // valid phone numbers
        // 0 < EP < 5
        assertTrue(Year.isValidYear("1")); // boundary value
        assertTrue(Year.isValidYear("2"));
        assertTrue(Year.isValidYear("3"));
        assertTrue(Year.isValidYear("4")); // boundary value
    }
}
