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
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_EXAM_LINK_DROPPED;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        int taskListSizePlusOne = model.getFilteredTaskList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(taskListSizePlusOne);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        String expectedMessage =
                String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, taskListSizePlusOne);

        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        String expectedMessage =
                String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_linkedTaskUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_LINKED_TASK);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete)
                + MESSAGE_EXAM_LINK_DROPPED;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskFirstCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        DeleteTaskCommand deleteTaskSecondCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteTaskFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTaskFirstCommand.equals(null));

        // different task index -> returns false
        assertFalse(deleteTaskFirstCommand.equals(deleteTaskSecondCommand));
    }



    private void showNoTask(Model model) {
        model.updateFilteredTaskList(t -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
