package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidDateTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidDateTime(null));

        // invalid time
        assertFalse(Time.isValidDateTime("")); // empty string
        assertFalse(Time.isValidDateTime(" ")); // spaces only
        assertFalse(Time.isValidDateTime("^")); // only non-alphanumeric characters
        assertFalse(Time.isValidDateTime("2022-12-011400")); // no space
        assertFalse(Time.isValidDateTime("2022-12-01 1400 ")); // space at the end
        assertFalse(Time.isValidDateTime("2022-49-40 1400 ")); // impossible time

        // valid time
        assertTrue(Time.isValidDateTime("2022-12-01 1400")); // correct format

    }
}
