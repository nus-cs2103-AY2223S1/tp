package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.TestUtil.getCurrentDate;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void isValidDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));
    }

    @Test
    public void isValidDateTime_validDateTime_returnsTrue() {
        assertTrue(DateTime.isValidDateTime("12-12-2022 1815"));
        assertTrue(DateTime.isValidDateTime("09-08-2022 1000"));
    }

    @Test
    public void isValidDateTime_invalidDateTime_returnsFalse() {
        // day of month and month of year swapped
        assertFalse(DateTime.isValidDateTime("09-22-2022 1815"));

        // invalid characters
        assertFalse(DateTime.isValidDateTime("09-i6-2022 17: 16"));
    }

    @Test
    public void isToday_sameDay_returnsTrue() {
        assertTrue(new DateTime(getCurrentDate()).isToday());
    }

    @Test
    public void isToday_differentDay_returnsFalse() {
        assertFalse(new DateTime().isToday());
    }

    // TODO date related tests, e.g date validation etc
}
