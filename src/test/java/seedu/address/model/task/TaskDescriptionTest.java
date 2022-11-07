package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class TaskDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidDescription = " asd";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // invalid description
        assertFalse(TaskDescription.isValidDescription(" ")); // white space
        assertFalse(TaskDescription.isValidDescription(" Complete Today")); // start with white space

        // valid description
        assertTrue(TaskDescription.isValidDescription("Complete Today"));
        assertTrue(TaskDescription.isValidDescription("Complete Tomorrow"));
    }
}
