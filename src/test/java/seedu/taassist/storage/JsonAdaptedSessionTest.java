package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_SESSION_NAME;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.storage.JsonAdaptedSession.MESSAGE_MISSING_DATE;
import static seedu.taassist.storage.JsonAdaptedSession.MESSAGE_MISSING_NAME;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.session.Session;

public class JsonAdaptedSessionTest {

    private static final LocalDate VALID_DATE = ASSIGNMENT_1.getDate().getValue();

    @Test
    public void toModelType_validSessionDetails_returnsSession() throws Exception {
        JsonAdaptedSession session = new JsonAdaptedSession(ASSIGNMENT_1);
        assertEquals(ASSIGNMENT_1, session.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(INVALID_SESSION_NAME, VALID_DATE);
        String expectedMessage = Session.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(null, VALID_DATE);
        String expectedMessage = MESSAGE_MISSING_NAME;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_SESSION_LAB1, null);
        String expectedMessage = MESSAGE_MISSING_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

}
