package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.TypicalTeams;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedTeamTest {

    @Test
    void toModelType_validTeam_returnsTeam() throws IllegalValueException {
        JsonAdaptedTeam team = new JsonAdaptedTeam(TypicalTeams.SECOND);
        assertEquals(TypicalTeams.SECOND, team.toModelType());
    }

    @Test
    void toModelType_invalidTeam_throwsException() throws IllegalValueException {
        JsonAdaptedTeam team = new JsonAdaptedTeam(null, null, null);
        assertThrows(IllegalValueException.class, () -> team.toModelType());
    }
}