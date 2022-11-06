package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new Time(null, "12:00"));

        assertThrows(NullPointerException.class, () -> new Time("12:00", null));

        assertThrows(NullPointerException.class, () -> new Time(null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {

        //bad times
        assertThrows(IllegalArgumentException.class, () -> new Time("12:00", "99:00"));
        assertThrows(IllegalArgumentException.class, () -> new Time("12:99", "13:00"));

        //end time before start time
        assertThrows(IllegalArgumentException.class, () -> new Time("13:00", "11:00"));

        // Empty start and end time
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime, invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("9 - 10")); // am and pm not specified for 12 hour format

        // valid time
        assertTrue(Time.isValidTime("12:00 - 13:00")); // 12 hour format
        assertTrue(Time.isValidTime("1:00pm 2:00pm")); // "-" omitted
        assertTrue(Time.isValidTime("2200 - 2330")); // 24 hour format
        assertTrue(Time.isValidTime("2200 to 2330")); // 24 hour format with "to"
    }
}
