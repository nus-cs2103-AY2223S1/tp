package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTitle(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskTitle(invalidTitle));
    }

    @Test
    public void constructor_invalidTitleWithWhiteSpace_throwsIllegalArgumentException() {
        String invalidTitle = "    ";
        assertThrows(IllegalArgumentException.class, () -> new TaskTitle(invalidTitle));
    }

    @Test
    public void constructor_validTitle_createsTaskTitle() {
        assertTrue(new TaskTitle(VALID_TASK_TITLE) instanceof TaskTitle);
    }
}
