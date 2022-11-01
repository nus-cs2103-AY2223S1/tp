package seedu.address.logic.commands.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTORIAL;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelType;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTutorialCommand}.
 */
public class DeleteTutorialCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(DeleteTutorialCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
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
