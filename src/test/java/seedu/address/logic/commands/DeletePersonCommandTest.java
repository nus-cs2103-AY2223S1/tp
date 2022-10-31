package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyPersons;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.JON_NOT_IN_TYPICAL_ADDRESS_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithOnlyPersons(), new UserPrefs());

    @Test
    public void execute_validPersonUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(personToDelete.getName());

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.goToHomePage();

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentPersonUnfilteredList_throwsCommandException() {
        Name nameOfInvalidPerson = JON_NOT_IN_TYPICAL_ADDRESS_BOOK.getName();
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(nameOfInvalidPerson);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_NO_SUCH_PERSON_DELETE);
    }

    @Test
    public void execute_validPersonFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(personToDelete.getName());

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.goToHomePage();

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personNotInFilteredListButInAddressBook_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        // Ensures that the person we are filtering for is ALICE.
        assertTrue(model.hasPersonInFilteredList(ALICE));

        assertTrue(model.hasPerson(BENSON)); // Ensures that BENSON is in the address book.
        assertTrue(!model.hasPersonInFilteredList(BENSON)); // Ensures that BENSON is not in filtered list.

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(BENSON.getName());

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_NO_SUCH_PERSON_DELETE);
    }

    @Test
    public void equals() {
        Name firstName = new Name(VALID_NAME_AMY);
        Name secondName = new Name(VALID_NAME_BOB);
        DeletePersonCommand firstDeletePersonCommand = new DeletePersonCommand(firstName);
        DeletePersonCommand secondDeletePersonCommand = new DeletePersonCommand(secondName);

        // same object -> returns true
        assertTrue(firstDeletePersonCommand.equals(firstDeletePersonCommand));

        // same values -> returns true
        DeletePersonCommand firstDeletePersonCommandCopy = new DeletePersonCommand(firstName);
        assertTrue(firstDeletePersonCommand.equals(firstDeletePersonCommandCopy));

        // different types -> returns false
        assertFalse(firstDeletePersonCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeletePersonCommand.equals(null));

        // different person -> returns false
        assertFalse(firstDeletePersonCommand.equals(secondDeletePersonCommand));
    }
}
