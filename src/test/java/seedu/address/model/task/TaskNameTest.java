package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class TaskNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskName = " asd";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // invalid task name
        assertFalse(TaskDescription.isValidDescription(" ")); // white space
        assertFalse(TaskDescription.isValidDescription(" Assignment 1")); // start with white space

        // valid task name
        assertTrue(TaskDescription.isValidDescription("Assignment 1"));
        assertTrue(TaskDescription.isValidDescription("Assignment 3"));
    }
}
