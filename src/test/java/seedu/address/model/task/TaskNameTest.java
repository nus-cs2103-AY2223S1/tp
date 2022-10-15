package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.task.TaskName;



public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidDescription));
    }

    @Test
    public void isValidTaskDescription() {
        String invalidDescription = "";
        String validDescription = "Test";
        assertTrue(TaskName.isValidTaskName(validDescription));
        assertFalse(TaskName.isValidTaskName(invalidDescription));
    }
}
