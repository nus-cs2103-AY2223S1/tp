package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

public class UnAssignMemberCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Team teamToUnAssign = model.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased());
        Person memberToUnAssign = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnAssignMemberCommand unAssignMemberCommand = new UnAssignMemberCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TEAM);
        String expectedMessage = String.format(UnAssignMemberCommand.MESSAGE_SUCCESS,
                memberToUnAssign.getName(), teamToUnAssign.getName());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
        Team teamToUnAssign2 = expectedModel.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased());
        Person memberToUnAssign2 = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.removePersonFromTeam(memberToUnAssign2, teamToUnAssign2);

        assertCommandSuccess(unAssignMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTeamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexTeam = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        Index person = INDEX_FIRST_PERSON;
        UnAssignMemberCommand unAssignMemberCommand = new UnAssignMemberCommand(person, outOfBoundIndexTeam);

        assertCommandFailure(unAssignMemberCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexPerson = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index team = INDEX_FIRST_TEAM;
        UnAssignMemberCommand unAssignMemberCommand = new UnAssignMemberCommand(outOfBoundIndexPerson, team);

        assertCommandFailure(unAssignMemberCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personDoesNotExistInTeamUnfilteredList_throwsCommandException() {
        Index teamIndex = INDEX_SECOND_TEAM;
        Index personIndex = INDEX_FIRST_PERSON;
        UnAssignMemberCommand unAssignMemberCommand = new UnAssignMemberCommand(personIndex, teamIndex);

        assertCommandFailure(unAssignMemberCommand, model, UnAssignMemberCommand.MESSAGE_PERSON_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        UnAssignMemberCommand unAssignFirstCommand = new UnAssignMemberCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TEAM);
        UnAssignMemberCommand unAssignSecondCommand = new UnAssignMemberCommand(INDEX_SECOND_PERSON, INDEX_SECOND_TEAM);

        // same object -> returns true
        assertTrue(unAssignFirstCommand.equals(unAssignFirstCommand));

        // different types -> returns false
        assertFalse(unAssignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unAssignFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unAssignFirstCommand.equals(unAssignSecondCommand));
    }

}


