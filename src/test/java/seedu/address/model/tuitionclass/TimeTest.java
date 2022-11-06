package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        // Empty start and end time
        String invalidTime = "";
        assertThrows(ParseException.class, () -> new Time(invalidTime, invalidTime));
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
