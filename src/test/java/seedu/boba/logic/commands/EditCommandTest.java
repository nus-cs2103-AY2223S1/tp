package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.commons.core.index.Index;
import seedu.boba.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.boba.model.*;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.testutil.CustomerBuilder;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the BobaBotModel) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void execute_phoneAllFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = new CustomerBuilder().build();
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(editedCustomer).build();
        EditCommand editCommand = new EditCommand(PHONE_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_emailAllFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = new CustomerBuilder().build();
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(editedCustomer).build();
        EditCommand editCommand = new EditCommand(EMAIL_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(bobaBotModel.getFilteredPersonList().size());
        Customer lastCustomer = bobaBotModel.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        CustomerBuilder personInList = new CustomerBuilder(lastCustomer);
        Customer editedCustomer = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_GOLD).build();

        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_GOLD).build();
        EditCommand editCommand = new EditCommand(lastCustomer.getPhone(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(lastCustomer, editedCustomer);

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_phoneNoFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(PHONE_FIRST_PERSON, new EditPersonDescriptor());
        Customer editedCustomer = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_emailNoFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(EMAIL_FIRST_PERSON, new EditPersonDescriptor());
        Customer editedCustomer = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_phoneFilteredList_success() {
        showPersonAtIndex(bobaBotModel, INDEX_FIRST_PERSON);

        Customer customerInFilteredList = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(PHONE_FIRST_PERSON,
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_emailFilteredList_success() {
        showPersonAtIndex(bobaBotModel, INDEX_FIRST_PERSON);

        Customer customerInFilteredList = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(EMAIL_FIRST_PERSON,
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_phoneDuplicatePersonUnfilteredList_failure() {
        Customer firstCustomer = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(firstCustomer).build();
        EditCommand editCommand = new EditCommand(PHONE_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, bobaBotModel, EditCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_emailDuplicatePersonUnfilteredList_failure() {
        Customer firstCustomer = bobaBotModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(firstCustomer).build();
        EditCommand editCommand = new EditCommand(EMAIL_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, bobaBotModel, EditCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_phoneInvalidPersonUnfilteredList_failure() {
        Phone outOfBoundPhone = new Phone("00000000");
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundPhone, descriptor);

        assertCommandFailure(editCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_emailInvalidPersonUnfilteredList_failure() {
        Email outOfBoundEmail = new Email("test@test.com");
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundEmail, descriptor);

        assertCommandFailure(editCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(PHONE_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(PHONE_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different phone -> returns false
        assertFalse(standardCommand.equals(new EditCommand(PHONE_SECOND_PERSON, DESC_AMY)));

        // different email -> returns false
        assertFalse(standardCommand.equals(new EditCommand(EMAIL_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(PHONE_FIRST_PERSON, DESC_BOB)));
    }

}
