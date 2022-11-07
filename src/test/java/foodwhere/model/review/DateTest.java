package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void isValidDate_generalTests_returnsExpectedValues() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("001-01-2020")); // wrong digits in day
        assertFalse(Date.isValidDate("01-001-2020")); // wrong digits in month
        assertFalse(Date.isValidDate("01-01-20000")); // wrong digits in year
        assertFalse(Date.isValidDate("01-01-202")); // wrong digits in year

        assertFalse(Date.isValidDate("01/01-2020")); // mix slash and dash
        assertFalse(Date.isValidDate("29-02/2000")); // mix slash and dash

        assertFalse(Date.isValidDate("32/01/2000")); // invalid day
        assertFalse(Date.isValidDate("29/02/2100")); // no leap year
        assertFalse(Date.isValidDate("00/01/2100")); // invalid day
        assertFalse(Date.isValidDate("01/00/2100")); // invalid month
        assertFalse(Date.isValidDate("01-01-0000")); // year 0

        // valid dates
        assertTrue(Date.isValidDate("01/01/2020")); // normal date
        assertTrue(Date.isValidDate("29/02/2000")); // leap year
        assertTrue(Date.isValidDate("01-01-2020")); // normal date
        assertTrue(Date.isValidDate("29-02-2000")); // leap year
        assertTrue(Date.isValidDate("01-01-0001")); // normal date

        assertTrue(Date.isValidDate("01-1-2020")); // normal date
        assertTrue(Date.isValidDate("1-01-2020")); // normal date
        assertTrue(Date.isValidDate("1-1-2020")); // normal date
    }

    @Test
    public void constructor_invalidDates_throwIllegalArgumentException() {
        // short edge cases
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("")); // empty string
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date(" ")); // spaces only

        // wrong number of digits
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("001-01-2020"));
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("01-001-2020"));

        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("01-01-20000"));
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("01-01-202"));

        // mix slash and dash
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("01/01-2020"));
        assertThrows(IllegalArgumentException.class, Date.MESSAGE_CONSTRAINTS, () -> new Date("29-02/2000"));

        // invalid day
        assertThrows(IllegalArgumentException.class, Date.VALID_DATE_CONSTRAINTS, () -> new Date("32/01/2000"));
        assertThrows(IllegalArgumentException.class, Date.VALID_DATE_CONSTRAINTS, () -> new Date("29/02/2100"));
        assertThrows(IllegalArgumentException.class, Date.VALID_DATE_CONSTRAINTS, () -> new Date("00/01/2100"));
        assertThrows(IllegalArgumentException.class, Date.VALID_DATE_CONSTRAINTS, () -> new Date("01/00/2100"));
        assertThrows(IllegalArgumentException.class, Date.VALID_DATE_CONSTRAINTS, () -> new Date("01-01-0000"));
    }

    @Test
    public void constructor_validDates_success() {
        // valid dates
        assertEquals("01/01/2020", new Date("01/01/2020").toString()); // normal date
        assertEquals("29/02/2000", new Date("29/02/2000").toString()); // leap year
        assertEquals("01/01/2020", new Date("01-01-2020").toString()); // normal date
        assertEquals("29/02/2000", new Date("29-02-2000").toString()); // leap year
        assertEquals("01/01/0001", new Date("01-01-0001").toString()); // normal date

        // 1 digit in day/month
        assertEquals("01/01/2020", new Date("01-1-2020").toString()); // normal date
        assertEquals("01/01/2020", new Date("1-01-2020").toString()); // normal date
        assertEquals("01/01/2020", new Date("1-1-2020").toString()); // normal date
    }

    @Test
    public void compareTo_generalTests_returnsExpectedValues() {
        // dates that make sense
        assertEquals(1, new Date("1/1/2000").compareTo(new Date("1/1/1999")));
        assertEquals(1, new Date("1/2/2000").compareTo(new Date("1/1/2000")));
        assertEquals(1, new Date("2/1/2000").compareTo(new Date("1/1/2000")));
        assertEquals(-1, new Date("1/1/1999").compareTo(new Date("1/1/2000")));
        assertEquals(-1, new Date("1/1/2000").compareTo(new Date("1/2/2000")));
        assertEquals(-1, new Date("1/1/2000").compareTo(new Date("2/1/2000")));
        assertEquals(0, new Date("29/2/2000").compareTo(new Date("29-02-2000")));

        // throws
        assertThrows(NullPointerException.class, () -> new Date("1/1/2000").compareTo(null));
    }
}
