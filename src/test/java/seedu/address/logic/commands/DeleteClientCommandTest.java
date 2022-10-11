package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteClientCommand}.
 */
public class DeleteClientCommandTest {

    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_ELEMENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        ModelManager expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);

        assertCommandSuccess(deleteClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_ELEMENT);

        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_ELEMENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        Model expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);
        showNoClient(expectedModel);

        assertCommandSuccess(deleteClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_ELEMENT);

        Index outOfBoundIndex = INDEX_SECOND_ELEMENT;
        // ensures that outOfBoundIndex is still in bounds of client list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyInsuRec().getClientList().size());

        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteClientCommand deleteFirstCommand = new DeleteClientCommand(INDEX_FIRST_ELEMENT);
        DeleteClientCommand deleteSecondCommand = new DeleteClientCommand(INDEX_SECOND_ELEMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteClientCommand deleteFirstCommandCopy = new DeleteClientCommand(INDEX_FIRST_ELEMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoClient(Model model) {
        model.updateFilteredClientList(p -> false);

        assertTrue(model.getFilteredClientList().isEmpty());
    }
}
