package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateRangeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateRange(null));
    }

    @Test
    public void constructor_invalidDateRange_throwsIllegalArgumentException() {
        String invalidDateRange = "";
        assertThrows(IllegalArgumentException.class, () -> new DateRange(invalidDateRange));
    }

    @Test
    public void isValidDateRange() {
        // null date range
        assertThrows(NullPointerException.class, () -> DateRange.isValidDateRange(null));

        // invalid date range
        assertFalse(DateRange.isValidDateRange("")); // empty string
        assertFalse(DateRange.isValidDateRange("13/09/22")); // 1 date only
        assertFalse(DateRange.isValidDateRange("13/09/22 15/09/22")); // wrong separator
        assertFalse(DateRange.isValidDateRange("13/09/2022 - 15/09/2022")); // wrong year format
        assertFalse(DateRange.isValidDateRange("13/9/22 - 15/9/22")); // wrong month format
        assertFalse(DateRange.isValidDateRange("1/09/22 - 3/09/22")); // wrong day format
        assertFalse(DateRange.isValidDateRange("13/09/22 - 31/09/22")); // invalid day
        assertFalse(DateRange.isValidDateRange("13/09/22 - 15/13/22")); // invalid month
        assertFalse(DateRange.isValidDateRange("13/09/22 - 13/09/22")); // end date same as start date
        assertFalse(DateRange.isValidDateRange("13/09/22 - 12/09/22")); // end date earlier than start date
        assertFalse(DateRange.isValidDateRange("13/09/22 - 15/09/22 - 17/09/22")); // more than 2 dates

        // valid date range
        assertTrue(DateRange.isValidDateRange("13/09/22 - 14/09/22")); // 1 day
        assertTrue(DateRange.isValidDateRange("30/09/22 - 01/10/22")); // across months
        assertTrue(DateRange.isValidDateRange("13/09/22 - 15/09/23")); // across years
    }

    @Test
    public void hashcode() {
        DateRange tempDateRange = new DateRange("13/09/22 - 14/09/22");

        // same values -> return true
        assertEquals(tempDateRange, new DateRange("13/09/22 - 14/09/22"));

        // different values -> return false
        assertNotEquals(tempDateRange, new DateRange("13/09/22 - 15/09/22"));
    }
}
