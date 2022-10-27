package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static taskbook.testutil.TypicalTaskBook.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.commons.core.index.Index;
import taskbook.logic.commands.contacts.ContactDeleteCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.person.Person;
import taskbook.testutil.TypicalTaskBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class ContactDeleteCommandTest {

    private final Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ContactDeleteCommand contactDeleteCommand = new ContactDeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ContactDeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(contactDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ContactDeleteCommand contactDeleteCommand = new ContactDeleteCommand(outOfBoundIndex);

        assertCommandFailure(contactDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deletingPersonWithTask_throwsCommandException() {
        // Ensures that a person with a task is deleted.
        ModelManager modelManager = new ModelManager();
        Index index = Index.fromZeroBased(0);
        Person person = TypicalTaskBook.ALICE;
        modelManager.addPerson(person);
        modelManager.addTask(TypicalTaskBook.EATING);
        ContactDeleteCommand contactDeleteCommand = new ContactDeleteCommand(index);

        String expectedMessage = ContactDeleteCommand.getDeletePersonFailureMessage(person);
        assertCommandFailure(contactDeleteCommand, modelManager, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ContactDeleteCommand contactDeleteCommand = new ContactDeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ContactDeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(contactDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of task book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getPersonList().size());

        ContactDeleteCommand contactDeleteCommand = new ContactDeleteCommand(outOfBoundIndex);

        assertCommandFailure(contactDeleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ContactDeleteCommand deleteFirstCommand = new ContactDeleteCommand(INDEX_FIRST_PERSON);
        ContactDeleteCommand deleteSecondCommand = new ContactDeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ContactDeleteCommand deleteFirstCommandCopy = new ContactDeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonListPredicate(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
