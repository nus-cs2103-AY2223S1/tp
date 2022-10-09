package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalItems.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.EditItemDescriptorBuilder;
import seedu.address.testutil.ItemBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());

        ItemBuilder personInList = new ItemBuilder(lastItem);
        Item editedItem = personInList
            .withItemName(VALID_ITEM_NAME_CUCUMBERS)
            .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
            .build();

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withItemName(VALID_ITEM_QUANTITY_CUCUMBERS)
            .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
            .build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditItemDescriptor());
        Item editedItem = model.getFilteredItemList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showItemAtIndex(model, INDEX_FIRST_PERSON);

        Item personInFilteredList = model.getFilteredItemList().get(INDEX_FIRST_PERSON.getZeroBased());
        Item editedItem = new ItemBuilder(personInFilteredList).withItemName(VALID_ITEM_NAME_CUCUMBERS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_CUCUMBERS).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Item firstItem = model.getFilteredItemList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Item personInList = model.getAddressBook().getItemList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditItemDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withItemName(VALID_ITEM_NAME_CUCUMBERS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getItemList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_CUCUMBERS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_POTATOES);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditItemDescriptor(DESC_POTATOES);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_POTATOES)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_CUCUMBERS)));
    }

}
