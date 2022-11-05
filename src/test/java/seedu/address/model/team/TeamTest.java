package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalLinks.LINK_YOUTUBE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_3;
import static seedu.address.testutil.TypicalTeams.FIRST_TEAM_DETAILS;
import static seedu.address.testutil.TypicalTeams.FIRST;
import static seedu.address.testutil.TypicalTeams.FIRST_DUPLICATE;
import static seedu.address.testutil.TypicalTeams.SECOND;
import static seedu.address.testutil.TypicalTeams.TYPICAL_LINKS;
import static seedu.address.testutil.TypicalTeams.TYPICAL_MEMBERS;
import static seedu.address.testutil.TypicalTeams.TYPICAL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.TeamBuilder;
import seedu.address.testutil.TypicalTasks;

class TeamTest {

    Team teamUnderTest = new Team(new TeamName("teamUnderTest"));
    @Test
    public void null_constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Team(null, null));
    }

    //Member Tests
    @Test
    public void hasMember_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teamUnderTest.hasMember(null));
    }

    @Test
    public void hasMember_personNotInTeam_returnsFalse() {
        assertFalse(teamUnderTest.hasMember(ALICE));
    }

    @Test
    public void hasMember_personInTeam_returnsTrue() {
        teamUnderTest.addMember(ALICE);
        assertTrue(teamUnderTest.hasMember(ALICE));
    }

    @Test
    public void getTeamMembers_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teamUnderTest.getTeamMembers().remove(0));
    }

    //Link Tests
    @Test
    public void hasLink_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teamUnderTest.hasLink(null));
    }

    @Test
    public void hasLink_linkNotInTeam_returnsFalse() {
        assertFalse(teamUnderTest.hasLink(LINK_GOOGLE));
    }

    @Test
    public void hasLink_linkInTeam_returnsTrue() {
        teamUnderTest.addLink(LINK_GOOGLE);
        assertTrue(teamUnderTest.hasLink(LINK_GOOGLE));
    }

    @Test
    public void getLinkList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teamUnderTest.getLinkList().remove(0));
    }

    //Task Tests
    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teamUnderTest.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTeam_returnsFalse() {
        assertFalse(teamUnderTest.hasTask(TASK_1));
    }

    @Test
    public void hasTask_taskInTeam_returnsTrue() {
        teamUnderTest.addTask(TASK_1);
        assertTrue(teamUnderTest.hasTask(TASK_1));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teamUnderTest.getTaskList().remove(0));
    }

    @Test
    void isSameTeam() {
        //Teams are equal to itself
        assertTrue(FIRST.isSameTeam(FIRST));

        //Teams are equal to other teams with identical names
        assertTrue(FIRST.isSameTeam(FIRST_DUPLICATE));

        //Teams are equal to other teams with different team details
        assertTrue(FIRST.isSameTeam(FIRST_TEAM_DETAILS));

        //Teams are not equal to other teams with different names
        assertFalse(FIRST.isSameTeam(SECOND));

        //Teams are equal to other teams with different descriptions
        Team editedFirst =  new Team(FIRST.getTeamName(), new Description("other description"));
        assertTrue(FIRST.isSameTeam(editedFirst));

        //Teams are not equal to null
        assertFalse(FIRST.isSameTeam(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Team editedFirst = new TeamBuilder(FIRST_TEAM_DETAILS).build();
        assertTrue(FIRST_TEAM_DETAILS.equals(editedFirst));

        // same object -> returns true
        assertTrue(FIRST_TEAM_DETAILS.equals(FIRST_TEAM_DETAILS));

        // null -> returns false
        assertFalse(FIRST_TEAM_DETAILS.equals(null));

        // different type -> returns false
        assertFalse(FIRST_TEAM_DETAILS.equals(5));

        // different team -> returns false
        assertFalse(FIRST_TEAM_DETAILS.equals(SECOND));

        // different name -> returns false
        Team firstDifferent = new TeamBuilder(FIRST_TEAM_DETAILS).withName("otherName").build();
        assertFalse(FIRST_TEAM_DETAILS.equals(firstDifferent));

        // different description -> returns false
        firstDifferent = new TeamBuilder(FIRST_TEAM_DETAILS).withDescription("otherDescription").build();
        assertFalse(FIRST_TEAM_DETAILS.equals(firstDifferent));

        // different members -> returns false
        List<Person> members = new ArrayList<>();
        for (Person p : TYPICAL_MEMBERS) {
            members.add(p);
        }
        members.add(CARL);
        firstDifferent = new TeamBuilder(FIRST_TEAM_DETAILS).withMembers(members).build();
        assertFalse(FIRST_TEAM_DETAILS.equals(firstDifferent));

        // different tasks -> returns false
        List<Task> tasks = new ArrayList<>();
        for (Task t : TYPICAL_TASKS) {
            tasks.add(t);
        }
        tasks.add(TASK_3);
        firstDifferent = new TeamBuilder(FIRST_TEAM_DETAILS).withTasks(tasks).build();
        assertFalse(FIRST_TEAM_DETAILS.equals(firstDifferent));

        // different links -> returns false
        List<Link> links = new ArrayList<>();
        for (Link l : TYPICAL_LINKS) {
            links.add(l);
        }
        links.add(LINK_YOUTUBE);
        firstDifferent = new TeamBuilder(FIRST_TEAM_DETAILS).withLinks(links).build();
        assertFalse(FIRST_TEAM_DETAILS.equals(firstDifferent));
    }
}
