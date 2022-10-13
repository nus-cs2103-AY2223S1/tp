package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTeams.FIRST;
import static seedu.address.testutil.TypicalTeams.FIRST_DUPLICATE;
import static seedu.address.testutil.TypicalTeams.SECOND;

import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    void isSameTeam() {
        //Teams are equal to itself
        assertTrue(FIRST.isSameTeam(FIRST));

        //Teams are equal to other teams with identical names
        assertTrue(FIRST.isSameTeam(FIRST_DUPLICATE));

        //Teams are not equal to other teams with different names
        assertFalse(FIRST.isSameTeam(SECOND));

        //Teams are not equal to null
        assertFalse(FIRST.isSameTeam(null));
    }

    @Test
    void testEquals() {
        //Teams are equal to itself
        assertTrue(FIRST.equals(FIRST));

        //Teams are equal to other teams with identical names
        assertTrue(FIRST.equals(FIRST_DUPLICATE));

        //Teams are not equal to other teams with different names
        assertFalse(FIRST.equals(SECOND));

        //Teams are not equal to null
        assertFalse(FIRST.equals(null));

        //Different Type
        assertFalse(FIRST.equals(5));
    }
}
