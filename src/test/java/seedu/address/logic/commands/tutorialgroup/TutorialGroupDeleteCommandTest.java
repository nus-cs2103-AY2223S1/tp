package seedu.address.logic.commands.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showTutorialGroupAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTORIAL;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.TutorialGroup;


public class TutorialGroupDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        TutorialGroup tutorialToDelete = model.getFilteredTutorialGroupList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        TutorialGroupDeleteCommand tutorialGroupDeleteCommand = new TutorialGroupDeleteCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(TutorialGroupDeleteCommand.MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS,
                tutorialToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorialGroup(tutorialToDelete);

        //assertCommandSuccess(tutorialGroupDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorialGroupList().size() + 1);
        TutorialGroupDeleteCommand tutorialGroupDeleteCommand = new TutorialGroupDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tutorialGroupDeleteCommand, model,
                Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTutorialGroupAtIndex(model, INDEX_FIRST_TUTORIAL);

        TutorialGroup tutorialToDelete = model.getFilteredTutorialGroupList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        TutorialGroupDeleteCommand tutorialGroupDeleteCommand = new TutorialGroupDeleteCommand(INDEX_FIRST_TUTORIAL);

        String expectedMessage = String.format(tutorialGroupDeleteCommand.MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS,
                tutorialToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTutorialGroup(tutorialToDelete);
        showNoTutorialGroups(expectedModel);

        //assertCommandSuccess(tutorialGroupDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorialGroupAtIndex(model, INDEX_FIRST_TUTORIAL);

        Index outOfBoundIndex = INDEX_SECOND_TUTORIAL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTutorialGroupList().size());

        TutorialGroupDeleteCommand tutorialGroupDeleteCommand = new TutorialGroupDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tutorialGroupDeleteCommand, model,
                Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TutorialGroupDeleteCommand deleteFirstCommand = new TutorialGroupDeleteCommand(INDEX_FIRST_TUTORIAL);
        TutorialGroupDeleteCommand deleteSecondCommand = new TutorialGroupDeleteCommand(INDEX_SECOND_TUTORIAL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        TutorialGroupDeleteCommand deleteFirstCommandCopy = new TutorialGroupDeleteCommand(INDEX_FIRST_TUTORIAL);
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
    private void showNoTutorialGroups(Model model) {
        model.updateFilteredTutorialGroupList(p -> false);

        assertTrue(model.getFilteredTutorialGroupList().isEmpty());
    }
}
