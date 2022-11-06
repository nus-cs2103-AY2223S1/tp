package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_CONTACT_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_DEADLINE_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_PROJECT_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TITLE_WORKSHOP;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.TASK_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_ONE.isSameTask(TASK_ONE));

        // null -> returns false
        assertFalse(TASK_ONE.isSameTask(null));

        // same title, all other attributes different -> returns true
        Task editedTaskOne =
                new TaskBuilder(TASK_ONE)
                        .withContacts("Kloppo")
                        .withDeadline("2 January 2023")
                        .build();
        assertTrue(TASK_ONE.isSameTask(editedTaskOne));

        // different title, all other attributes same -> returns false
        editedTaskOne = new TaskBuilder(TASK_ONE).withTitle(VALID_TITLE_WORKSHOP).build();
        assertFalse(TASK_ONE.isSameTask(editedTaskOne));

        // title differs in case, all other attributes same -> returns false
        Task editedTaskTwo = new TaskBuilder(TASK_TWO).withTitle(VALID_TITLE_WORKSHOP.toLowerCase()).build();
        assertFalse(TASK_TWO.isSameTask(editedTaskTwo));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_WORKSHOP + " ";
        editedTaskTwo = new TaskBuilder(TASK_TWO).withTitle(titleWithTrailingSpaces).build();
        assertFalse(TASK_TWO.isSameTask(editedTaskTwo));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task taskOneCopy = new TaskBuilder(TASK_ONE).build();
        assertEquals(TASK_ONE, taskOneCopy);

        // same object -> returns true
        assertEquals(TASK_ONE, TASK_ONE);
        assertEquals(TASK_ONE.hashCode(), TASK_ONE.hashCode());

        // null -> returns false
        assertNotEquals(null, TASK_ONE);

        // different type -> returns false
        assertNotEquals(5, TASK_ONE);

        // different task -> returns false
        assertNotEquals(TASK_ONE, TASK_TWO);

        // different title -> returns false
        Task editedTaskOne = new TaskBuilder(TASK_ONE).withTitle(VALID_TITLE_WORKSHOP).build();
        assertNotEquals(TASK_ONE, editedTaskOne);

        // different status -> return false
        editedTaskOne = new TaskBuilder(TASK_ONE).withCompleted(true).build();
        assertNotEquals(TASK_ONE, editedTaskOne);

        // different deadline -> returns false
        editedTaskOne = new TaskBuilder(TASK_ONE).withDeadline(VALID_DEADLINE_WORKSHOP).build();
        assertNotEquals(TASK_ONE, editedTaskOne);

        // different project -> returns false
        editedTaskOne = new TaskBuilder(TASK_ONE).withProject(VALID_PROJECT_WORKSHOP).build();
        assertNotEquals(TASK_ONE, editedTaskOne);

        // different assigned contacts -> returns false
        editedTaskOne = new TaskBuilder(TASK_ONE).withContacts(VALID_CONTACT_WORKSHOP).build();
        assertNotEquals(TASK_ONE, editedTaskOne);
    }
}
