package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        String nullDate = null;
        LocalDate nullLocalDate = null;
        assertThrows(NullPointerException.class, () -> new Date(nullDate));
        assertThrows(NullPointerException.class, () -> new Date(nullLocalDate));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("91")); // less than 3 numbers
        assertFalse(Date.isValidDate("5 Jan 2022")); // non-numeric
        assertFalse(Date.isValidDate("9011p041")); // alphabets within digits
        assertFalse(Date.isValidDate("12-12-12")); // year should be 4 digits
        assertFalse(Date.isValidDate("12/12/1212")); // slash instead of dash
        assertFalse(Date.isValidDate("12-13-1212")); // invalid month
        assertFalse(Date.isValidDate("32-13-1212")); // invalid date
        assertFalse(Date.isValidDate("29-2-2022")); // month should be 2 digits
        assertFalse(Date.isValidDate("9-02-2022")); // date should be 2 digits

        // valid date
        assertTrue(Date.isValidDate("12-12-1212"));
        assertTrue(Date.isValidDate("09-02-2022"));
        assertTrue(Date.isValidDate("29-02-2022"));
    }
}
