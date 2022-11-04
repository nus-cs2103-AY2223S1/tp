package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidName));

        String invalidName2 = "00/00/0000";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidName2));

        String invalidName3 = "01/-1/2222";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidName3));

    }

    @Test
    public void isValidDateFormat() {
        // null name
        assertThrows(NullPointerException.class, () -> Date.isValidDateFormat(null));

        // invalid date
        /*assertFalse(Date.isValidDateFormat("")); // empty string
        assertFalse(Date.isValidDateFormat(" ")); // spaces only

         */
        assertFalse(Date.isValidDateFormat("11/09/200*")); // contains non-integers


        // valid date
        assertTrue(Date.isValidDateFormat("11/09/2000")); // integers only
        assertTrue(Date.isValidDateFormat("29/02/2004")); // leap year
    }

    @Test
    public void isValidDateInput() {

        assertThrows(NullPointerException.class, () -> Date.isValidDateInput(null));
        assertFalse(Date.isValidDateInput("11/13/2000")); // invalid month
        assertFalse(Date.isValidDateInput("39/10/2000")); // invalid day
        assertFalse(Date.isValidDateInput("-11/11/2000")); // negative integers
        assertFalse(Date.isValidDateInput("11.223/09/2000.0")); // non-integer values
        assertFalse(Date.isValidDateInput("31/02/2000")); // Invalid day in february
        assertFalse(Date.isValidDateInput("31/11/2022")); // Invalid day in november

        // valid date
        assertTrue(Date.isValidDateInput("11/09/2000")); // integers only
        assertTrue(Date.isValidDateInput("29/02/2004")); // leap year
    }

    @Test
    public void toString_returnsValueInName() {
        String value = "22/09/2000";
        String formattedValue = "22 Sep 2000";
        Date date = new Date(value);
        assertEquals(date.toString(), formattedValue);
    }

    @Test
    public void equals() {
        String value = "22/09/2000";
        Date date = new Date(value);
        Date otherDate = new Date(value);
        assertEquals(date, otherDate);
        assertEquals(date.getLocalDate(), otherDate.getLocalDate());
    }

    @Test
    public void isOlderDate() {
        // older date
        Date date1 = new Date("11/09/2003");
        Date date2 = new Date("11/09/2001");

        assertFalse(date1.isOlderThan(date2));
        assertFalse(date1.isOlderThan(new Date("12/09/2002")));
        assertFalse(date1.isOlderThan(date1));

        // earlier date
        assertTrue(date2.isOlderThan(date1));
    }

}
