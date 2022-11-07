package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

public class AssignMemberCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Team teamToAssign = model.getFilteredTeamList().get(INDEX_SECOND_TEAM.getZeroBased());
        Person memberToAssign = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AssignMemberCommand assignMemberCommand = new AssignMemberCommand(INDEX_FIRST_PERSON, INDEX_SECOND_TEAM);
        String expectedMessage = String.format(AssignMemberCommand.MESSAGE_SUCCESS,
                memberToAssign.getName(), teamToAssign.getName());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
        Team teamToAssign2 = expectedModel.getFilteredTeamList().get(INDEX_SECOND_TEAM.getZeroBased());
        Person memberToAssign2 = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.addPersonToTeam(memberToAssign2, teamToAssign2);

        assertCommandSuccess(assignMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTeamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexTeam = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        Index person = INDEX_FIRST_PERSON;
        AssignMemberCommand assignMemberCommand = new AssignMemberCommand(person, outOfBoundIndexTeam);

        assertCommandFailure(assignMemberCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexPerson = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index team = INDEX_FIRST_TEAM;
        AssignMemberCommand assignMemberCommand = new AssignMemberCommand(outOfBoundIndexPerson, team);

        assertCommandFailure(assignMemberCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personAlreadyExistsInTeamUnfilteredList_throwsCommandException() {
        Index teamIndex = INDEX_FIRST_TEAM;
        Index personIndex = INDEX_FIRST_PERSON;
        AssignMemberCommand assignMemberCommand = new AssignMemberCommand(personIndex, teamIndex);

        assertCommandFailure(assignMemberCommand, model, AssignMemberCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_nullInputUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignMemberCommand(null, null));
    }

    @Test
    public void execute_nullTeamIndexUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignMemberCommand(null, INDEX_FIRST_PERSON));
    }

    @Test
    public void execute_nullPersonIndexUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignMemberCommand(INDEX_FIRST_TEAM, null));
    }

    @Test
    public void equals() {
        AssignMemberCommand assignFirstCommand = new AssignMemberCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TEAM);
        AssignMemberCommand assignSecondCommand = new AssignMemberCommand(INDEX_SECOND_PERSON, INDEX_SECOND_TEAM);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }

}


