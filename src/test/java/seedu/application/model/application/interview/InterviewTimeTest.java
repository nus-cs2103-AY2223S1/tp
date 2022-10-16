package seedu.application.model.application.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.testutil.Assert.assertThrows;

public class InterviewTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewTime(null));
    }

    @Test
    public void constructor_invalidInterviewTime_throwsIllegalArgumentException() {
        String invalidInterviewTime = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidInterviewTime));
    }

    @Test
    public void isValidTime() {
        // null InterviewTime
        assertThrows(NullPointerException.class, () -> InterviewTime.isValidTime(null));

        // invalid InterviewTime
        assertFalse(InterviewTime.isValidTime("")); // empty string
        assertFalse(InterviewTime.isValidTime(" ")); // spaces only
        assertFalse(InterviewTime.isValidTime("^")); // only non-alphanumeric characters
        assertFalse(InterviewTime.isValidTime("peter*")); // contains alphanumeric characters
        assertFalse(InterviewTime.isValidTime("2000-13-12")); // month does not exist
        assertFalse(InterviewTime.isValidTime("2022-02-31")); // InterviewTime does not exist in particular month
        assertFalse(InterviewTime.isValidTime("2019-12-32")); // InterviewTime does not exist
        assertFalse(InterviewTime.isValidTime("2000-12-00")); // InterviewTime does not exist
        assertFalse(InterviewTime.isValidTime("1900-02-29")); // not leap year
        assertFalse(InterviewTime.isValidTime("1700-02-29")); // not leap year
        assertFalse(InterviewTime.isValidTime("34-12-13")); //two digit year
        assertFalse(InterviewTime.isValidTime("2022-1-13")); //one digit month
        assertFalse(InterviewTime.isValidTime("2022/08/24")); // slash


        /*
        // valid InterviewTime
        assertTrue(InterviewTime.isValidTime("2022-11-12"));
        assertTrue(InterviewTime.isValidTime("2034-12-13"));
        assertTrue(InterviewTime.isValidTime("2065-02-12"));
        assertTrue(InterviewTime.isValidTime("1987-09-12"));
        assertTrue(InterviewTime.isValidTime("2004-02-29")); // leap year
        assertTrue(InterviewTime.isValidTime("2000-02-29")); // leap year
        assertTrue(InterviewTime.isValidTime("2400-02-29")); // leap year

         */



    }
    @Test
    public void InterviewTime() {
        assertEquals(new InterviewTime("2359").toString(), "23:59:00");
        assertEquals(new InterviewTime("0900").toString(), "09:00:00");
    }
}
