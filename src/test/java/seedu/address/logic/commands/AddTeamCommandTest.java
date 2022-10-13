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

class AddTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_teamAlreadyExist_throwsCommandException() {
        Team exitingTeam = model.getTeam();
        AddTeamCommand addTeamCommand = new AddTeamCommand(exitingTeam);
        assertCommandFailure(addTeamCommand, model, AddTeamCommand.MESSAGE_TEAM_EXISTS);
    }

    @Test
    void testEquals() {
        Team exitingTeam = model.getTeam();
        Team anotherTeam = new Team("first", new ArrayList<>(), new ArrayList<>());
        AddTeamCommand addTeamCommand = new AddTeamCommand(exitingTeam);
        AddTeamCommand addTeamCommandDuplicate = new AddTeamCommand(exitingTeam);
        AddTeamCommand addTeamCommandAnotherTeam = new AddTeamCommand(anotherTeam);

        //Same object -> returns true
        assertTrue(addTeamCommand.equals(addTeamCommand));

        //Same values -> returns true
        assertTrue(addTeamCommand.equals(addTeamCommandDuplicate));

        //Different values -> returns false
        assertFalse(addTeamCommand.equals(addTeamCommandAnotherTeam));

        //null -> returns false
        assertFalse(addTeamCommand.equals(null));

        //different type -> returns false
        assertFalse(addTeamCommand.equals(5));
    }
}
