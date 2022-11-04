package seedu.hrpro.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidTaskDescription));
    }

    @Test
    public void isValidTaskDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid staff name
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only
        assertFalse(TaskDescription.isValidTaskDescription("^")); // only non-alphanumeric characters
        assertFalse(TaskDescription.isValidTaskDescription("peter*")); // contains non-alphanumeric characters

        // valid staff name
        assertTrue(TaskDescription.isValidTaskDescription("peter jack has homework to do")); // alphabets only
        assertTrue(TaskDescription.isValidTaskDescription("12345")); // numbers only
        assertTrue(TaskDescription.isValidTaskDescription("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskDescription.isValidTaskDescription("Capital Tan")); // with capital letters
        assertTrue(TaskDescription.isValidTaskDescription("David Roger Jackson Ray Jr 2nd "
                                                        + "has to schedule a meeting")); // long names
    }
}
