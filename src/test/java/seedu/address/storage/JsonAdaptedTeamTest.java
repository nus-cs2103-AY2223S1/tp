package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTeam.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.FRONTEND;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Name;



public class JsonAdaptedTeamTest {

    private static final String INVALID_NAME = "Front@nd";

    private static final List<JsonAdaptedTask> VALID_TASKS = FRONTEND.getTasksList()
            .stream().map(JsonAdaptedTask::new).collect(Collectors.toList());
    private static final List<JsonAdaptedPerson> VALID_MEMBERS = FRONTEND.getMemberList()
            .stream().map(JsonAdaptedPerson::new).collect(Collectors.toList());

    @Test
    public void toModelType_validTeamDetails_returnsTeam() throws Exception {
        JsonAdaptedTeam team = new JsonAdaptedTeam(FRONTEND);
        assertEquals(FRONTEND, team.toModelType());
    }

    @Test
    public void toModelType_invalidTeamName_throwsIllegalValueException() {
        JsonAdaptedTeam team =
                new JsonAdaptedTeam(INVALID_NAME, VALID_TASKS, VALID_MEMBERS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, team::toModelType);
    }

    @Test
    public void toModelType_nullTeamName_throwsIllegalValueException() {
        JsonAdaptedTeam team =
                new JsonAdaptedTeam(null, VALID_TASKS, VALID_MEMBERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, team::toModelType);
    }


}
