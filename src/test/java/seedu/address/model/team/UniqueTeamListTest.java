package seedu.address.model.team;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.model.team.exceptions.DuplicateTeamException;

class UniqueTeamListTest {

    private final UniqueTeamList uniqueTeamList = new UniqueTeamList();

    @Test
    public void contains_teamNotInList_returnsFalse() {
        assertFalse(uniqueTeamList.contains(FIRST));
    }

    @Test
    public void contains_teamInList_returnsTrue() {
        uniqueTeamList.add(FIRST);
        assertTrue(uniqueTeamList.contains(FIRST));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTeamList.add(FIRST);
        assertThrows(DuplicateTeamException.class, () -> uniqueTeamList.add(FIRST));
    }
}
