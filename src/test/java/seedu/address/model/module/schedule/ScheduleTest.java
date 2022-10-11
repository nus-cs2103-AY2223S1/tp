package seedu.address.model.module.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {

    @Test
    public void constructor_moduleNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null, ScheduleBuilder.DEFAULT_VENUE,
                ScheduleBuilder.DEFAULT_WEEKDAY, ScheduleBuilder.DEFAULT_START_TIME, ScheduleBuilder.DEFAULT_END_TIME,
                ScheduleBuilder.DEFAULT_CLASS_TYPE));
    }

    @Test
    public void constructor_venueNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(ScheduleBuilder.DEFAULT_MODULE, null,
                ScheduleBuilder.DEFAULT_WEEKDAY, ScheduleBuilder.DEFAULT_START_TIME, ScheduleBuilder.DEFAULT_END_TIME,
                ScheduleBuilder.DEFAULT_CLASS_TYPE));
    }

    @Test
    public void constructor_weekdayNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(ScheduleBuilder.DEFAULT_MODULE,
                ScheduleBuilder.DEFAULT_VENUE, null, ScheduleBuilder.DEFAULT_START_TIME,
                ScheduleBuilder.DEFAULT_END_TIME, ScheduleBuilder.DEFAULT_CLASS_TYPE));
    }

    @Test
    public void constructor_startTimeNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(ScheduleBuilder.DEFAULT_MODULE,
                ScheduleBuilder.DEFAULT_VENUE, ScheduleBuilder.DEFAULT_WEEKDAY, null,
                ScheduleBuilder.DEFAULT_END_TIME, ScheduleBuilder.DEFAULT_CLASS_TYPE));
    }

    @Test
    public void constructor_endTimeNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(ScheduleBuilder.DEFAULT_MODULE,
                ScheduleBuilder.DEFAULT_VENUE, ScheduleBuilder.DEFAULT_WEEKDAY, ScheduleBuilder.DEFAULT_START_TIME,
                null, ScheduleBuilder.DEFAULT_CLASS_TYPE));
    }

    @Test
    public void constructor_classTypeNull_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(ScheduleBuilder.DEFAULT_MODULE,
                ScheduleBuilder.DEFAULT_VENUE, ScheduleBuilder.DEFAULT_WEEKDAY, ScheduleBuilder.DEFAULT_START_TIME,
                ScheduleBuilder.DEFAULT_END_TIME, null));
    }

    @Test
    public void isTimeValidTest() {
        assertTrue(Schedule.isTimeValid("08:00", "10:00"));
        assertTrue(Schedule.isTimeValid("11:00", "12:00"));
        assertTrue(Schedule.isTimeValid("18:00", "19:00"));
        assertTrue(Schedule.isTimeValid("13:00", "21:00"));

        assertFalse(Schedule.isTimeValid("8", "10"));
        assertFalse(Schedule.isTimeValid("8:00", "11"));
        assertFalse(Schedule.isTimeValid("20:0", "21:00"));
        assertFalse(Schedule.isTimeValid("8pm", "10pm"));
    }

    @Test
    public void isConflictTest() {
        Schedule s1 = new ScheduleBuilder().build();
        Schedule s2 = new Schedule("CS2109S", new Venue("COM2 02-17"), Weekdays.Friday, "15:00",
                "17:00", ClassType.Tutorial);
        Schedule s3 = new Schedule("MA2108", new Venue("LT31"), Weekdays.Tuesday,
                "14:00", "16:00", ClassType.Lecture);

        assertTrue(s1.isConflictWith(s2));
        assertFalse(s1.isConflictWith(s3));
        assertFalse(s2.isConflictWith(s3));
    }
}
