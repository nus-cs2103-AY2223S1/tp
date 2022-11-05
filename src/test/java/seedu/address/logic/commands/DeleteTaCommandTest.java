package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalProfNus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Student;
import seedu.address.model.person.TutorContainsModulePredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaCommand}.
 */
public class DeleteTaCommandTest {

    private Model model = new ModelManager(getTypicalProfNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToDelete = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTaCommand deleteTaCommand = new DeleteTaCommand(INDEX_FIRST_PERSON);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(DeleteTaCommand.MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete),
                false, false, false,
                true, false, false, false, false, false);

        ModelManager expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
        expectedModel.deletePerson(studentToDelete);

        assertCommandSuccess(deleteTaCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        DeleteTaCommand deleteTaCommand = new DeleteTaCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Student studentToDelete = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTaCommand deleteTaCommand = new DeleteTaCommand(INDEX_FIRST_PERSON);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(DeleteTaCommand.MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete),
                false, false, false,
                true, false, false, false, false, false);

        Model expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
        expectedModel.deleteTutor(studentToDelete);
        expectedModel.deletePerson(studentToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteTaCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {

        TutorContainsModulePredicate predicate = new TutorContainsModulePredicate(new ModuleCode("CS1101S"));
        model.updateFilteredTutorList(predicate);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of profNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProfNus().getTutorList().size());

        DeleteTaCommand deleteTaCommand = new DeleteTaCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaCommand deleteFirstCommand = new DeleteTaCommand(INDEX_FIRST_PERSON);
        DeleteTaCommand deleteSecondCommand = new DeleteTaCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaCommand deleteFirstCommandCopy = new DeleteTaCommand(INDEX_FIRST_PERSON);
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
        model.updateFilteredTutorList(p -> false);

        assertTrue(model.getFilteredTutorList().isEmpty());
    }
}

