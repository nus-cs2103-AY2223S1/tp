package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Team;

class DeleteTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_deleteTeamNotExist_throwsCommandException() {
        Team newTeam = new Team("newTeam" , new ArrayList<>(), new ArrayList<>());

        assertTrue(!model.getTeamList().contains(newTeam));
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(newTeam);
        assertCommandFailure(deleteTeamCommand, model, DeleteTeamCommand.MESSAGE_TEAM_NOT_EXISTS);
    }

    @Test
    void testEquals() {
        Team exitingTeam = model.getTeam();
        Team anotherTeam = new Team("first", new ArrayList<>(), new ArrayList<>());
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(exitingTeam);
        DeleteTeamCommand deleteTeamCommandDuplicate = new DeleteTeamCommand(exitingTeam);
        DeleteTeamCommand deleteTeamCommandAnotherTeam = new DeleteTeamCommand(anotherTeam);

        //Same object -> returns true
        assertTrue(deleteTeamCommand.equals(deleteTeamCommand));

        //Same values -> returns true
        assertTrue(deleteTeamCommand.equals(deleteTeamCommandDuplicate));

        //Different values -> returns false
        assertFalse(deleteTeamCommand.equals(deleteTeamCommandAnotherTeam));

        //null -> returns false
        assertFalse(deleteTeamCommand.equals(null));

        //different type -> returns false
        assertFalse(deleteTeamCommand.equals(5));
    }
}
