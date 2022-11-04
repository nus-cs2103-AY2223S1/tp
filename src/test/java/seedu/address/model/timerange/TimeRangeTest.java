package seedu.address.model.timerange;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimeRangeTest {

    @Test
    public void isValidTimeRangeFormatTest() {
        assertTrue(TimeRange.isValidTimeRangeFormat("1000-1100 60"));

        // different format
        assertFalse(TimeRange.isValidTimeRangeFormat("1000 1100 60"));
    }

    @Test
    public void isValidEndTimeTest() {
        LocalTime startTime = LocalTime.of(12, 0);
        LocalTime validEndTime = LocalTime.of(13, 0);
        LocalTime invalidEndTime = LocalTime.of(12, 30);
        Integer duration = 60;
        assertTrue(TimeRange.isValidEndTime(startTime, validEndTime, duration));
        assertFalse(TimeRange.isValidEndTime(startTime, invalidEndTime, duration));
    }

}
