package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommands.DeleteBuyerCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteBuyerCommand}.
 */
public class DeleteBuyerCommandTest {

    private final Model modelForBuyers = new ModelManager(getTypicalBuyerAddressBook(), new UserPrefs());

    // Buyer List Tests
    @Test
    public void execute_validIndexUnfilteredBuyerList_success() {
        Buyer personToDelete = modelForBuyers.getFilteredBuyerList().get(INDEX_FIRST.getZeroBased());
        DeleteBuyerCommand deleteBuyerCommand = new DeleteBuyerCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_BUYER_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForBuyers.getAddressBook(), new UserPrefs());
        expectedModel.deleteBuyer(personToDelete);

        assertCommandSuccess(deleteBuyerCommand, modelForBuyers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredBuyerList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForBuyers.getFilteredBuyerList().size() + 1);
        DeleteBuyerCommand deleteBuyerCommand = new DeleteBuyerCommand(outOfBoundIndex);

        assertCommandFailure(deleteBuyerCommand, modelForBuyers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredBuyerList_success() {
        showBuyerAtIndex(modelForBuyers, INDEX_FIRST);

        Buyer personToDelete = modelForBuyers.getFilteredBuyerList().get(INDEX_FIRST.getZeroBased());
        DeleteBuyerCommand deleteBuyerCommand = new DeleteBuyerCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_BUYER_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForBuyers.getAddressBook(), new UserPrefs());
        expectedModel.deleteBuyer(personToDelete);
        showNoBuyer(expectedModel);

        assertCommandSuccess(deleteBuyerCommand, modelForBuyers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredBuyerList_throwsCommandException() {
        showBuyerAtIndex(modelForBuyers, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelForBuyers.getAddressBook().getBuyerList().size());

        DeleteBuyerCommand deleteBuyerCommand = new DeleteBuyerCommand(outOfBoundIndex);

        assertCommandFailure(deleteBuyerCommand, modelForBuyers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_buyer() {
        DeleteBuyerCommand deleteFirstCommand = new DeleteBuyerCommand(INDEX_FIRST);
        DeleteBuyerCommand deleteSecondCommand = new DeleteBuyerCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBuyerCommand deleteFirstCommandCopy = new DeleteBuyerCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no buyers.
     */
    private void showNoBuyer(Model model) {
        model.updateFilteredBuyerList(p -> false);

        assertTrue(model.getFilteredBuyerList().isEmpty());
    }
}
