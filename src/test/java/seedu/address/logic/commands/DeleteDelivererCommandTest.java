package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDelivererAtIndex;
import static seedu.address.testutil.TypicalDeliverers.getTypicalDelivererAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Deliverer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDelivererCommand}.
 */
public class DeleteDelivererCommandTest {

    private Model modelForDeliverers = new ModelManager(getTypicalDelivererAddressBook(), new UserPrefs());

    // Deliverer List Tests
    @Test
    public void execute_validIndexUnfilteredDelivererList_success() {
        Deliverer personToDelete = modelForDeliverers.getFilteredDelivererList().get(INDEX_FIRST.getZeroBased());
        DeleteDelivererCommand deleteDelivererCommand = new DeleteDelivererCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDelivererCommand.MESSAGE_DELETE_DELIVERER_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForDeliverers.getAddressBook(), new UserPrefs());
        expectedModel.deleteDeliverer(personToDelete);

        assertCommandSuccess(deleteDelivererCommand, modelForDeliverers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredDelivererList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForDeliverers.getFilteredDelivererList().size() + 1);
        DeleteDelivererCommand deleteDelivererCommand = new DeleteDelivererCommand(outOfBoundIndex);

        assertCommandFailure(deleteDelivererCommand, modelForDeliverers,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredDelivererList_success() {
        showDelivererAtIndex(modelForDeliverers, INDEX_FIRST);

        Deliverer personToDelete = modelForDeliverers.getFilteredDelivererList().get(INDEX_FIRST.getZeroBased());
        DeleteDelivererCommand deleteDelivererCommand = new DeleteDelivererCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDelivererCommand.MESSAGE_DELETE_DELIVERER_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForDeliverers.getAddressBook(), new UserPrefs());
        expectedModel.deleteDeliverer(personToDelete);
        showNoDeliverer(expectedModel);

        assertCommandSuccess(deleteDelivererCommand, modelForDeliverers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredDelivererList_throwsCommandException() {
        showDelivererAtIndex(modelForDeliverers, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < modelForDeliverers.getAddressBook().getDelivererList().size());

        DeleteDelivererCommand deleteDelivererCommand = new DeleteDelivererCommand(outOfBoundIndex);

        assertCommandFailure(deleteDelivererCommand, modelForDeliverers,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_deliverer() {
        DeleteDelivererCommand deleteFirstCommand = new DeleteDelivererCommand(INDEX_FIRST);
        DeleteDelivererCommand deleteSecondCommand = new DeleteDelivererCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDelivererCommand deleteFirstCommandCopy = new DeleteDelivererCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no Deliverers.
     */
    private void showNoDeliverer(Model model) {
        model.updateFilteredDelivererList(p -> false);

        assertTrue(model.getFilteredDelivererList().isEmpty());
    }
}
