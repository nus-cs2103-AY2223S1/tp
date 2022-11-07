package seedu.application.model.application.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(InterviewTime.isValidTime("235900")); // extra seconds
        assertFalse(InterviewTime.isValidTime("23")); // without minutes
        assertFalse(InterviewTime.isValidTime("159")); // without preamble 0
        assertFalse(InterviewTime.isValidTime("100")); // lack of ending 0
        assertFalse(InterviewTime.isValidTime("23:59")); // with colon
        assertFalse(InterviewTime.isValidTime("01:59:00")); // with colon and seconds
        assertFalse(InterviewTime.isValidTime("2401")); // invalid hour
        assertFalse(InterviewTime.isValidTime("0760")); // invalid minute

        // valid InterviewTime
        assertTrue(InterviewTime.isValidTime("0000"));
        assertTrue(InterviewTime.isValidTime("2359"));
        assertTrue(InterviewTime.isValidTime("0756"));
        assertTrue(InterviewTime.isValidTime("1600"));
    }

    @Test
    public void interviewTime() {
        assertEquals(new InterviewTime("2359").toString(), "23:59");
        assertEquals(new InterviewTime("0900").toString(), "09:00");

        assertEquals(new InterviewTime("1645").toCommandString(), "1645");
        assertEquals(new InterviewTime("0901").toCommandString(), "0901");
    }
}
