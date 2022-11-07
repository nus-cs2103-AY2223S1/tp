package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.commons.core.index.Index;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;


/**
 * Contains integration tests (interaction with the BobaBotModel) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void execute_validPhoneArg_success() {
        // Corresponds to phone number input for "ALICE" under TypicalCustomers class
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("94351253"));

        // Gets the index of Customer (ALICE) within the list via her phone number
        int index = bobaBotModel.findNum(deletePersonDescriptor.getPhone());
        Index targetIndex = Index.fromZeroBased(index);

        // Gets ALICE
        Customer customerToDelete = bobaBotModel.getFilteredPersonList().get(targetIndex.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(deletePersonDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, customerToDelete);

        BobaBotModelManager expectedModel = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        expectedModel.deletePerson(customerToDelete);

        assertCommandSuccess(deleteCommand, bobaBotModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPhoneArg_throwsCommandException() {
        // Corresponds to a random phone number input not in the TypicalCustomers class
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("11111111"));

        // Throws a CommandException due to no corresponding Customer found
        DeleteCommand deleteCommand = new DeleteCommand(deletePersonDescriptor);

        assertCommandFailure(deleteCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_validEmailArg_success() {
        // Corresponds to email input for "CARL" under TypicalCustomers class
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("heinz@example.com"));

        // Gets the index of Customer (CARL) within the list via his email
        int index = bobaBotModel.findEmail(deletePersonDescriptor.getEmail());
        Index targetIndex = Index.fromZeroBased(index);

        // Gets CARL
        Customer customerToDelete = bobaBotModel.getFilteredPersonList().get(targetIndex.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(deletePersonDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, customerToDelete);

        BobaBotModelManager expectedModel = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        expectedModel.deletePerson(customerToDelete);

        assertCommandSuccess(deleteCommand, bobaBotModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEmailArg_throwsCommandException() {
        // Corresponds to a random email input not in the TypicalCustomers class
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("testing123@test.com"));

        // Throws a CommandException due to no corresponding Customer found
        DeleteCommand deleteCommand = new DeleteCommand(deletePersonDescriptor);

        assertCommandFailure(deleteCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void equals() {
        // (Phone Number) input values for deleteFirstCommand
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("12345678"));

        // (Phone Number) input values for deleteSecondCommand
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor2 = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor2.setPhone(new Phone("87654321"));

        // (Email) input values for deleteThirdCommand
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor3 = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor3.setEmail(new Email("test@test.com"));

        // (Email) input values for deleteFourthCommand
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor4 = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor4.setEmail(new Email("test2@test2.com"));

        DeleteCommand deleteFirstCommand = new DeleteCommand(deletePersonDescriptor);
        DeleteCommand deleteSecondCommand = new DeleteCommand(deletePersonDescriptor2);

        DeleteCommand deleteThirdCommand = new DeleteCommand(deletePersonDescriptor3);
        DeleteCommand deleteFourthCommand = new DeleteCommand(deletePersonDescriptor4);

        // (Phone Number) same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // (Phone Number) same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(deletePersonDescriptor);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // (Phone Number) different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // (Phone Number) null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // (Phone Number) different customer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // (Email) same object -> returns true
        assertTrue(deleteThirdCommand.equals(deleteThirdCommand));

        // (Email) same values -> returns true
        DeleteCommand deleteThirdCommandCopy = new DeleteCommand(deletePersonDescriptor3);
        assertTrue(deleteThirdCommand.equals(deleteThirdCommandCopy));

        // (Email) different types -> returns false
        assertFalse(deleteThirdCommand.equals(1));

        // (Email) null -> returns false
        assertFalse(deleteThirdCommand.equals(null));

        // (Email) different customer -> returns false
        assertFalse(deleteThirdCommand.equals(deleteFourthCommand));

        // Test equals method of deletePersonDescriptor
        assertFalse(deletePersonDescriptor.equals(1));
    }
}
