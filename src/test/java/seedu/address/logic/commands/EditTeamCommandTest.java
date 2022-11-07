package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTeams.BACKEND;
import static seedu.address.testutil.TypicalTeams.FRONTEND;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Team;
import seedu.address.testutil.TeamBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTeamCommand.
 */
public class EditTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_fieldsSpecifiedUnfilteredList_success() {
        Team editedTeam = new TeamBuilder().build();
        EditTeamCommand editTeamCommand = new EditTeamCommand(INDEX_FIRST_TEAM, editedTeam.getName());

        String expectedMessage = String.format(EditTeamCommand.MESSAGE_EDIT_TEAM_SUCCESS, editedTeam.getName());

        Model expectedModel = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
        expectedModel.setTeamName(INDEX_FIRST_TEAM, editedTeam.getName());

        assertCommandSuccess(editTeamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTeamNameUnfilteredList_failure() {
        Team editedTeam = new TeamBuilder(BACKEND).build();
        EditTeamCommand editTeamCommand = new EditTeamCommand(INDEX_FIRST_TEAM, editedTeam.getName());

        assertCommandFailure(editTeamCommand, model, EditTeamCommand.MESSAGE_DUPLICATE_TEAM_NAME);
    }

    @Test
    public void execute_nullTeamNameUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTeamCommand(INDEX_FIRST_TEAM, null));
    }

    @Test
    public void execute_nullTeamIndexUnfilteredList_throwsNullPointerException() {
        Team editedTeam = new TeamBuilder().build();
        assertThrows(NullPointerException.class, () -> new EditTeamCommand(null, editedTeam.getName()));
    }

    @Test
    public void execute_invalidTeamIndexUnfilteredList_throwsCommandException() {
        Team editedTeam = new TeamBuilder().build();
        Index outOfBoundIndexTeam = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        EditTeamCommand editTeamCommand = new EditTeamCommand(outOfBoundIndexTeam, editedTeam.getName());

        assertCommandFailure(editTeamCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Team editedTeam = new TeamBuilder().build();
        Team editedTeam1 = new TeamBuilder(FRONTEND).build();
        EditTeamCommand editTeamCommand = new EditTeamCommand(INDEX_FIRST_TEAM, editedTeam.getName());
        EditTeamCommand editTeamCommand1 = new EditTeamCommand(INDEX_FIRST_TEAM, editedTeam1.getName());
        EditTeamCommand editTeamCommand2 = new EditTeamCommand(INDEX_SECOND_TEAM, editedTeam.getName());


        // same object -> returns true
        assertTrue(editTeamCommand.equals(editTeamCommand));

        // different types -> returns false
        assertFalse(editTeamCommand.equals(1));

        // null -> returns false
        assertFalse(editTeamCommand.equals(null));

        // different teamName -> returns false
        assertFalse(editTeamCommand.equals(editTeamCommand1));

        //different team -> returns false
        assertFalse(editTeamCommand.equals(editTeamCommand2));
    }

}
