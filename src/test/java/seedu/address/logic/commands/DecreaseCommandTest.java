package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.address.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE_DECREMENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPhones.PHONE_FIRST_PERSON;
import static seedu.address.testutil.TypicalPhones.PHONE_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains integration tests (interaction with the Model) and unit tests for IncreaseCommand.
 */
public class DecreaseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_phoneAllFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = ALICE_DECREMENT;
        DecreaseCommand decreaseCommand = new DecreaseCommand(PHONE_FIRST_PERSON, "100");

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        assertCommandSuccess(decreaseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emailAllFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = ALICE_DECREMENT;
        DecreaseCommand decreaseCommand = new DecreaseCommand(EMAIL_FIRST_PERSON, "100");

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(decreaseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_phoneInvalidPersonUnfilteredList_failure() {
        Phone outOfBoundPhone = new Phone("00000000");
        DecreaseCommand increaseCommand = new DecreaseCommand(outOfBoundPhone, "100");

        assertCommandFailure(increaseCommand, model, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_emailInvalidPersonUnfilteredList_failure() {
        Email outOfBoundEmail = new Email("test@test.com");
        DecreaseCommand increaseCommand = new DecreaseCommand(outOfBoundEmail, "100");

        assertCommandFailure(increaseCommand, model, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
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
