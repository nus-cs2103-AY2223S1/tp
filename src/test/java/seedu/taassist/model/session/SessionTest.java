package seedu.taassist.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.moduleclass.ModuleClass;

public class SessionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleClass(null));
    }

    @Test
    public void constructor_invalidSessionName_throwsIllegalArgumentException() {
        String invalidSessionName = "";
        assertThrows(IllegalArgumentException.class, () -> new Session(invalidSessionName));
    }

    @Test
    public void constructor_nullSessionName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session(null));
    }

    @Test
    public void isSameSession_sameSessionName_returnsTrue() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_1.isSameSession(ASSIGNMENT_1));

        // same name -> returns true
        Session assignment1Copy = new Session(ASSIGNMENT_1.getSessionName());
        assertTrue(assignment1Copy.isSameSession(ASSIGNMENT_1));
    }

    @Test
    public void isValidSessionName() {
        // special characters and whitespaces are allowed
        assertTrue(Session.isValidSessionName("+/~@#$%^&* "));

        // sessions names beginning with whitespaces are not allowed
        assertFalse(Session.isValidSessionName(" Assignment 1"));

        // empty session names are not allowed
        assertFalse(Session.isValidSessionName(""));
    }
}
