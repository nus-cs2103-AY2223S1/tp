package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidFormat() {

        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank DateOfBirth
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // invalid DateOfBirth
        assertFalse(Date.isValidDate("1.1.2000"));
        assertFalse(Date.isValidDate("12/23/2000"));
        assertFalse(Date.isValidDate("12 Mar 1993"));
        assertFalse(Date.isValidDate("00/01/2000"));
        assertFalse(Date.isValidDate("00/00/2000"));
        assertFalse(Date.isValidDate("00/00/2000"));
        // Year 0000 corner case
        assertFalse(Date.isValidDate("01/01/0000"));
        // alphabetical characters
        assertFalse(Date.isValidDate("01/01/A000"));
        assertFalse(Date.isValidDate("01/0A/2000"));
        assertFalse(Date.isValidDate("0A/01/2000"));
        // invalid format
        assertFalse(Date.isValidDate("01/01/0"));
        assertFalse(Date.isValidDate("01/01/00"));
        assertFalse(Date.isValidDate("01/01/000"));
        assertFalse(Date.isValidDate("01/0/2000"));
        assertFalse(Date.isValidDate("0/01/2000"));

        // valid DateOfBirth
        assertTrue(Date.isValidDate("01/01/1000"));
        assertTrue(Date.isValidDate("23/12/2000"));
        assertTrue(Date.isValidDate("02/11/1900"));
        assertTrue(Date.isValidDate("11/11/1111"));
    }
}
