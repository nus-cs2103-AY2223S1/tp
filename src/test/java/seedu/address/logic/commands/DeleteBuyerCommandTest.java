package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.buyer.DeleteBuyerCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteBuyerCommandTest {

    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Buyer buyerToDelete = model.getFilteredBuyerList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_BUYER_SUCCESS, buyerToDelete);

        ModelManager expectedModel = new ModelManager(
                model.getBuyerBook(), model.getPropertyBook(), new UserPrefs());
        expectedModel.deleteBuyer(buyerToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //
    //        Buyer buyerToDelete = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
    //        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(INDEX_FIRST_ITEM);
    //
    //        String expectedMessage = String.format(DeleteBuyerCommand.MESSAGE_DELETE_PERSON_SUCCESS, buyerToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getPersonModel(), model.getPropertyModel(), new UserPrefs());
    //        expectedModel.deletePerson(buyerToDelete);
    //        showNoPerson(expectedModel);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //
    //        Index outOfBoundIndex = INDEX_SECOND_ITEM;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getPersonModel().getPersonList().size());
    //
    //        DeleteBuyerCommand deleteCommand = new DeleteBuyerCommand(outOfBoundIndex);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        DeleteBuyerCommand deleteFirstCommand = new DeleteBuyerCommand(INDEX_FIRST_ITEM);
        DeleteBuyerCommand deleteSecondCommand = new DeleteBuyerCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBuyerCommand deleteFirstCommandCopy = new DeleteBuyerCommand(INDEX_FIRST_ITEM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different buyer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered buyer list to show no one.
     */
    private void showNoBuyer(Model model) {
        model.updateFilteredBuyerList(p -> false);

        assertTrue(model.getFilteredBuyerList().isEmpty());
    }
}
