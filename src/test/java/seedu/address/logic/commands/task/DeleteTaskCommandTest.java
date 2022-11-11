package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class DeleteTaskCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null));
    }

    @Test
    public void execute_validTaskIndexAccepted() {
        Index targetIndex = Index.fromOneBased(1);
        Task taskToDelete = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(targetIndex);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS,
            taskToDelete.getTitle(),
            taskToDelete.getDeadline(),
            taskToDelete.getProject(),
            taskToDelete.getAssignedContacts());
        ModelManager expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskPanel(model.getTaskPanel()), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different teammate -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

}
