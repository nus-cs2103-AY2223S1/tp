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
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // blank deadline
        assertFalse(TaskDeadline.isValidTaskDeadline("")); // empty string
        assertFalse(TaskDeadline.isValidTaskDeadline(" ")); // spaces only

        // missing deadline parts
        assertFalse(TaskDeadline.isValidTaskDeadline("2022-12")); // missing day part
        assertFalse(TaskDeadline.isValidTaskDeadline("2022-15")); // missing month part
        assertFalse(TaskDeadline.isValidTaskDeadline("12-20")); // missing year part

        // invalid parts
        // invalid month
        // invalid day

        // valid deadline
        assertTrue(TaskDeadline.isValidTaskDeadline("2022-12-31")); // underscore in local part
    }
}
