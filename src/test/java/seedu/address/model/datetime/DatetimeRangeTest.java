package seedu.address.model.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DatetimeRangeTest {

    private DatetimeRange datetimeRange = DatetimeRange.fromFormattedString("2023-01-01", "15:00", "17:00");

    @Test
    public void isOverlapping() {
        //different day -> returns false
        assertFalse(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-02-02", "10:00", "12:00")));

        //different day, overlapping timeslots -> return false
        assertFalse(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-02-02", "15:00", "16:00")));

        //same day, non-overlapping timeslots -> return false
        assertFalse(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "10:00", "15:00")));

        assertFalse(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "17:00", "20:00")));

        //same datetimeRange
        assertTrue(datetimeRange.isOverlapping(datetimeRange));

        //same day, overlapping timeslots -> return true
        assertTrue(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "15:00", "16:00")));

        assertTrue(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "16:00", "18:00")));

        assertTrue(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "14:00", "16:00")));

        assertTrue(datetimeRange.isOverlapping(
                DatetimeRange.fromFormattedString("2023-01-01", "14:00", "18:00")));
    }

    @Test
    public void getValuesDatetimeRange_success() {
        assertEquals("2023-01-01 15:00", datetimeRange.getStartDatetimeFormatted());

        assertEquals("2023-01-01 17:00", datetimeRange.getEndDatetimeFormatted());
    }

    @Test
    public void toStringDatetimeRange_success() {
        assertEquals("2023 Jan 01, 15:00 to 17:00", datetimeRange.toString());
    }
}
