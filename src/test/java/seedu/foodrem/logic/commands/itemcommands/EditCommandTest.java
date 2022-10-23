package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.logic.commands.CommandTestUtil.showItemAtIndex;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.TypicalFoodRem;
import seedu.foodrem.testutil.TypicalIndexes;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final String EXPECTED_SUCCESS_FORMAT = "Edited Item: %1$s";

    private final Model model = new ModelManager(TypicalFoodRem.getTypicalFoodRem(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EXPECTED_SUCCESS_FORMAT, editedItem);

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());

        ItemBuilder itemInList = new ItemBuilder(lastItem);
        Item editedItem = itemInList
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)
                .build();

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)
                .build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EXPECTED_SUCCESS_FORMAT, editedItem);

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM, new EditItemDescriptor());
        Item editedItem = model.getFilteredItemList().get(TypicalIndexes.INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EXPECTED_SUCCESS_FORMAT, editedItem);

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showItemAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);

        Item itemInFilteredList = model.getFilteredItemList().get(TypicalIndexes.INDEX_FIRST_ITEM.getZeroBased());
        Item editedItem = new ItemBuilder(itemInFilteredList)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)
                .build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM,
                new EditItemDescriptorBuilder()
                        .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS)
                        .build());

        String expectedMessage = String.format(EXPECTED_SUCCESS_FORMAT, editedItem);

        Model expectedModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Item firstItem = model.getFilteredItemList().get(TypicalIndexes.INDEX_FIRST_ITEM.getZeroBased());
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in foodRem
        Item itemInList = model.getFoodRem().getItemList().get(TypicalIndexes.INDEX_SECOND_ITEM.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM,
                new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of foodRem
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, TypicalIndexes.INDEX_FIRST_ITEM);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of FoodRem list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodRem().getItemList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditItemDescriptorBuilder().withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM,
                CommandTestUtil.DESC_POTATOES);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditItemDescriptor(CommandTestUtil.DESC_POTATOES);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ResetCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(TypicalIndexes.INDEX_SECOND_ITEM,
                CommandTestUtil.DESC_POTATOES));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM,
                CommandTestUtil.DESC_CUCUMBERS));
    }

}
