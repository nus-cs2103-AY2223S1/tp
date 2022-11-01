package longtimenosee.model.Event;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import longtimenosee.model.event.Date;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null values
        assertThrows(NullPointerException.class, () -> Date.isValidFormat(null));
        assertThrows(NullPointerException.class, () -> Date.isValidRange(null));
        assertThrows(NullPointerException.class, () -> Date.isValidFormat(null));
    }

    /**
     * Tests if months/days are in a valid year, given that it is in the right format
     */
    @Test
    public void isValidRange() {
        assertFalse(Date.isValidRange("2020-13-20")); // valid date, invalid month
        assertFalse(Date.isValidRange("2020-00-20")); // valid date, invalid month
        assertFalse(Date.isValidRange("2020-10-00")); // valid month, invalid date
        assertFalse(Date.isValidRange("2020-10-50")); // valid month, invalid date
        assertFalse(Date.isValidRange("2020-04-31")); // April has no 31st

        assertTrue(Date.isValidRange("2020-12-12"));
        assertTrue(Date.isValidRange("2020-04-30"));
        assertTrue(Date.isValidRange("2020-01-01"));
    }

    /**
     * Tests if years are given between 1900 and 2100
     */
    @Test
    public void isValidYear() {
        assertFalse(Date.isValidYear("1800-10-10"));
        assertFalse(Date.isValidYear("1899-12-12"));
        assertFalse(Date.isValidYear("2101-01-01"));
        assertFalse(Date.isValidYear("2200-12-31"));

        assertTrue(Date.isValidYear("1901-05-05"));
        assertTrue(Date.isValidYear("2022-11-11"));
        assertTrue(Date.isValidYear("2099-12-31"));
    }

    /**
     * Tests if input String is a valid date format ie. YYYY-MM-DD
     */
    @Test
    public void isValidFormat() {
        assertFalse(Date.isValidFormat("ABCD-GG-AA"));
        assertFalse(Date.isValidFormat("2020-111-10"));
        assertFalse(Date.isValidFormat("2020-11-005"));
        assertFalse(Date.isValidFormat("12-12-12"));
        assertFalse(Date.isValidFormat("121212-11-11"));
        assertFalse(Date.isValidFormat("09-09-2020"));
        assertFalse(Date.isValidFormat("03-2020-03"));
        assertFalse(Date.isValidFormat("2020/10/10"));
        assertFalse(Date.isValidFormat("2020 10 10"));

        assertTrue(Date.isValidFormat("2020-10-10"));
        assertTrue(Date.isValidFormat("2022-01-01"));

    }


}

