package modtrekt.logic.commands;

import static modtrekt.logic.commands.CommandTestUtil.assertCommandFailure;
import static modtrekt.logic.commands.CommandTestUtil.assertCommandSuccess;
import static modtrekt.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static modtrekt.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static modtrekt.testutil.TypicalModules.getTypicalModuleList;
import static modtrekt.testutil.TypicalTasks.getTypicalTaskBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.Messages;
import modtrekt.commons.core.index.Index;
import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.UserPrefs;
import modtrekt.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveCommand}.
 */
public class RemoveTaskCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(RemoveTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getTaskBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(removeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(outOfBoundIndex);

        assertCommandFailure(removeTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
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

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
