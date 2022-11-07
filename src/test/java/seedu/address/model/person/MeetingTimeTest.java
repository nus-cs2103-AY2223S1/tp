package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingTime(null));
    }

    @Test
    public void constructor_emptyMeetingTime_throwsIllegalArgumentException() {
        String emptyMeetingTimeName = "";

        assertThrows(IllegalArgumentException.class, () -> new MeetingTime(emptyMeetingTimeName));
    }

    @Test
    public void isValidMeetingTimeName() {
        // null meeting time
        assertThrows(NullPointerException.class, () -> MeetingTime.isValidMeetingTime(null));

        // blank meeting time
        assertFalse(MeetingTime.isValidMeetingTime(""));
        assertFalse(MeetingTime.isValidMeetingTime("  "));

        // missing parts
        assertFalse(MeetingTime.isValidMeetingTime("32-2022-19:00")); //missing month
        assertFalse(MeetingTime.isValidMeetingTime("11-2022-19:00")); //missing day
        assertFalse(MeetingTime.isValidMeetingTime("32-11-19:00")); //missing year
        assertFalse(MeetingTime.isValidMeetingTime("32-11-2022")); //missing time

        // invalid parts
        assertFalse(MeetingTime.isValidMeetingTime("32-11-2022-19:00")); //day after 31
        assertFalse(MeetingTime.isValidMeetingTime("00-11-2022-19:00")); //day before 1
        assertFalse(MeetingTime.isValidMeetingTime("1-11-2022-19:00")); //day in 1 digit
        assertFalse(MeetingTime.isValidMeetingTime("001-02-2022-19:00")); //valid day in 3 digits
        assertFalse(MeetingTime.isValidMeetingTime("30-13-2022-19:00")); //month after 12
        assertFalse(MeetingTime.isValidMeetingTime("30-00-2022-19:00")); //month before 1
        assertFalse(MeetingTime.isValidMeetingTime("30-1-2022-19:00")); //month in 1 digit
        assertFalse(MeetingTime.isValidMeetingTime("30-011-2022-19:00")); //valid month in 3 digits
        assertFalse(MeetingTime.isValidMeetingTime("30-11-202-19:00")); //year before 4 digits
        assertFalse(MeetingTime.isValidMeetingTime("30-11-20225-19:00")); //year after 4 digits
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-24:00")); //hours past 23
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-19:60")); //minutes past 59
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-9:60")); //single digit hours
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-19:6")); //single digit minutes
        assertFalse(MeetingTime.isValidMeetingTime("3-0-11-2022-19:00")); //too many hyphens
        assertFalse(MeetingTime.isValidMeetingTime("30--11-2022-19:00")); //extra hyphen after day
        assertFalse(MeetingTime.isValidMeetingTime("30-11--2022-19:00")); //extra hyphen after month
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022--19:00")); //extra hyphen after year
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-19:00-")); //extra hyphen after time
        assertFalse(MeetingTime.isValidMeetingTime("30/11/2022/19:00")); //wrong symbols
        assertFalse(MeetingTime.isValidMeetingTime("30-11-2022-19:00:29")); //wrong time format
        assertFalse(MeetingTime.isValidDayMonth("29-02-2022-16:00")); //2022 is not a leap year
        assertFalse(MeetingTime.isValidDayMonth("30-02-2022-16:00")); //February does not have 30 days
        assertFalse(MeetingTime.isValidDayMonth("31-02-2022-16:00")); //February does not have 31 days
        assertFalse(MeetingTime.isValidDayMonth("31-04-2022-16:00")); //April does not have 31 days
        assertFalse(MeetingTime.isValidDayMonth("31-06-2022-16:00")); //June does not have 31 days
        assertFalse(MeetingTime.isValidDayMonth("31-09-2022-16:00")); //September does not have 31 days
        assertFalse(MeetingTime.isValidDayMonth("31-11-2022-16:00")); //April does not have 31 days

        //valid parts
        assertTrue(MeetingTime.isValidMeetingTime("01-06-2022-19:00")); //minimal day
        assertTrue(MeetingTime.isValidMeetingTime("31-06-2022-19:00")); //maximal day
        assertTrue(MeetingTime.isValidMeetingTime("05-01-2022-19:00")); //minimal month
        assertTrue(MeetingTime.isValidMeetingTime("06-12-2022-19:00")); //maximal month
        assertTrue(MeetingTime.isValidMeetingTime("05-06-2000-19:00")); //minimal year
        assertTrue(MeetingTime.isValidMeetingTime("01-06-2099-19:00")); //maximal year
        assertTrue(MeetingTime.isValidMeetingTime("05-06-2022-00:30")); //minimal hours
        assertTrue(MeetingTime.isValidMeetingTime("01-06-2022-23:00")); //maximal hours
        assertTrue(MeetingTime.isValidMeetingTime("05-06-2022-15:00")); //minimal minutes
        assertTrue(MeetingTime.isValidMeetingTime("01-06-2022-19:59")); //maximal minutes
    }
}
