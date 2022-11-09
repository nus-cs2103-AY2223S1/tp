package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTimeInWeekTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DayTimeInWeek(null));
    }

    @Test
    public void isValidDayTimeInWeekRegexTest() {

        // null
        assertThrows(NullPointerException.class, () -> DayTimeInWeek.isValidDayTimeInWeekRegex(null));

        // invalid
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekRegex("mdrdgt4")); // string length wrong
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekRegex("mon#1345")); // no use of @ symbol
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekRegex("mr4@1255")); // day of week wrong

        // valid
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekRegex("mon@1259"));
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekRegex("fri@2359"));
    }

    @Test
    public void isValidDayTimeInWeekParsingTest() {

        // null
        assertThrows(NullPointerException.class, () -> DayTimeInWeek.isValidDayTimeInWeekParsing(null));

        // invalid
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekParsing("mon@2400")); // time of day wrong
        assertFalse(DayTimeInWeek.isValidDayTimeInWeekParsing("mon@1199")); // time of day wrong

        // valid
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekParsing("mon@0000"));
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekParsing("tue@2359"));
        assertTrue(DayTimeInWeek.isValidDayTimeInWeekParsing("sun@1359"));
    }
}
