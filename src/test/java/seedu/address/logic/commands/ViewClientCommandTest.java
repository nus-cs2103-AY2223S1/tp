package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
 * {@code ViewClientCommand}.
 */
public class ViewClientCommandTest {
    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToView = model.getFilteredClientList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        ViewClientCommand viewCommand = new ViewClientCommand(INDEX_FIRST_ELEMENT);

        String expectedMessage = String.format(viewCommand.MESSAGE_SUCCESS, clientToView);
        ModelManager expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ViewClientCommand viewCommand = new ViewClientCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewClientCommand viewFirstCommand = new ViewClientCommand(INDEX_FIRST_ELEMENT);
        ViewClientCommand viewSecondCommand = new ViewClientCommand(INDEX_SECOND_ELEMENT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewClientCommand viewFirstCommandCopy = new ViewClientCommand(INDEX_FIRST_ELEMENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
