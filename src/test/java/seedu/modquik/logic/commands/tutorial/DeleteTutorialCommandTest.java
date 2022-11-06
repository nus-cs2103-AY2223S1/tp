package seedu.modquik.logic.commands.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_SECOND_TUTORIAL;
import static seedu.modquik.testutil.TypicalTutorials.getTypicalModQuik;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.tutorial.Tutorial;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTutorialCommand}.
 */
public class DeleteTutorialCommandTest {

    private Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(DeleteTutorialCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        ModelManager expectedModel = new ModelManager(model.getModQuik(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);
        assertCommandSuccess(deleteTutorialCommand, model, expectedMessage, ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialList().size() + 1);
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(outOfBoundIndex);

        assertCommandFailure(deleteTutorialCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTutorialCommand deleteFirstTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);
        DeleteTutorialCommand deleteSecondTutorialCommand = new DeleteTutorialCommand(INDEX_SECOND_TUTORIAL);

        // same object -> returns true
        assertTrue(deleteFirstTutorialCommand.equals(deleteFirstTutorialCommand));

        // same values -> returns true
        DeleteTutorialCommand deleteFirstTutorialCommandCopy = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);
        assertTrue(deleteFirstTutorialCommand.equals(deleteFirstTutorialCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTutorialCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTutorialCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstTutorialCommand.equals(deleteSecondTutorialCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTutorial(Model model) {
        model.updateFilteredTutorialList(p -> false);

        assertTrue(model.getFilteredTutorialList().isEmpty());
    }
}
