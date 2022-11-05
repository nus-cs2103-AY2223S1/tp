package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalDateTimes;

public class InterviewDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewDateTime(null));
    }

    @Test
    public void constructor_invalidInterviewDateTime_throwsIllegalArgumentException() {
        // valid date and invalid time
        assertThrows(IllegalArgumentException.class, () -> new InterviewDateTime(TypicalDateTimes.SECOND_VALID_DATE
                + " " + TypicalDateTimes.FIRST_INVALID_TIME));

        // invalid date and valid time
        assertThrows(IllegalArgumentException.class, () -> new InterviewDateTime(TypicalDateTimes.SECOND_INVALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME));
    }

    @Test
    public void isValidInterviewDateTime() {
        // null interview date time
        assertThrows(NullPointerException.class, () -> InterviewDateTime.isValidInterviewDateTime(null));

        // invalid date
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_INVALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // only MMM or M allowed for month
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.SECOND_INVALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // only uuuu
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.THIRD_INVALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // 29 Feb on non-leap years
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_NONSTANDARD_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // does not exist
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.SECOND_NONSTANDARD_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // does not exist

        // invalid time
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_VALID_DATE
                + " " + TypicalDateTimes.FIRST_INVALID_TIME)); // colon missing
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_VALID_DATE
                + " " + TypicalDateTimes.SECOND_INVALID_TIME)); // :mm missing
        assertFalse(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_VALID_DATE
                + " " + TypicalDateTimes.THIRD_INVALID_TIME)); // space missing

        // empty interview date time
        assertFalse(InterviewDateTime.isValidInterviewDateTime(" "));

        // valid interview date time
        assertTrue(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIRST_VALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // d MMM uuuu HH:mm
        assertTrue(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.SECOND_VALID_DATE
                + ", " + TypicalDateTimes.SECOND_VALID_TIME)); // d MMM, h:mm a
        assertTrue(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.THIRD_VALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // d/M/uuuu HH:mm
        assertTrue(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FOURTH_VALID_DATE
                + ", " + TypicalDateTimes.SECOND_VALID_TIME)); // d/M, h:mm a
        assertTrue(InterviewDateTime.isValidInterviewDateTime(TypicalDateTimes.FIFTH_VALID_DATE
                + " " + TypicalDateTimes.FIRST_VALID_TIME)); // 29 Feb on leap year
    }
}
