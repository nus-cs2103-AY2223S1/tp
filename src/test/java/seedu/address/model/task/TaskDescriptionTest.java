package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
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
    public void constructor_invalidDescriptionWithWhiteSpace_throwsIllegalArgumentException() {
        String invalidDescription = "    ";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void constructor_validDescription_createsTaskDescription() {
        assertTrue(new TaskDescription(VALID_TASK_DESCRIPTION) instanceof TaskDescription);
    }
}
