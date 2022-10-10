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

class SetTeamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_setTeamAlreadySet_throwsCommandException() {
        Team exitingTeam = model.getTeam();
        SetTeamCommand setTeamCommand = new SetTeamCommand(exitingTeam);
        assertCommandFailure(setTeamCommand, model, SetTeamCommand.MESSAGE_TEAM_ALREADY_SET);
    }

    @Test
    void testEquals() {
        Team exitingTeam = model.getTeam();
        Team anotherTeam = new Team("first", new ArrayList<>(), new ArrayList<>());
        SetTeamCommand setTeamCommand = new SetTeamCommand(exitingTeam);
        SetTeamCommand setTeamCommandDuplicate = new SetTeamCommand(exitingTeam);
        SetTeamCommand setTeamCommandAnotherTeam = new SetTeamCommand(anotherTeam);

        //Same object -> returns true
        assertTrue(setTeamCommand.equals(setTeamCommand));

        //Same values -> returns true
        assertTrue(setTeamCommand.equals(setTeamCommandDuplicate));

        //Different values -> returns false
        assertFalse(setTeamCommand.equals(setTeamCommandAnotherTeam));

        //null -> returns false
        assertFalse(setTeamCommand.equals(null));

        //different type -> returns false
        assertFalse(setTeamCommand.equals(5));
    }
}
