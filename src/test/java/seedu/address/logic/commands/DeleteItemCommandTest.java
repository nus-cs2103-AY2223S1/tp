package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSupplyItemAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.SupplyItem;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteItemCommandTest {

    private final Model model = new ModelManager(new AddressBook(), new UserPrefs(),
            getTypicalTaskList(), getTypicalInventory());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        SupplyItem supplyItemToDelete = model.getFilteredSupplyItemList().get(INDEX_FIRST_SUPPLY_ITEM.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST_SUPPLY_ITEM);

        String expectedMessage = String.format(DeleteItemCommand
                .MESSAGE_DELETE_SUPPLY_ITEM_SUCCESS, supplyItemToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel.deleteSupplyItem(INDEX_FIRST_SUPPLY_ITEM);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSupplyItemList().size() + 1);
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteItemCommand, model, Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSupplyItemAtIndex(model, INDEX_FIRST_SUPPLY_ITEM);

        SupplyItem supplyItemToDelete = model.getFilteredSupplyItemList().get(INDEX_FIRST_SUPPLY_ITEM.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST_SUPPLY_ITEM);

        String expectedMessage = String.format(DeleteItemCommand
                .MESSAGE_DELETE_SUPPLY_ITEM_SUCCESS, supplyItemToDelete);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel.deleteSupplyItem(INDEX_FIRST_SUPPLY_ITEM);
        showNoSupplyItem(expectedModel);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSupplyItemAtIndex(model, INDEX_FIRST_SUPPLY_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_SUPPLY_ITEM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInventory().getSupplyItems().size());

        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteItemCommand, model, Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteItemFirstCommand = new DeleteItemCommand(INDEX_FIRST_SUPPLY_ITEM);
        DeleteItemCommand deleteItemSecondCommand = new DeleteItemCommand(INDEX_SECOND_SUPPLY_ITEM);

        // same object -> returns true
        assertTrue(deleteItemFirstCommand.equals(deleteItemFirstCommand));

        // same values -> returns true
        DeleteItemCommand deleteItemFirstCommandCopy = new DeleteItemCommand(INDEX_FIRST_SUPPLY_ITEM);
        assertTrue(deleteItemFirstCommand.equals(deleteItemFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteItemFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteItemFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteItemFirstCommand.equals(deleteItemSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSupplyItem(Model model) {
        model.updateFilteredSupplyItemList(p -> false);

        assertTrue(model.getFilteredSupplyItemList().isEmpty());
    }
}
