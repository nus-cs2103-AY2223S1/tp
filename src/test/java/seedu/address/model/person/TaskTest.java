package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void constructor_invalidTask_throwsIllegalArgumentException() {
        String invalidTask = "";
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTask));
    }

    @Test
    public void isValidTask() {
        // null task
        assertThrows(NullPointerException.class, () -> Task.isValidTask(null));

        // invalid tasks
        assertFalse(Task.isValidTask("")); // empty string
        assertFalse(Task.isValidTask(" ")); // spaces only

        // valid tasks
        assertTrue(Task.isValidTask("Task description for Task 1")); // normal task description
        assertTrue(Task.isValidTask("-")); // one character
        assertTrue(Task.isValidTask("This is a very long task description that is very long.")); // long address
    }
}
