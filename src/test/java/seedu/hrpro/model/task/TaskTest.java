package seedu.hrpro.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_BRAVO;
import static seedu.hrpro.testutil.TypicalTasks.TASK_1;
import static seedu.hrpro.testutil.TypicalTasks.TASK_ALPHA;

import org.junit.jupiter.api.Test;

import seedu.hrpro.testutil.TaskBuilder;
import seedu.hrpro.testutil.TypicalTasks;

public class TaskTest {
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_1.isSameTask(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedTask1 = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_BRAVO).build();
        assertTrue(TASK_1.isSameTask(editedTask1));

        // different description, all other attributes same -> returns false
        editedTask1 = new TaskBuilder(TASK_1).withDescription(VALID_TASKDESCRIPTION_BRAVO).build();
        assertFalse(TASK_1.isSameTask(editedTask1));

        // name differs in case, all other attributes same -> returns true
        Task editedAlpha = new TaskBuilder(TASK_ALPHA)
                                .withDescription(VALID_TASKDESCRIPTION_ALPHA.toLowerCase()).build();
        assertTrue(TASK_ALPHA.isSameTask(editedAlpha));

        // name has trailing spaces, all other attributes same -> returns false
        String descriptionWithTrailingSpaces = VALID_TASKDESCRIPTION_ALPHA + " ";
        editedAlpha = new TaskBuilder(TASK_ALPHA).withDescription(descriptionWithTrailingSpaces).build();
        assertFalse(TASK_ALPHA.isSameTask(editedAlpha));
    }
    @Test
    public void equals() {
        // same values -> returns true
        Task task1Copy = new TaskBuilder(TASK_1).build();
        assertTrue(TASK_1.equals(task1Copy));

        // same object -> returns true
        assertTrue(TASK_1.equals(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.equals(null));

        // different type -> returns false
        assertFalse(TASK_1.equals(5));

        // different task -> returns false
        assertFalse(TASK_1.equals(TypicalTasks.TASK_2));

        // different description -> returns false
        Task editedTask1 = new TaskBuilder(TASK_1).withDescription(VALID_TASKDESCRIPTION_BRAVO).build();
        assertFalse(TASK_1.equals(editedTask1));

        // different contact -> returns false
        editedTask1 = new TaskBuilder(TASK_1).withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        assertFalse(TASK_1.equals(editedTask1));
    }
}
