package tracko.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.commands.CommandTestUtil.assertCommandFailure;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalIndexes.INDEX_SECOND;
import static tracko.testutil.TypicalItems.getTrackOWithTypicalItems;

import org.junit.jupiter.api.Test;

import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.CommandTestUtil;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.item.InventoryItem;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteItemCommand}.
 */
public class DeleteItemCommandTest {

    private Model model = new ModelManager(getTrackOWithTypicalItems(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InventoryItem inventoryItemToDelete = model.getInventoryList().get(INDEX_FIRST.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, inventoryItemToDelete);

        ModelManager expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.deleteItem(inventoryItemToDelete);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getInventoryList().size() + 1);
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showItemAtIndex(model, INDEX_FIRST);

        InventoryItem inventoryItemToDelete = model.getFilteredItemList().get(INDEX_FIRST.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, inventoryItemToDelete);

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.deleteItem(inventoryItemToDelete);
        showNoItem(expectedModel);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showItemAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackO().getInventoryList().size());

        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteFirstCommand = new DeleteItemCommand(INDEX_FIRST);
        DeleteItemCommand deleteSecondCommand = new DeleteItemCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteItemCommand deleteFirstCommandCopy = new DeleteItemCommand(INDEX_FIRST);
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
    private void showNoItem(Model model) {
        model.updateFilteredItemList(p -> false);

        assertTrue(model.getFilteredItemList().isEmpty());
    }
}
