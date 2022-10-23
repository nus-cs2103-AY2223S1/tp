package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class AddItemCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList(), new Inventory());

    @Test
    public void execute_addItemFromSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        AddItemCommand addItemCommand = new AddItemCommand(indexLastPerson, 5, 4);
        SupplyItem toAdd = new SupplyItem(lastPerson.getItem().toString(), 5, 4, lastPerson, getTagSet("Item"));

        String expectedMessage = String.format(AddItemCommand.MESSAGE_SUCCESS, toAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), new Inventory());
        expectedModel.addSupplyItem(toAdd);
        assertCommandSuccess(addItemCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_addItemFromFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person personInList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        AddItemCommand addItemCommand = new AddItemCommand(INDEX_FIRST_PERSON, 5, 4);
        SupplyItem toAdd = new SupplyItem(personInList.getItem().toString(), 5, 4, personInList, getTagSet("Item"));

        String expectedMessage = String.format(AddItemCommand.MESSAGE_SUCCESS, toAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), new Inventory());
        expectedModel.addSupplyItem(toAdd);
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(addItemCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_existingItemSpecifiedUnfilteredList_failure() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        SupplyItem existingItem = new SupplyItem(lastPerson.getItem().toString(), 5, 4, lastPerson, getTagSet("Item"));
        model.addSupplyItem(existingItem);

        AddItemCommand addItemCommand = new AddItemCommand(indexLastPerson, 5, 4);

        assertCommandFailure(addItemCommand, model, AddItemCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }
    @Test
    public void execute_existingItemFilteredList_failure() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        showPersonAtIndex(model, indexLastPerson);

        Person lastPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SupplyItem existingItem = new SupplyItem(lastPerson.getItem().toString(), 5, 4, lastPerson, getTagSet("Item"));
        model.addSupplyItem(existingItem);

        AddItemCommand addItemCommand = new AddItemCommand(INDEX_FIRST_PERSON, 5, 4);

        assertCommandFailure(addItemCommand, model, AddItemCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }

    @Test
    public void execute_duplicateItemNameSpecifiedUnfilteredList_failure() {
        Index indexThirdPerson = Index.fromOneBased(3);
        Person thirdPerson = model.getFilteredPersonList().get(indexThirdPerson.getZeroBased());

        SupplyItem existingItem = new SupplyItem(thirdPerson.getItem().toString(),
                5, 4, thirdPerson, getTagSet("Item"));
        model.addSupplyItem(existingItem);

        AddItemCommand addItemCommand = new AddItemCommand(INDEX_SECOND_PERSON, 5, 4);

        assertCommandFailure(addItemCommand, model, AddItemCommand.MESSAGE_DUPLICATE_SUPPLYITEM);
    }

    @Test
    public void execute_duplicateItemNameFilteredList_failure() {
        Index indexThirdPerson = Index.fromOneBased(3);

        Person thirdPerson = model.getFilteredPersonList().get(indexThirdPerson.getZeroBased());
        SupplyItem existingItem = new SupplyItem(thirdPerson.getItem().toString(),
                5, 4, thirdPerson, getTagSet("Item"));
        model.addSupplyItem(existingItem);

        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        AddItemCommand addItemCommand = new AddItemCommand(INDEX_FIRST_PERSON, 5, 4);

        assertCommandFailure(addItemCommand, model, AddItemCommand.MESSAGE_DUPLICATE_SUPPLYITEM);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddItemCommand addItemCommand = new AddItemCommand(outOfBoundIndex, 5, 4);

        assertCommandFailure(addItemCommand, model, Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddItemCommand addItemCommand = new AddItemCommand(outOfBoundIndex, 5, 4);

        assertCommandFailure(addItemCommand, model, Messages.MESSAGE_INVALID_SUPPLYITEM_DISPLAYED_INDEX);
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    @Test
    public void equals() {
        final AddItemCommand standardCommand = new AddItemCommand(INDEX_FIRST_PERSON, 5, 4);

        // same values -> returns true
        int curr = 5;
        int min = 4;
        AddItemCommand commandWithSameValues = new AddItemCommand(INDEX_FIRST_PERSON, curr, min);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddItemCommand(INDEX_SECOND_PERSON, 5, 4)));

        // different currentStock -> returns false
        assertFalse(standardCommand.equals(new AddItemCommand(INDEX_FIRST_PERSON, 4, 4)));

        // different minimumStock -> returns false
        assertFalse(standardCommand.equals(new AddItemCommand(INDEX_FIRST_PERSON, 5, 2)));

    }

}
