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
 * {@code MarkTutorialCommand}.
 */
public class MarkTutorialCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tutorial tutorialToMark = model.getFilteredTutorialList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        Tutorial editedTutorial = new Tutorial(tutorialToMark.getGroup(),
                tutorialToMark.getContent(), tutorialToMark.getTime(), true);
        MarkTutorialCommand markTutorialCommand = new MarkTutorialCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(markTutorialCommand.MESSAGE_MARKTUT_SUCCESS, editedTutorial);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTutorial(tutorialToMark, editedTutorial);

        assertCommandSuccess(markTutorialCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialList().size() + 1);
        MarkTutorialCommand markTutorialCommand = new MarkTutorialCommand(outOfBoundIndex);

        assertCommandFailure(markTutorialCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTutorialCommand markTutorialFirstCommand = new MarkTutorialCommand(INDEX_FIRST_TUTORIAL);
        MarkTutorialCommand markTutorialSecondCommand = new MarkTutorialCommand(INDEX_SECOND_TUTORIAL);

        // same object -> returns true
        assertTrue(markTutorialFirstCommand.equals(markTutorialFirstCommand));

        // same values -> returns true
        MarkTutorialCommand markTutorialFirstCommandCopy = new MarkTutorialCommand(INDEX_FIRST_TUTORIAL);
        assertTrue(markTutorialFirstCommand.equals(markTutorialFirstCommandCopy));

        // different types -> returns false
        assertFalse(markTutorialFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markTutorialFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markTutorialFirstCommand.equals(markTutorialSecondCommand));
    }
}
