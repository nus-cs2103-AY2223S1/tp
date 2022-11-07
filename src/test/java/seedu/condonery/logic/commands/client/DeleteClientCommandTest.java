package seedu.condonery.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.Client;

public class DeleteClientCommandTest {
    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        DeleteClientCommand deleteCommand = new DeleteClientCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        ModelManager expectedModel = new ModelManager(model.getPropertyDirectory(),
                model.getClientDirectory(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        DeleteClientCommand deleteCommand = new DeleteClientCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST);

        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        DeleteClientCommand deleteCommand = new DeleteClientCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        Model expectedModel = new ModelManager(model.getPropertyDirectory(),
                model.getClientDirectory(), new UserPrefs());
        expectedModel.deleteClient(clientToDelete);
        showNoClient(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientDirectory().getClientList().size());

        DeleteClientCommand deleteCommand = new DeleteClientCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteClientCommand deleteFirstCommand = new DeleteClientCommand(INDEX_FIRST);
        DeleteClientCommand deleteSecondCommand = new DeleteClientCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteClientCommand deleteFirstCommandCopy = new DeleteClientCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
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
