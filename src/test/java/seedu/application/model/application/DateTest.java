package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

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
        assertFalse(Date.isValidDate("2000-13-12")); // month does not exist
        assertFalse(Date.isValidDate("2022-02-31")); // date does not exist in particular month
        assertFalse(Date.isValidDate("2019-12-32")); // date does not exist
        assertFalse(Date.isValidDate("2000-12-00")); // date does not exist
        assertFalse(Date.isValidDate("1900-02-29")); // not leap year
        assertFalse(Date.isValidDate("1700-02-29")); // not leap year
        assertFalse(Date.isValidDate("34-12-13")); //two digit year
        assertFalse(Date.isValidDate("2022-1-13")); //one digit month
        assertFalse(Date.isValidDate("2022/08/24")); // slash


        // valid Date
        assertTrue(Date.isValidDate("2022-11-12"));
        assertTrue(Date.isValidDate("2034-12-13"));
        assertTrue(Date.isValidDate("2065-02-12"));
        assertTrue(Date.isValidDate("1987-09-12"));
        assertTrue(Date.isValidDate("2004-02-29")); // leap year
        assertTrue(Date.isValidDate("2000-02-29")); // leap year
        assertTrue(Date.isValidDate("2400-02-29")); // leap year


    }
    @Test
    public void date() {
        assertEquals(new Date("2022-11-12").toString(), "Nov 12 2022");
        assertEquals(new Date("2020-02-29").toString(), "Feb 29 2020");
    }
}
