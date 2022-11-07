package seedu.address.logic.commands.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProfileAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROFILE;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.Profile;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteProfileCommandTest {

    private Model model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Profile profileToDelete = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        DeleteProfileCommand deleteCommand = new DeleteProfileCommand(INDEX_FIRST_PROFILE);

        String expectedMessage = String.format(DeleteProfileCommand.MESSAGE_DELETE_PROFILE_SUCCESS, profileToDelete);

        ModelManager expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
        expectedModel.deleteProfile(profileToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileList().size() + 1);
        DeleteProfileCommand deleteCommand = new DeleteProfileCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        Profile profileToDelete = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        DeleteProfileCommand deleteCommand = new DeleteProfileCommand(INDEX_FIRST_PROFILE);

        String expectedMessage = String.format(DeleteProfileCommand.MESSAGE_DELETE_PROFILE_SUCCESS, profileToDelete);

        Model expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
        expectedModel.deleteProfile(profileToDelete);
        showNoProfile(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        Index outOfBoundIndex = INDEX_SECOND_PROFILE;
        // ensures that outOfBoundIndex is still in bounds of NUScheduler list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNuScheduler().getProfileList().size());

        DeleteProfileCommand deleteCommand = new DeleteProfileCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProfileCommand deleteFirstCommand = new DeleteProfileCommand(INDEX_FIRST_PROFILE);
        DeleteProfileCommand deleteSecondCommand = new DeleteProfileCommand(INDEX_SECOND_PROFILE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProfileCommand deleteFirstCommandCopy = new DeleteProfileCommand(INDEX_FIRST_PROFILE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different profile -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProfile(Model model) {
        model.updateFilteredProfileList(p -> false);

        assertTrue(model.getFilteredProfileList().isEmpty());
    }
}
