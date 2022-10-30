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
    public void isValidDayTimeInWeek() {

        // null
        assertThrows(NullPointerException.class, () -> DayTimeInWeek.isValidDayTimeInWeek(null));

        // invalid
        assertFalse(DayTimeInWeek.isValidDayTimeInWeek("mdrdgt4")); // string length wrong
        assertFalse(DayTimeInWeek.isValidDayTimeInWeek("mr4@1255")); // day of week wrong
        assertFalse(DayTimeInWeek.isValidDayTimeInWeek("mon@2400")); // time of day wrong
        assertFalse(DayTimeInWeek.isValidDayTimeInWeek("mon@1199"));

        // valid
        assertTrue(DayTimeInWeek.isValidDayTimeInWeek("mon@1259"));
        assertTrue(DayTimeInWeek.isValidDayTimeInWeek("fri@2359"));
        assertTrue(DayTimeInWeek.isValidDayTimeInWeek("sun@0000"));
    }
}
