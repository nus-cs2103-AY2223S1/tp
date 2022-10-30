package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.ALICE_DECREMENT;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.model.*;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;

/**
 * Contains integration tests (interaction with the BobaBotModel) and unit tests for IncreaseCommand.
 */
public class DecreaseCommandTest {

    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void execute_phoneAllFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = ALICE_DECREMENT;
        DecreaseCommand decreaseCommand = new DecreaseCommand(PHONE_FIRST_PERSON, "100");

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);
        assertCommandSuccess(decreaseCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_emailAllFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = ALICE_DECREMENT;
        DecreaseCommand decreaseCommand = new DecreaseCommand(EMAIL_FIRST_PERSON, "100");

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer);

        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(new BobaBot(bobaBotModel.getBobaBot()), new UserPrefs());
        expectedBobaBotModel.setPerson(bobaBotModel.getFilteredPersonList().get(0), editedCustomer);

        assertCommandSuccess(decreaseCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_phoneInvalidPersonUnfilteredList_failure() {
        Phone outOfBoundPhone = new Phone("00000000");
        DecreaseCommand increaseCommand = new DecreaseCommand(outOfBoundPhone, "100");

        assertCommandFailure(increaseCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_emailInvalidPersonUnfilteredList_failure() {
        Email outOfBoundEmail = new Email("test@test.com");
        DecreaseCommand increaseCommand = new DecreaseCommand(outOfBoundEmail, "100");

        assertCommandFailure(increaseCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void equals() {
        final DecreaseCommand standardCommand = new DecreaseCommand(PHONE_FIRST_PERSON, "100");

        // same values -> returns true
        DecreaseCommand commandWithSameValues = new DecreaseCommand(PHONE_FIRST_PERSON, "100");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different phone -> returns false
        assertFalse(standardCommand.equals(new DecreaseCommand(PHONE_SECOND_PERSON, "100")));

        // different email -> returns false
        assertFalse(standardCommand.equals(new DecreaseCommand(EMAIL_SECOND_PERSON, "100")));

        // different reward value -> returns false
        assertFalse(standardCommand.equals(new DecreaseCommand(PHONE_FIRST_PERSON, "200")));
    }

}
