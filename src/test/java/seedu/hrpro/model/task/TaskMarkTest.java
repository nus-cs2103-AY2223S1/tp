package seedu.hrpro.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains test cases for TaskMark
 */
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
    public void isValidTaskMark() {
        // null TaskMark
        assertThrows(NullPointerException.class, () -> TaskMark.isValidTaskMark(null));

        // blank TaskMark
        assertFalse(TaskMark.isValidTaskMark("")); // empty string
        assertFalse(TaskMark.isValidTaskMark(" ")); // spaces only

        // invalid parts
        assertFalse(TaskMark.isValidTaskMark("Maybe")); // invalid day
        assertFalse(TaskMark.isValidTaskMark("truth")); // invalid month
        assertFalse(TaskMark.isValidTaskMark("FALSE")); // does not allow CAPS
        assertFalse(TaskMark.isValidTaskMark("TRUE")); // does not allow CAPS

        // valid TaskMark
        assertTrue(TaskMark.isValidTaskMark("true")); // Valid true
        assertTrue(TaskMark.isValidTaskMark("false")); //valid false
    }
}
