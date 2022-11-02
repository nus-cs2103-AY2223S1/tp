package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.buyer.EditBuyerCommand;
import seedu.address.logic.commands.buyer.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditBuyerCommandTest {

    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Buyer currentBuyer = model.getFilteredPersonList().get(0);
    //        Buyer editedBuyer = new BuyerBuilder().withPriceRange("20 - 50")
    //        .withDesiredCharacteristics("Clean").build();
    //
    //        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(editedBuyer).build();
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, descriptor);
    //
    //        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);
    //
    //        Model expectedModel = new ModelManager(new BuyerBook(
    //                model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());
    //
    //        expectedModel.setPerson(currentBuyer, editedBuyer);
    //
    //        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    //        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
    //        Buyer lastBuyer = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
    //
    //        BuyerBuilder personInList = new BuyerBuilder(lastBuyer);
    //        Buyer editedBuyer = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //                .withPriority(VALID_PRIORITY_LOW).build();
    //
    //        EditBuyerCommand.EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder()
    //        .withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withPriority(VALID_PRIORITY_LOW).build();
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(indexLastPerson, descriptor);
    //
    //        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);
    //
    //        Model expectedModel = new ModelManager(
    //                new BuyerBook(model.getPersonModel()), new PropertyBook(model.getPropertyModel()),
    //                new UserPrefs());
    //        expectedModel.setPerson(lastBuyer, editedBuyer);
    //
    //        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_noFieldSpecifiedUnfilteredList_success() {
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, new EditBuyerDescriptor());
    //        Buyer editedBuyer = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
    //
    //        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);
    //
    //        Model expectedModel = new ModelManager(new BuyerBook(
    //                model.getPersonModel()), new PropertyBook(model.getPropertyModel()), new UserPrefs());
    //
    //        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_filteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //
    //        Buyer buyerInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
    //        Buyer editedBuyer = new BuyerBuilder(buyerInFilteredList).withName(VALID_NAME_BOB).build();
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM,
    //                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer);
    //
    //        Model expectedModel = new ModelManager(
    //                new BuyerBook(model.getPersonModel()), new PropertyBook(model.getPropertyModel()),
    //                new UserPrefs());
    //        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedBuyer);
    //
    //        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Buyer firstBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditBuyerCommand.EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(firstBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_BUYER);
    }

    //    @Test
    //    public void execute_duplicatePersonFilteredList_failure() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //
    //        // edit buyer in filtered list into a duplicate in address book
    //        Buyer buyerInList = model.getPersonModel().getPersonList().get(INDEX_SECOND_ITEM.getZeroBased());
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_ITEM,
    //                new EditBuyerDescriptorBuilder(buyerInList).build());
    //
    //        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_PERSON);
    //    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        EditBuyerCommand.EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of person book
     */
    //    @Test
    //    public void execute_invalidPersonIndexFilteredList_failure() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //        Index outOfBoundIndex = INDEX_SECOND_ITEM;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getPersonModel().getPersonList().size());
    //
    //        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex,
    //                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        final EditBuyerCommand standardCommand = new EditBuyerCommand(INDEX_FIRST_ITEM, DESC_AMY);

        // same values -> returns true
        EditBuyerDescriptor copyDescriptor = new EditBuyerDescriptor(DESC_AMY);
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
