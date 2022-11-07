package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Team;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Team teamToDelete = model.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased());
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(INDEX_FIRST_TEAM);

        String expectedMessage = String.format(DeleteTeamCommand.MESSAGE_DELETE_TEAM_SUCCESS, teamToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeam(teamToDelete);

        assertCommandSuccess(deleteTeamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(outOfBoundIndex);

        assertCommandFailure(deleteTeamCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteTeamCommand deleteFirstCommand = new DeleteTeamCommand(INDEX_FIRST_TEAM);
        DeleteTeamCommand deleteSecondCommand = new DeleteTeamCommand(INDEX_SECOND_TEAM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTeamCommand deleteFirstCommandCopy = new DeleteTeamCommand(INDEX_FIRST_TEAM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
