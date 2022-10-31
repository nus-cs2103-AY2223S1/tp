package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeIntervalTest {

    public static final String INVALID_TIME_INTERVAL_BECAUSE_OF_PARSING =
            "mon@2400-tue@1399";

    public static final String VALID_TIME_INTERVAL_AFTER_PARSING =
            "mon@1200-tue@1259";

    public static final String VALID_TIME_INTERVAL_FROM_SUNDAY_NIGHT_TO_MONDAY_MORNING =
            "sun@2300 - mon@0200";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeInterval(null));
    }

    @Test
    public void isValidTimeIntervalBasedOffRegex() {
        // null Time Interval
        assertThrows(NullPointerException.class, () -> TimeInterval.isValidTimeInterval(null));

        // Invalid Time Interval based off Regex only
        // day should be in short form
        assertFalse(TimeInterval.isValidTimeInterval("monday@1200-tuesday@1200"));
        assertFalse(TimeInterval.isValidTimeInterval("mon@00000-tue@0000")); // invalid size of numbers
        assertFalse(TimeInterval.isValidTimeInterval("mon@1200 @ tue@1200")); // invalid use of @

        // Valid Time Interval based off Regex only
        assertTrue(TimeInterval.isValidTimeInterval("mon@1200-tue@1200"));
        // invalid time format but valid based off Regex
        // would require further testing in this case
        assertTrue(TimeInterval.isValidTimeInterval("mon@2400-tue@1200"));
        // can insert infinite amount of spaces
        assertTrue(TimeInterval.isValidTimeInterval("mon@1359     -   mon@1600"));
    }

    @Test
    public void isValidTimeIntervalBasedOffParsingToDateTimeInWeek() {
        // null Time Interval
        assertThrows(NullPointerException.class, () -> TimeInterval.isValidTimeInterval(null));

        String invalidStartTime = TimeInterval.getStartingDayTimeInWeek(INVALID_TIME_INTERVAL_BECAUSE_OF_PARSING);
        // invalidStartTime = "mon@2400"
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekParsing(invalidStartTime));
        String invalidEndTime = TimeInterval.getEndingDayTimeInWeek(INVALID_TIME_INTERVAL_BECAUSE_OF_PARSING);
        // invalidEndTime = "tue@1399"
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekParsing(invalidEndTime));

        String validStartTime = TimeInterval.getStartingDayTimeInWeek(VALID_TIME_INTERVAL_AFTER_PARSING);
        // validStartTime = "mon@0000"
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekParsing(validStartTime));
        String validEndTime = TimeInterval.getEndingDayTimeInWeek(VALID_TIME_INTERVAL_AFTER_PARSING);
        // validEndTime = "tue@1259"
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekParsing(validEndTime));
    }

    @Test
    public void isAvailableDayTimeInWeek() {
        TimeInterval timeInterval = new TimeInterval(VALID_TIME_INTERVAL_AFTER_PARSING);
        DayTimeInWeek availableTime = new DayTimeInWeek("mon@2200");
        DayTimeInWeek unavailableTime = new DayTimeInWeek("tue@1300");

        assertFalse(timeInterval.isAvailable(unavailableTime)); // tue@1300 is not within the time interval

        assertTrue(timeInterval.isAvailable(availableTime)); // mon@2200 is not within the time interval
    }

    @Test
    public void isAvailableDayTimeInWeekFromSundayNightToMondayMorning() {
        // tests if the code successfully calculates time intervals from Sunday night to early Monday morning

        TimeInterval timeInterval = new TimeInterval(VALID_TIME_INTERVAL_FROM_SUNDAY_NIGHT_TO_MONDAY_MORNING);
        DayTimeInWeek availableTime = new DayTimeInWeek("mon@0100");
        DayTimeInWeek unavailableTime = new DayTimeInWeek("mon@0300");

        assertFalse(timeInterval.isAvailable(unavailableTime)); // mon@0300 is not within the time interval

        assertTrue(timeInterval.isAvailable(availableTime)); // mon@0100 is within the time interval
    }
}
