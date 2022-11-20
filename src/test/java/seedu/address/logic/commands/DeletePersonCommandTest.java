package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FullNamePredicate fullNamePredicate = new FullNamePredicate(personToDelete.getName().toString());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(fullNamePredicate);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        FullNamePredicate fullNamePredicate = new FullNamePredicate("Abc");
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(fullNamePredicate);

        assertCommandFailure(deletePersonCommand, model, DeletePersonCommand.MESSAGE_INVALID_PERSON_NAME);
    }


    @Test
    public void equals() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(
                new FullNamePredicate(firstPerson.getName().toString()));
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(
                new FullNamePredicate(secondPerson.getName().toString()));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        Person firstpersoncopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FullNamePredicate fullNamePredicate = new FullNamePredicate(firstpersoncopy.getName().toString());
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(fullNamePredicate);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
