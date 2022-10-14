package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TITLE_TASK_TWO;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.TASK_TWO;

import java.time.LocalDate;

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
                        .withDeadline(Deadline.of(LocalDate.of(2022, 9, 9)))
                        .build();
        assertTrue(TASK_ONE.isSameTask(editedTaskOne));

        // different title, all other attributes same -> returns false
        // TODO: Update test
        editedTaskOne = new TaskBuilder(TASK_ONE).withTitle(VALID_TITLE_TASK_TWO).build();
        assertFalse(TASK_ONE.isSameTask(editedTaskOne));

        // title differs in case, all other attributes same -> returns false
        Task editedTaskTwo = new TaskBuilder(TASK_TWO).withTitle(VALID_TITLE_TASK_TWO.toLowerCase()).build();
        assertFalse(TASK_TWO.isSameTask(editedTaskTwo));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_TASK_TWO + " ";
        editedTaskTwo = new TaskBuilder(TASK_TWO).withTitle(titleWithTrailingSpaces).build();
        assertFalse(TASK_TWO.isSameTask(editedTaskTwo));
    }
}
