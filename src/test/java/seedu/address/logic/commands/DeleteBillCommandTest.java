package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBillAtIndex;
import static seedu.address.testutil.TypicalBills.getTypicalBillsHealthContact;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BILL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BILL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Bill;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteBillCommand}.
 */
public class DeleteBillCommandTest {

    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Bill billToDelete = model.getFilteredBillList()
                .get(INDEX_FIRST_BILL.getZeroBased());
        DeleteBillCommand deleteBillCommand = new DeleteBillCommand(INDEX_FIRST_BILL);

        String expectedMessage = String.format(DeleteBillCommand
                .MESSAGE_DELETE_BILL_SUCCESS, billToDelete);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.deleteBill(billToDelete);

        assertCommandSuccess(deleteBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBillList().size() + 1);
        DeleteBillCommand deleteBillCommand = new DeleteBillCommand(outOfBoundIndex);

        assertCommandFailure(deleteBillCommand, model, Messages.MESSAGE_INVALID_BILL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBillAtIndex(model, INDEX_FIRST_BILL);

        Bill billToDelete = model.getFilteredBillList()
                .get(INDEX_FIRST_BILL.getZeroBased());
        DeleteBillCommand deleteBillCommand = new DeleteBillCommand(INDEX_FIRST_BILL);

        String expectedMessage = String.format(DeleteBillCommand
                .MESSAGE_DELETE_BILL_SUCCESS, billToDelete);

        Model expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.deleteBill(billToDelete);
        showNoBill(expectedModel);

        assertCommandSuccess(deleteBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBillAtIndex(model, INDEX_FIRST_BILL);

        Index outOfBoundIndex = INDEX_SECOND_BILL;
        // ensures that outOfBoundIndex is still in bounds of HealthContact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHealthContact().getBillList().size());

        DeleteBillCommand deleteBillCommand = new DeleteBillCommand(outOfBoundIndex);

        assertCommandFailure(deleteBillCommand, model, Messages.MESSAGE_INVALID_BILL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBillCommand deleteFirstCommand = new DeleteBillCommand(INDEX_FIRST_BILL);
        DeleteBillCommand deleteSecondCommand = new DeleteBillCommand(INDEX_SECOND_BILL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBillCommand deleteFirstCommandCopy = new DeleteBillCommand(INDEX_FIRST_BILL);
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
    private void showNoBill(Model model) {
        model.updateFilteredBillList(p -> false);

        assertTrue(model.getFilteredBillList().isEmpty());
    }
}
