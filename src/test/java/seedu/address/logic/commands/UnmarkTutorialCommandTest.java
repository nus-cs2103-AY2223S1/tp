package seedu.address.logic.commands;

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
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkTutorialCommand}.
 */
public class UnmarkTutorialCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutorial tutorialToUnmark = model.getFilteredTutorialList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        Tutorial editedTutorial = new Tutorial(tutorialToUnmark.getGroup(), tutorialToUnmark.getContent(),
                tutorialToUnmark.getTime(), false);
        UnmarkTutorialCommand unmarkTutorialCommand = new UnmarkTutorialCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(unmarkTutorialCommand.MESSAGE_UNMARKTUT_SUCCESS, editedTutorial);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTutorial(tutorialToUnmark, editedTutorial);

        assertCommandSuccess(unmarkTutorialCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialList().size() + 1);
        UnmarkTutorialCommand unmarkTutorialCommand = new UnmarkTutorialCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTutorialCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkTutorialCommand unmarkTutorialFirstCommand = new UnmarkTutorialCommand(INDEX_FIRST_TUTORIAL);
        UnmarkTutorialCommand unmarkTutorialSecondCommand = new UnmarkTutorialCommand(INDEX_SECOND_TUTORIAL);

        // same object -> returns true
        assertTrue(unmarkTutorialFirstCommand.equals(unmarkTutorialFirstCommand));

        // same values -> returns true
        UnmarkTutorialCommand unmarkTutorialFirstCommandCopy = new UnmarkTutorialCommand(INDEX_FIRST_TUTORIAL);
        assertTrue(unmarkTutorialFirstCommand.equals(unmarkTutorialFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkTutorialFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkTutorialFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unmarkTutorialFirstCommand.equals(unmarkTutorialSecondCommand));
    }
}
