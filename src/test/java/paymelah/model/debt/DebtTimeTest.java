package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DebtTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DebtTime(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DebtTime(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> DebtTime.isValidTime(null));

        // invalid time
        assertFalse(DebtTime.isValidTime("")); // empty string
        assertFalse(DebtTime.isValidTime(" ")); // spaces only
        assertFalse(DebtTime.isValidTime("       ")); // many white spaces
        assertFalse(DebtTime.isValidTime("24:01")); // invalid hour
        assertFalse(DebtTime.isValidTime("20:61")); // invalid minute
        assertFalse(DebtTime.isValidTime("12.45")); // wrong format
        assertFalse(DebtTime.isValidTime("PM12.45")); // wrong format
        assertFalse(DebtTime.isValidTime("12:12:12")); // wrong format

        // valid time
        assertTrue(DebtTime.isValidTime("009:30")); // >2-digit hour
        assertTrue(DebtTime.isValidTime("09:24")); // 2-digit hour
        assertTrue(DebtTime.isValidTime("9:24")); // 1-digit hour
        assertTrue(DebtTime.isValidTime("17:0015")); // >2-digit minute
        assertTrue(DebtTime.isValidTime("17:05")); // 2-digit minute
        assertTrue(DebtTime.isValidTime("17:5")); // 1-digit minute
        assertTrue(DebtTime.isValidTime("24:00")); // 24:00
        assertTrue(DebtTime.isValidTime("00:00")); // 00:00
    }
}
