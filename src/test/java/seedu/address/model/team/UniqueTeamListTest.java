package seedu.address.model.team;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SECOND_TEAM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.FIRST_TEAM;
import static seedu.address.testutil.TypicalTeams.SECOND_TEAM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;
import seedu.address.testutil.TeamBuilder;

class UniqueTeamListTest {

    private final UniqueTeamList uniqueTeamList = new UniqueTeamList();

    @Test
    public void contains_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.contains(null));
    }

    @Test
    public void contains_teamNotInList_returnsFalse() {
        assertFalse(uniqueTeamList.contains(FIRST_TEAM));
    }

    @Test
    public void contains_teamInList_returnsTrue() {
        uniqueTeamList.add(FIRST_TEAM);
        assertTrue(uniqueTeamList.contains(FIRST_TEAM));
    }

    @Test
    public void contains_teamWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTeamList.add(FIRST_TEAM);
        Team editFirst = new TeamBuilder(FIRST_TEAM).withDescription(VALID_DESCRIPTION_SECOND_TEAM).build();
        assertTrue(uniqueTeamList.contains(editFirst));
    }

    @Test
    public void add_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.add(null));
    }

    @Test
    public void add_duplicateTeam_throwsDuplicateTeamException() {
        uniqueTeamList.add(FIRST_TEAM);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.add(FIRST_TEAM));
    }

    @Test
    public void remove_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.remove(null));
    }

    @Test
    public void remove_teamDoesNotExist_throwsTeamNotFoundException() {
        assertThrows(TeamNotFoundException.class, () -> uniqueTeamList.remove(FIRST_TEAM));
    }

    @Test
    public void remove_existingTeam_removesTeam() {
        uniqueTeamList.add(FIRST_TEAM);
        uniqueTeamList.remove(FIRST_TEAM);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_nullUniqueTeamList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeams(null));
    }

    @Test
    public void setTeams_uniqueTeamList_replacesOwnListWithProvidedUniqueTeamList() {
        uniqueTeamList.add(FIRST_TEAM);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(FIRST_TEAM);
        uniqueTeamList.setTeams(expectedUniqueTeamList.asUnmodifiableObservableList());
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTeamList.setTeams((List<Team>) null));
    }

    @Test
    public void setTeams_list_replacesOwnListWithProvidedList() {
        uniqueTeamList.add(FIRST_TEAM);
        List<Team> teamList = Collections.singletonList(SECOND_TEAM);
        uniqueTeamList.setTeams(teamList);
        UniqueTeamList expectedUniqueTeamList = new UniqueTeamList();
        expectedUniqueTeamList.add(SECOND_TEAM);
        assertEquals(expectedUniqueTeamList, uniqueTeamList);
    }

    @Test
    public void setTeams_listWithDuplicateTeams_throwsDuplicateTeamException() {
        List<Team> listWithDuplicateTeams = Arrays.asList(FIRST_TEAM, FIRST_TEAM);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.setTeams(listWithDuplicateTeams));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTeamList.asUnmodifiableObservableList().remove(0));
    }
}
