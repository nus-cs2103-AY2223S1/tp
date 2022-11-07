package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewDateTest {

    @Test
    public void constructor_blankDateTimeStr_doesNotThrowIllegalArgumentException() {
        String blankDateTimeStr = "";
        assertDoesNotThrow(() -> new InterviewDate(blankDateTimeStr));
    }

    @Test
    public void isValidDatetimeStr() {
        // null datetime string
        assertThrows(NullPointerException.class, () -> InterviewDate.isValidDatetimeStr(null));

        // invalid datetime string
        assertFalse(InterviewDate.isValidDatetimeStr("")); // empty string
        assertFalse(InterviewDate.isValidDatetimeStr(" ")); // spaces only
        assertFalse(InterviewDate.isValidDatetimeStr("2022-12-32 00:00")); // invalid date
        assertFalse(InterviewDate.isValidDatetimeStr("2022-02-29 00:00")); // invalid leap year date
        assertFalse(InterviewDate.isValidDatetimeStr("2022-02-28 24:00")); // invalid time
        assertFalse(InterviewDate.isValidDatetimeStr("02-28-2022 23:00")); // invalid datetime format

        // valid datetime string
        assertTrue(InterviewDate.isValidDatetimeStr("2000-02-29 12:00")); // leap year rare (millennium)
        assertTrue(InterviewDate.isValidDatetimeStr("2004-02-29 12:00")); // leap year common (every 4 years)
        assertTrue(InterviewDate.isValidDatetimeStr("2000-01-01 00:00")); // first day of the month
        assertTrue(InterviewDate.isValidDatetimeStr("2000-01-29 23:59")); // last day of the month
        assertTrue(InterviewDate.isValidDatetimeStr("2022-12-31 23:59")); // last day of the year
    }
}
