package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.SessionData;

class JsonAdaptedSessionDataTest {

    @Test
    public void toModelType_validSessionData_returnsSessionData() throws IllegalValueException {
        SessionData sessionData = new SessionData(LAB_1, 100);
        JsonAdaptedSessionData jsonAdaptedSessionData = new JsonAdaptedSessionData(sessionData);
        assertEquals(sessionData, jsonAdaptedSessionData.toModelType());
    }

    @Test
    public void toModelType_invalidSessionName_throwsIllegalValueException() {
        JsonAdaptedSessionData jsonAdaptedSessionData = new JsonAdaptedSessionData("+Lab 1", "100");
        String expectedMessage = Session.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedSessionData::toModelType);
    }

    @Test
    public void toModelType_nullSessionName_throwsIllegalValueException() {
        JsonAdaptedSessionData jsonAdaptedSessionData = new JsonAdaptedSessionData(null, "100");
        assertThrows(IllegalValueException.class, JsonAdaptedSessionData.MISSING_NAME_MESSAGE,
                jsonAdaptedSessionData::toModelType);
    }
}
