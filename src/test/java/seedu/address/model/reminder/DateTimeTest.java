package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.reminder.DateTime.DATE_TIME_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.model.message.Message;

class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Message(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidMessage = " ";
        assertThrows(AssertionError.class, () -> new DateTime(invalidMessage));
    }

    @Test
    public void getDateTimeString_validDateTime_success() {
        assertEquals(REMINDER_MEETING_DATETIME_STRING, REMINDER_MEETING_DATETIME.getDateTimeString());
    }

    @Test
    public void compareTo_differentDates_success() {
        DateTime dateTime1 = new DateTime("2022-12-12 at 11:11");
        DateTime dateTime2 = new DateTime("2022-12-13 at 11:11");

        assertEquals(-1, dateTime1.compareTo(dateTime2));
        assertEquals(1, dateTime2.compareTo(dateTime1));
    }

    @Test
    public void compareTo_sameDates_success() {
        DateTime dateTime1 = new DateTime("2022-12-12 at 11:11");
        DateTime dateTime2 = new DateTime("2022-12-12 at 11:11");

        assertEquals(0, dateTime1.compareTo(dateTime1));
        assertEquals(0, dateTime1.compareTo(dateTime2));
        assertEquals(0, dateTime2.compareTo(dateTime1));
    }

    @Test
    public void equals() {
        DateTime dateTime1 = new DateTime("2022-12-12 at 11:11");
        DateTime dateTime2 = new DateTime("2022-12-12 at 11:11");
        DateTime dateTime3 = new DateTime("2022-12-13 at 11:12");

        // same object -> true
        assertTrue(dateTime1.equals(dateTime1));

        // different object, same date and time -> true
        assertTrue(dateTime1.equals(dateTime2));

        // different date and time -> false
        assertFalse(dateTime2.equals(dateTime3));
    }

    @Test
    public void isValidDateTimeString_invalidDateTimeString_false() {
        String invalidDateTimeString = "2022-12-12 at ";
        assertFalse(DateTime.isValidDateTimeString(invalidDateTimeString, DATE_TIME_FORMATTER));
    }

    @Test
    public void isValidDateTimeString_validDateTimeString_true() {
        String invalidDateTimeString = "2022-12-12 at 11:11";
        assertTrue(DateTime.isValidDateTimeString(invalidDateTimeString, DATE_TIME_FORMATTER));
    }
}
