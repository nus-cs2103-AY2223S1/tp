package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandFailure;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static hobbylist.logic.commands.CommandTestUtil.showActivityAtIndex;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.Activity;
import hobbylist.testutil.TypicalActivities;
import hobbylist.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Activity activityToDelete = model.getFilteredActivityList().get(TypicalIndexes
                .INDEX_FIRST_ACTIVITY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        ModelManager expectedModel = new ModelManager(model.getHobbyList(), new UserPrefs());
        expectedModel.deleteActivity(activityToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);

        Activity activityToDelete = model.getFilteredActivityList()
                .get(TypicalIndexes.INDEX_FIRST_ACTIVITY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete);

        Model expectedModel = new ModelManager(model.getHobbyList(), new UserPrefs());
        expectedModel.deleteActivity(activityToDelete);
        showNoActivity(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ACTIVITY;
        // ensures that outOfBoundIndex is still in bounds of HobbyList list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHobbyList().getActivityList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_ACTIVITY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different activity -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show nothing.
     */
    private void showNoActivity(Model model) {
        model.updateFilteredActivityList(p -> false);

        assertTrue(model.getFilteredActivityList().isEmpty());
    }
}
