package seedu.rc4hdb.model.venues.booking.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Day}.
 */
public class DayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidDay = "monday";
        assertThrows(IllegalArgumentException.class, () -> new Day(invalidDay));
    }

    @Test
    public void constructor_validDay_constructDay() {
        assertTrue(new Day("tue") instanceof Day);
    }

    @Test
    public void isValidDay() {
        // EP: within the list of valid days, case-insensitive
        assertTrue(Day.isValidDay("Mon"));
        assertTrue(Day.isValidDay("SuN"));
        assertTrue(Day.isValidDay("WED"));

        // EP: not inside the list of valid days
        assertFalse(Day.isValidDay("Monday"));
        assertFalse(Day.isValidDay("12345"));
        assertFalse(Day.isValidDay("---..123asd"));
        assertFalse(Day.isValidDay(" "));
        assertFalse(Day.isValidDay(""));
        assertFalse(Day.isValidDay(null));
    }

}
