package seedu.codeconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeconnect.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.codeconnect.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.codeconnect.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.codeconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeconnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.commons.core.Messages;
import seedu.codeconnect.commons.core.index.Index;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.ModelManager;
import seedu.codeconnect.model.UserPrefs;
import seedu.codeconnect.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteContactCommand deleteFirstCommand = new DeleteContactCommand(INDEX_FIRST);
        DeleteContactCommand deleteSecondCommand = new DeleteContactCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteContactCommand deleteFirstCommandCopy = new DeleteContactCommand(INDEX_FIRST);
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
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
