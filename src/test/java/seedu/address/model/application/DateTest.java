package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void isValidDate() {
        // null Date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid Date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(Date.isValidDate("peter*")); // contains alphanumeric characters
        assertFalse(Date.isValidDate("12-13-2000")); // month does not exist
        assertFalse(Date.isValidDate("31-02-2022")); // date does not exist in particular month
        assertFalse(Date.isValidDate("32-12-2019")); // date does not exist
        assertFalse(Date.isValidDate("00-12-2000")); // date does not exist


        // valid Date
        assertTrue(Date.isValidDate("12-11-2022"));
        assertTrue(Date.isValidDate("13-12-2034"));
        assertTrue(Date.isValidDate("12-02-2065"));
        assertTrue(Date.isValidDate("12-09-1987"));
        assertTrue(Date.isValidDate("29-02-2004")); // leap year

    }
    @Test
    public void date() {
        assertEquals(new Date("12-11-2022").toString(), "Nov 12 2022");
        assertEquals(new Date("29-02-2020").toString(), "Feb 29 2020");
    }
}
