package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_NAME_BACKEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalTeams.BACKEND;
import static seedu.address.testutil.TypicalTeams.FRONTEND;
import static seedu.address.testutil.TypicalTeams.INTERNS;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TeamBuilder;

public class TeamTest {

    @Test
    public void emptyTeamConstructor() {
        Name db = new Name("Database");
        Team team = new Team(db);
        assertEquals(team.getName(), db);
    }

    @Test
    public void nullTeamNameConstructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Team(null));
    }

    @Test
    public void asObservableList_modifyList_throwsPersonNotFoundException() {
        Team team = new TeamBuilder().build();
        assertThrows(PersonNotFoundException.class, () -> team.getMembers().remove(new PersonBuilder().build()));
    }

    @Test
    public void asObservableList_modifyList_throwsTaskNotFoundException() {
        Team team = new TeamBuilder().build();
        assertThrows(TaskNotFoundException.class, () -> team.getTasks().remove(new TaskBuilder().build()));
    }

    @Test
    public void isSameTeam() {
        // same object -> returns true
        assertTrue(FRONTEND.isSameTeam(FRONTEND));

        // null -> returns false
        assertFalse(FRONTEND.isSameTeam(null));

        // name differs, members same -> returns false
        assertFalse(FRONTEND.isSameTeam(INTERNS));

        // name has trailing spaces -> returns false
        Team database = new TeamBuilder().withName("Database").build();
        Team databaseWithTrailingSpace = new TeamBuilder().withName("Database ").build();
        assertFalse(database.isSameTeam(databaseWithTrailingSpace));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Team frontendCopy = new TeamBuilder(FRONTEND).build();
        assertTrue(FRONTEND.equals(frontendCopy));

        // same object -> returns true
        assertTrue(FRONTEND.equals(FRONTEND));

        // null -> returns false
        assertFalse(FRONTEND.equals(null));

        // different type -> returns false
        assertFalse(FRONTEND.equals(5));

        // different team -> returns false
        assertFalse(FRONTEND.equals(BACKEND));

        // different name -> returns false
        Team editedFrontend = new TeamBuilder(FRONTEND).withName(VALID_TEAM_NAME_BACKEND).build();
        assertFalse(FRONTEND.equals(editedFrontend));

        // different members -> returns false
        editedFrontend = new TeamBuilder(FRONTEND).withMembers(FIONA).build();
        assertFalse(FRONTEND.equals(editedFrontend));

    }


}
