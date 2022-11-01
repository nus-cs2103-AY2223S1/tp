package tracko.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.commands.CommandTestUtil.DESC_ERASER;
import static tracko.logic.commands.CommandTestUtil.DESC_PEN;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_NAME_ERASER;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_ERASER;
import static tracko.logic.commands.CommandTestUtil.VALID_ITEM_SELL_PRICE_ERASER;
import static tracko.logic.commands.CommandTestUtil.assertCommandFailure;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.logic.commands.CommandTestUtil.showItemAtIndex;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalIndexes.INDEX_SECOND;
import static tracko.testutil.TypicalItems.getTrackOWithTypicalItems;

import org.junit.jupiter.api.Test;

import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.ClearCommand;
import tracko.logic.commands.item.EditItemCommand.EditItemDescriptor;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.TrackO;
import tracko.model.UserPrefs;
import tracko.model.item.InventoryItem;
import tracko.testutil.EditItemDescriptorBuilder;
import tracko.testutil.InventoryItemBuilder;

class EditItemCommandTest {
    private Model model = new ModelManager(getTrackOWithTypicalItems(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        InventoryItem editedItem = new InventoryItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new TrackO(model.getTrackO()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        InventoryItem lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());

        InventoryItemBuilder itemInList = new InventoryItemBuilder(lastItem);
        InventoryItem editedItem = itemInList.withItemName(VALID_ITEM_NAME_ERASER)
            .withQuantity(VALID_ITEM_QUANTITY_ERASER)
                .withSellPrice(VALID_ITEM_SELL_PRICE_ERASER).build();

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_ERASER)
                .withQuantity(VALID_ITEM_QUANTITY_ERASER).withSellPrice(VALID_ITEM_SELL_PRICE_ERASER).build();
        EditItemCommand editItemCommand = new EditItemCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new TrackO(model.getTrackO()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST, new EditItemDescriptor());
        InventoryItem editedItem = model.getFilteredItemList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new TrackO(model.getTrackO()), new UserPrefs());

        assertCommandSuccess(editItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        InventoryItem firstItem = model.getFilteredItemList().get(INDEX_FIRST.getZeroBased());
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editItemCommand, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST);

        // edit item in filtered inventory list into a duplicate in the inventory list
        InventoryItem itemInList = model.getTrackO().getInventoryList().get(INDEX_SECOND.getZeroBased());
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST,
                new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editItemCommand, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_ERASER).build();
        EditItemCommand editItemCommand = new EditItemCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackO().getInventoryList().size());

        EditItemCommand editItemCommand = new EditItemCommand(outOfBoundIndex,
                new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_ERASER).build());

        assertCommandFailure(editItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditItemCommand standardCommand = new EditItemCommand(INDEX_FIRST, DESC_PEN);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditItemDescriptor(DESC_PEN);
        EditItemCommand commandWithSameValues = new EditItemCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditItemCommand(INDEX_SECOND, DESC_PEN)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditItemCommand(INDEX_FIRST, DESC_ERASER)));
    }

}
