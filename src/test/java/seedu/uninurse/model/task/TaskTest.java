package seedu.uninurse.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void isValidTaskDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Task.isValidTaskDescription(null));
    }

    @Test
    public void isValidTaskDescription_validDescription_returnsTrue() {
        // numbers in task description -> returns true
        assertTrue(Task.isValidTaskDescription("123"));

        // special characters in task description -> returns true
        assertTrue(Task.isValidTaskDescription("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // valid task description -> returns true
        assertTrue(Task.isValidTaskDescription("Check vitals"));
    }

    @Test
    public void isValidTaskDescription_invalidDescription_returnsFalse() {
        // empty task description -> returns false
        assertFalse(Task.isValidTaskDescription(""));

        // blank task description -> returns false
        assertFalse(Task.isValidTaskDescription("  "));
    }

}
