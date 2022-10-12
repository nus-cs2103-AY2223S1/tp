package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.assertCommandFailure;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.logic.commands.CommandTestUtil.showTutorAtIndex;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalIndexes.INDEX_SECOND_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutor tutorToDelete = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TUTOR);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TUTOR_SUCCESS, tutorToDelete);

        ModelManager expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
        expectedModel.deleteTutor(tutorToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        Tutor tutorToDelete = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_TUTOR);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TUTOR_SUCCESS, tutorToDelete);

        Model expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
        expectedModel.deleteTutor(tutorToDelete);
        showNoTutor(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorAtIndex(model, INDEX_FIRST_TUTOR);

        Index outOfBoundIndex = INDEX_SECOND_TUTOR;
        // ensures that outOfBoundIndex is still in bounds of tuthub list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTuthub().getTutorList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_TUTOR);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_TUTOR);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_TUTOR);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTutor(Model model) {
        model.updateFilteredTutorList(p -> false);

        assertTrue(model.getFilteredTutorList().isEmpty());
    }
}
