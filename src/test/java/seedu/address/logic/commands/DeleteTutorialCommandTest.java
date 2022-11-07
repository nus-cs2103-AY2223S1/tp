package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTORIAL;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
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

        assertCommandSuccess(deleteTutorialCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialList().size() + 1);
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(outOfBoundIndex);

        assertCommandFailure(deleteTutorialCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTutorialAtIndex(model, INDEX_FIRST_TUTORIAL);

        Tutorial tutorialToDelete = model.getFilteredTutorialList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(DeleteTutorialCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorial(tutorialToDelete);
        showNoTutorial(expectedModel);

        assertCommandSuccess(deleteTutorialCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorialAtIndex(model, INDEX_FIRST_TUTORIAL);

        Index outOfBoundIndex = INDEX_SECOND_TUTORIAL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTutorialList().size());

        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(outOfBoundIndex);

        assertCommandFailure(deleteTutorialCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTutorialCommand deleteFirstCommand = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);
        DeleteTutorialCommand deleteSecondCommand = new DeleteTutorialCommand(INDEX_SECOND_TUTORIAL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTutorialCommand deleteFirstCommandCopy = new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tutorial -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTutorial(Model model) {
        model.updateFilteredTutorialList(p -> false);

        assertTrue(model.getFilteredTutorialList().isEmpty());
    }
}
