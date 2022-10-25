package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.BuyerCommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditBuyerCommandTest {

    private Model model = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Buyer currentBuyer = model.getFilteredPersonList().get(0);
        Buyer editedBuyer = new PersonBuilder().withPriceRange("20 - 50").withDesiredCharacteristics("Clean").build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(new PersonBook(
                model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());

        expectedModel.setPerson(currentBuyer, editedBuyer);

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Buyer lastBuyer = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastBuyer);
        Buyer editedBuyer = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withPriority(VALID_PRIORITY_LOW).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withPriority(VALID_PRIORITY_LOW).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(
                new PersonBook(model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());
        expectedModel.setPerson(lastBuyer, editedBuyer);

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, new EditPersonDescriptor());
        Buyer editedBuyer = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(new PersonBook(
                model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);

        Buyer buyerInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        Buyer editedBuyer = new PersonBuilder(buyerInFilteredList).withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);

        Model expectedModel = new ModelManager(
                new PersonBook(model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedBuyer);

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Buyer firstBuyer = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);

        // edit buyer in filtered list into a duplicate in address book
        Buyer buyerInList = model.getPersonModel().getPersonList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM,
                new EditPersonDescriptorBuilder(buyerInList).build());

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPersonModel().getPersonList().size());

        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBuyerCommand standardCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditBuyerCommand commandWithSameValues = new EditBuyerCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_SECOND_ITEM, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_FIRST_ITEM, DESC_BOB)));
    }

}
