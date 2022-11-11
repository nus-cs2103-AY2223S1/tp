package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTeammateAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.teammate.Teammate;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListNoTaskChange_success() {
        Teammate teammateToDelete = model.getFilteredTeammateList().get(INDEX_SECOND_TEAMMATE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_TEAMMATE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TEAMMATE_SUCCESS, teammateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.deleteTeammate(teammateToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeammateList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredListNoTaskChange_success() {
        showTeammateAtIndex(model, INDEX_SECOND_TEAMMATE);

        Teammate teammateToDelete = model.getFilteredTeammateList().get(INDEX_FIRST_TEAMMATE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TEAMMATE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TEAMMATE_SUCCESS, teammateToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.deleteTeammate(teammateToDelete);
        showNoTeammate(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskAssignedContactCascadeDeleted_success() {
        Teammate teammateToDelete = model.getFilteredTeammateList().get(INDEX_FIRST_TEAMMATE.getZeroBased());
        Task editedTask = new TaskBuilder(model.getFilteredTaskList().get(0)).withContacts().build();
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TEAMMATE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TEAMMATE_SUCCESS, teammateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.deleteTeammate(teammateToDelete);
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTeammateAtIndex(model, INDEX_FIRST_TEAMMATE);

        Index outOfBoundIndex = INDEX_SECOND_TEAMMATE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTeammateList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_TEAMMATE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_TEAMMATE);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different teammate -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTeammate(Model model) {
        model.updateFilteredTeammateList(p -> false);

        assertTrue(model.getFilteredTeammateList().isEmpty());
    }
}
