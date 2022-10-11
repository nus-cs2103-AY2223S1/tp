package seedu.address.model.module.task.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.task.Task;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_A.isSameTask(TASK_A));

        // completely different module -> returns false;
        assertFalse(TASK_A.isSameTask(TASK_B));

        // null -> returns false
        assertFalse(TASK_A.isSameTask(null));

        // same task description
        Task taskWithSameDescription = new Task(VALID_TASK_A);
        assertTrue(TASK_A.isSameTask(taskWithSameDescription));

        // description has trailing spaces
        String moduleWithTrailingSpaces = VALID_TASK_A + " ";
        Task taskWithTrailingSpaces =
                new Task(moduleWithTrailingSpaces);
        assertFalse(TASK_A.isSameTask(taskWithTrailingSpaces));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task taskACopy = new Task(VALID_TASK_A);
        assertTrue(TASK_A.equals(taskACopy));

        // same object -> returns true
        assertTrue(TASK_A.equals(taskACopy));

        // null -> returns false
        assertFalse(TASK_A.equals(null));

        // different type -> returns false
        assertFalse(TASK_A.equals(5));

        // different task description -> returns false
        assertFalse(TASK_A.equals(TASK_B));

        // description has trailing spaces
        String moduleWithTrailingSpaces = VALID_TASK_A + " ";
        Task taskWithTrailingSpaces =
                new Task(moduleWithTrailingSpaces);
        assertFalse(TASK_A.isSameTask(taskWithTrailingSpaces));
    }
}
