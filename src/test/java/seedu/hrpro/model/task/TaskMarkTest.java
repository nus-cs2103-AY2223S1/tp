package seedu.hrpro.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskMarkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskMark(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskMark = "MAYBE";
        assertThrows(IllegalArgumentException.class, () -> new TaskMark(invalidTaskMark));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> TaskMark.isValidTaskMark(null));

        // blank deadline
        assertFalse(TaskMark.isValidTaskMark("")); // empty string
        assertFalse(TaskMark.isValidTaskMark(" ")); // spaces only

        // invalid parts
        assertFalse(TaskMark.isValidTaskMark("Maybe")); // invalid day
        assertFalse(TaskMark.isValidTaskMark("truth")); // invalid month

        // valid deadline
        assertTrue(TaskMark.isValidTaskMark("true")); // Valid true
        assertTrue(TaskMark.isValidTaskMark("false")); //valid false
    }
}
