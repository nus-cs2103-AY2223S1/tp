package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    private static final DateTime EARLIER_TIME = new DateTime("2022-10-05T10:30:15");
    private static final DateTime LATER_TIME = new DateTime("2022-10-05T10:50:15");

    private static final String FORMATTED_DATE = "5 Oct 2022";

    private static final String INVALID_DATE_INPUT = "hello";
    private static final String INVALID_DATE_FORMAT = "2022T00:00:00";
    private static final String INVALID_DATE_OUT_OF_RANGE = "2022-13-13T00:00:00";

    private static final String VALID_DATE = "2022-11-11T00:00:00";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void isAfter() {
        assertTrue(LATER_TIME.isAfter(EARLIER_TIME));
    }

    @Test
    public void isOnSameDay() {
        assertTrue(LATER_TIME.isOnSameDay(EARLIER_TIME));
    }

    @Test
    public void isValidDateTime() {
        // invalid dateTime
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(INVALID_DATE_INPUT));
        assertFalse(DateTime.isValidDateTime(INVALID_DATE_FORMAT));
        assertFalse(DateTime.isValidDateTime(INVALID_DATE_OUT_OF_RANGE));

        // valid dateTime
        assertTrue(DateTime.isValidDateTime(VALID_DATE));
    }

    @Test
    public void getDate() {
        assertEquals(FORMATTED_DATE, EARLIER_TIME.getDate());
    }
}
