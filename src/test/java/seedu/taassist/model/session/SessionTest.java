package seedu.taassist.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;

import org.junit.jupiter.api.Test;

import seedu.taassist.testutil.SessionBuilder;

public class SessionTest {

    @Test
    public void constructor_invalidSessionName_throwsIllegalArgumentException() {
        String invalidSessionName = "";
        Date validDate = ASSIGNMENT_1.getDate();
        assertThrows(IllegalArgumentException.class, () -> new Session(invalidSessionName, validDate));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        String validSessionName = ASSIGNMENT_1.getSessionName();
        Date validDate = ASSIGNMENT_1.getDate();
        assertThrows(NullPointerException.class, () -> new Session(null, validDate));
        assertThrows(NullPointerException.class, () -> new Session(validSessionName, null));
    }

    @Test
    public void isSameSession_sameSessionName_returnsTrue() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_1.isSameSession(ASSIGNMENT_1));

        // same name -> returns true
        Session assignment1Copy = new SessionBuilder(ASSIGNMENT_1).withDate(TUTORIAL_1.getDate()).build();
        assertTrue(assignment1Copy.isSameSession(ASSIGNMENT_1));
    }

    @Test
    public void isSameSession_differentSessionName_returnsFalse() {
        // different session -> returns false
        assertFalse(ASSIGNMENT_1.isSameSession(TUTORIAL_1));

        // same date, different name -> returns false
        Session assignment1Copy = new SessionBuilder(ASSIGNMENT_1).withName(TUTORIAL_1.getSessionName()).build();
        assertFalse(assignment1Copy.isSameSession(ASSIGNMENT_1));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        // same object -> returns True
        assertTrue(ASSIGNMENT_1.equals(ASSIGNMENT_1));

        // same name and date -> returns True
        Session tutorial1Copy = new SessionBuilder(TUTORIAL_1).build();
        assertTrue(TUTORIAL_1.equals(tutorial1Copy));
    }

    @Test
    public void equals_diffValues_returnsFalse() {
        // different session -> returns false
        assertFalse(ASSIGNMENT_1.equals(TUTORIAL_1));

        // different date -> returns false
        Session assignment1Copy = new SessionBuilder(ASSIGNMENT_1).withDate(TUTORIAL_1.getDate()).build();
        assertFalse(assignment1Copy.equals(ASSIGNMENT_1));

        // different name -> returns false
        Session assignment1Copy2 = new SessionBuilder(ASSIGNMENT_1).withName(TUTORIAL_1.getSessionName()).build();
        assertFalse(assignment1Copy2.equals(ASSIGNMENT_1));
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
