package seedu.address.model.application.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewDate(null));
    }

    @Test
    public void constructor_invalidInterviewDate_throwsIllegalArgumentException() {
        String invalidInterviewDate = "";
        assertThrows(IllegalArgumentException.class, () -> new InterviewDate(invalidInterviewDate));
    }

    @Test
    public void isValidDate() {
        // null InterviewDate
        assertThrows(NullPointerException.class, () -> InterviewDate.isValidDate(null));

        // invalid InterviewDate
        assertFalse(InterviewDate.isValidDate("")); // empty string
        assertFalse(InterviewDate.isValidDate(" ")); // spaces only
        assertFalse(InterviewDate.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(InterviewDate.isValidDate("peter*")); // contains alphanumeric characters
        assertFalse(InterviewDate.isValidDate("2000-13-12")); // month does not exist
        assertFalse(InterviewDate.isValidDate("2022-02-31")); // InterviewDate does not exist in particular month
        assertFalse(InterviewDate.isValidDate("2019-12-32")); // InterviewDate does not exist
        assertFalse(InterviewDate.isValidDate("2000-12-00")); // InterviewDate does not exist
        assertFalse(InterviewDate.isValidDate("1900-02-29")); // not leap year
        assertFalse(InterviewDate.isValidDate("1700-02-29")); // not leap year
        assertFalse(InterviewDate.isValidDate("34-12-13")); //two digit year
        assertFalse(InterviewDate.isValidDate("2022-1-13")); //one digit month
        assertFalse(InterviewDate.isValidDate("2022/08/24")); // slash


        // valid InterviewDate
        assertTrue(InterviewDate.isValidDate("2022-11-12"));
        assertTrue(InterviewDate.isValidDate("2034-12-13"));
        assertTrue(InterviewDate.isValidDate("2065-02-12"));
        assertTrue(InterviewDate.isValidDate("1987-09-12"));
        assertTrue(InterviewDate.isValidDate("2004-02-29")); // leap year
        assertTrue(InterviewDate.isValidDate("2000-02-29")); // leap year
        assertTrue(InterviewDate.isValidDate("2400-02-29")); // leap year


    }
    @Test
    public void InterviewDate() {
        assertEquals(new InterviewDate("2022-11-12").toString(), "Nov 12 2022");
        assertEquals(new InterviewDate("2020-02-29").toString(), "Feb 29 2020");
    }
}
