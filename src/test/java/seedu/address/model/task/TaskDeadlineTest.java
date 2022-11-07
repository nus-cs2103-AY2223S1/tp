package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_invalidTaskDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "10-10-1010";
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline(invalidDeadline));
    }

    @Test
    public void isInDeadlineFormat() {
        // invalid deadline format
        assertFalse(TaskDeadline.isInDeadlineFormat("")); //empty string
        assertFalse(TaskDeadline.isInDeadlineFormat("13-10-2020")); // wrong format
        assertFalse(TaskDeadline.isValidDate("2020-10-13")); // wrong format
        assertFalse(TaskDeadline.isValidDate("10102020")); // wrong format

        // valid deadline format
        assertTrue(TaskDeadline.isInDeadlineFormat("10/10/2020"));
        assertTrue(TaskDeadline.isInDeadlineFormat("05/10/3022")); // a thousand years later
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidDate(null));

        // invalid date
        assertFalse(TaskDeadline.isValidDate("")); // empty string
        assertFalse(TaskDeadline.isValidDate(" ")); // spaces only
        assertFalse(TaskDeadline.isValidDate("03/13/2020")); // month exceed 12
        assertFalse(TaskDeadline.isValidDate("-1/10/2020")); // day is negative

        // valid date
        assertTrue(TaskDeadline.isValidDate("10/10/2020"));
        assertTrue(TaskDeadline.isValidDate("05/10/3022")); // a thousand years later
    }
}
