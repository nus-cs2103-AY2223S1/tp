package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

public class JsonAdaptedModuleClassTest {
    private static final String INVALID_CLASSNAME = "C#@@";
    private static final List<Session> VALID_SESSIONS = CS1231S.getSessions();


    public static List<JsonAdaptedSession> getValidSessions() {
        List<JsonAdaptedSession> sessions = new ArrayList<>();
        for (Session session : VALID_SESSIONS) {
            sessions.add(new JsonAdaptedSession(session));
        }
        return sessions;
    }

    @Test
    public void toModelType_validModuleClassDetails_returnsModuleClass() throws Exception {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass(CS1231S);
        assertEquals(CS1231S, moduleClass.toModelType());
    }

    @Test
    public void toModelType_invalidModuleClassName_throwsIllegalValueException() {
        List<JsonAdaptedSession> sessions = getValidSessions();
        JsonAdaptedModuleClass moduleClass =
                new JsonAdaptedModuleClass(INVALID_CLASSNAME, sessions);
        String expectedMessage = ModuleClass.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, moduleClass::toModelType);
    }

    @Test
    public void toModelType_nullModuleClassName_throwsIllegalValueException() {
        List<JsonAdaptedSession> sessions = getValidSessions();
        JsonAdaptedModuleClass moduleClass =
                new JsonAdaptedModuleClass(null, sessions);
        assertThrows(IllegalValueException.class, JsonAdaptedModuleClass.MISSING_NAME_MESSAGE,
                moduleClass::toModelType);
    }

}
