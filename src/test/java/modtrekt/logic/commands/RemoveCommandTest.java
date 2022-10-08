package modtrekt.logic.commands;

import static modtrekt.logic.commands.CommandTestUtil.assertCommandFailure;
import static modtrekt.logic.commands.CommandTestUtil.assertCommandSuccess;
import static modtrekt.logic.commands.CommandTestUtil.showPersonAtIndex;
import static modtrekt.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static modtrekt.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static modtrekt.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.Messages;
import modtrekt.commons.core.index.Index;
import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.UserPrefs;
import modtrekt.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class RemoveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(RemoveCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getPersonList().size());

        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemoveCommand deleteFirstCommand = new RemoveCommand(INDEX_FIRST_PERSON);
        RemoveCommand deleteSecondCommand = new RemoveCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        RemoveCommand deleteFirstCommandCopy = new RemoveCommand(INDEX_FIRST_PERSON);
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
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
