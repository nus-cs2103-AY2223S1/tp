package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

public class RemoveTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_validIndexTaskList_success() {
        Task taskToDelete = model.getTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        RemoveTaskCommand deleteCommand = new RemoveTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(RemoveTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexTaskList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTaskList().size() + 1);
        RemoveTaskCommand deleteCommand = new RemoveTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_INDEX);
    }

    @Test
    public void equals() {
        RemoveTaskCommand deleteFirstCommand = new RemoveTaskCommand(INDEX_FIRST_TASK);
        RemoveTaskCommand deleteSecondCommand = new RemoveTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RemoveTaskCommand deleteFirstCommandCopy = new RemoveTaskCommand(INDEX_FIRST_TASK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
