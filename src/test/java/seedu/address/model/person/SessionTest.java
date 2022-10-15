package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session(null));
    }

    @Test
    public void constructor_invalidSession_throwsIllegalArgumentException() {
        String invalidSession = "";
        assertThrows(IllegalArgumentException.class, () -> new Session(invalidSession));
    }

    @Test
    public void isValidSession() {
        // null name
        assertThrows(NullPointerException.class, () -> Session.isValidSession(null));

        // invalid session
        assertFalse(Session.isValidSession("")); // empty string
        assertFalse(Session.isValidSession(" ")); // spaces only
        assertFalse(Session.isValidSession("08:30-09:0X")); // contains alphabetic character
        assertFalse(Session.isValidSession("08:30-09:0*")); // contains non-alphanumeric characters

        // valid session
        assertTrue(Session.isValidSession("08:00-09:00")); // valid HH-mm
        assertTrue(Session.isValidSession("08:30-12:00")); // valid HH-mm
        assertTrue(Session.isValidSession("09:00-11:00")); // valid HH-mm
    }
}
