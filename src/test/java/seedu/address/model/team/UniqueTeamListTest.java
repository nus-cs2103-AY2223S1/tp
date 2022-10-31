package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_NAME_BACKEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalTasks.STUDY;
import static seedu.address.testutil.TypicalTeams.BACKEND;
import static seedu.address.testutil.TypicalTeams.FRONTEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;
import seedu.address.testutil.TeamBuilder;

public class UniqueTeamListTest {

    private final UniqueTeamList uniqueTeamList = new UniqueTeamList();

    @Test
    public void contains_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.contains(null));
    }

    @Test
    public void contains_teamNotInList_returnsFalse() {
        assertFalse(uniqueTeamList.contains(FRONTEND));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTeamList.add(FRONTEND);
        assertTrue(uniqueTeamList.contains(FRONTEND));
    }

    @Test
    public void contains_teamWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTeamList.add(FRONTEND);
        Team editedFrontend = new TeamBuilder(FRONTEND).withMembers(FIONA).build();
        assertTrue(uniqueTeamList.contains(editedFrontend));
    }

    @Test
    public void add_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicateTeamException() {
        uniqueTeamList.add(FRONTEND);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.add(FRONTEND));
    }

    @Test
    public void get_validIndex_returnTeam() {
        uniqueTeamList.add(FRONTEND);
        assertEquals(FRONTEND, uniqueTeamList.get(0));
    }

    @Test
    public void setTeamName_nullTargetTeam_throwsNullPointerException() {
        uniqueTeamList.add(FRONTEND);
        assertThrows(NullPointerException.class, ()
                -> uniqueTeamList.setTeamName(0, null));
    }

    @Test
    public void setTeamName_teamIndexNotInList_throwsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, ()
                -> uniqueTeamList.setTeamName(0, new Name(VALID_TEAM_NAME_BACKEND)));
    }

    @Test
    public void setTeamName_editedTeamRetainFields_success() {
        uniqueTeamList.add(FRONTEND);
        uniqueTeamList.setTeamName(0, new Name(VALID_TEAM_NAME_BACKEND));
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(FRONTEND);
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void getTeam_containTeamWithNameInList_returnTeam() {
        uniqueTeamList.add(FRONTEND);
        assertEquals(FRONTEND, uniqueTeamList.getTeam(FRONTEND.getName()));
    }

    @Test
    public void getTeam_noTeamWithTargetName_throwTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> uniqueTeamList.getTeam(FRONTEND.getName()));
    }


    @Test
    public void remove_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.remove(null));
    }

    @Test
    public void remove_teamDoesNotExist_throwsTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> uniqueTeamList.remove(FRONTEND));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTeamList.add(FRONTEND);
        uniqueTeamList.remove(FRONTEND);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }


    @Test
    public void setTeams_nullUniqueTeamList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeams((UniqueTeamList) null));
    }

    @Test
    public void setTeams_uniqueTeamList_replacesOwnListWithProvidedUniqueTeamList() {
        uniqueTeamList.add(FRONTEND);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(BACKEND);
        uniqueTeamList.setTeams(expectedUniqueTeamList);
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeams((List<Team>) null));
    }

    @Test
    public void setTeams_list_replacesOwnListWithProvidedList() {
        uniqueTeamList.add(FRONTEND);
        uniqueTeamList.setTeams(new ArrayList<>(Arrays.asList(BACKEND)));
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(BACKEND);
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Team> listWithDuplicatePersons = Arrays.asList(FRONTEND, FRONTEND);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.setTeams(listWithDuplicatePersons));
    }

    @Test
    public void addTask_noTeamWithTargetIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueTeamList.addTask(50, STUDY));
    }

    @Test
    public void addTask_nullTask_throwsNullPointerException() {
        uniqueTeamList.add(FRONTEND);
        assertThrows(NullPointerException.class, () -> uniqueTeamList.addTask(0, null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTeamList.asUnmodifiableObservableList().remove(0));
    }
}
