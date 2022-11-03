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
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void isEqual() {
        TaskDescription taskDescriptionA = new TaskDescription("task 1");
        TaskDescription taskDescriptionACopy = new TaskDescription("TASK 1");
        TaskDescription taskDescriptionB = new TaskDescription("task 2");

        // same object -> returns true
        assertTrue(taskDescriptionA.equals(taskDescriptionA));

        // same value but capitalized -> returns true
        assertTrue(taskDescriptionA.equals(taskDescriptionACopy));

        // different types -> returns false
        assertFalse(taskDescriptionA.equals(5));

        // null -> returns false
        assertFalse(taskDescriptionA.equals(null));

        // different value -> returns false
        assertFalse(taskDescriptionA.equals(taskDescriptionB));
    }

    @Test
    public void compareTo() {
        TaskDescription taskDescriptionA = new TaskDescription("task 1");
        TaskDescription taskDescriptionCopy = new TaskDescription("task 1");
        TaskDescription taskDescriptionB = new TaskDescription("task 2");

        // same value
        assertTrue(taskDescriptionA.compareTo(taskDescriptionCopy) == 0);

        // different values
        assertTrue(taskDescriptionA.compareTo(taskDescriptionB) < 0);
    }
}
