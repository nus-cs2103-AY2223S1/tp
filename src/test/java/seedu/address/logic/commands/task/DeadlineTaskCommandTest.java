package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

public class DeadlineTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeadlineTaskCommand command = new DeadlineTaskCommand(outOfBoundTaskIndex, Deadline.of(LocalDate.now()));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validTask_commandSuccessful() throws Exception {
        Index taskIndex = INDEX_FIRST_TASK;
        DeadlineTaskCommand command = new DeadlineTaskCommand(taskIndex, Deadline.of(LocalDate.now()));

        CommandResult commandResult = command.execute(model);

        Task updatedTask = model.getFilteredTaskList().get(taskIndex.getZeroBased());

        assertEquals(
                String.format(DeadlineTaskCommand.MESSAGE_SUCCESS, updatedTask.getDeadline(), updatedTask.getTitle()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DeadlineTaskCommand firstCommand = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(today));
        DeadlineTaskCommand secondCommand = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(tomorrow));
        DeadlineTaskCommand thirdCommand = new DeadlineTaskCommand(INDEX_SECOND_TASK, Deadline.of(today));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeadlineTaskCommand firstCommandCopy = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(today));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different task -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different assigned contacts -> returns false
        assertFalse(firstCommand.equals(thirdCommand));
    }

}
