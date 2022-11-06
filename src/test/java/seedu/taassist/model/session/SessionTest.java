package seedu.taassist.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_SESSION_NAME;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.testutil.SessionBuilder;

public class SessionTest {

    @Test
    public void constructor_invalidSessionName_throwsIllegalArgumentException() {
        Date validDate = ASSIGNMENT_1.getDate();
        assertThrows(IllegalArgumentException.class, () -> new Session(INVALID_SESSION_NAME));
        assertThrows(IllegalArgumentException.class, () -> new Session(INVALID_SESSION_NAME, validDate));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        String validSessionName = ASSIGNMENT_1.getSessionName();
        Date validDate = ASSIGNMENT_1.getDate();
        assertThrows(NullPointerException.class, () -> new Session(null));
        assertThrows(NullPointerException.class, () -> new Session(null, validDate));
        assertThrows(NullPointerException.class, () -> new Session(validSessionName, null));
    }


    @Test
    public void constructor_validLowerCaseSessionName_returnsSessionWithCapitalisedName() {
        String lowerCaseLab1 = "lab1";
        Session session = new Session(lowerCaseLab1);
        String expectedSessionName = StringUtil.capitalise(lowerCaseLab1);
        assertEquals(expectedSessionName, session.getSessionName());
    }

    @Test
    public void isSame_sameSessionName_returnsTrue() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_1.isSame(ASSIGNMENT_1));

        // same name -> returns true
        Session assignment1Copy = new SessionBuilder(ASSIGNMENT_1).withDate(TUTORIAL_1.getDate()).build();
        assertTrue(assignment1Copy.isSame(ASSIGNMENT_1));
    }

    @Test
    public void isSame_differentSessionName_returnsFalse() {
        // different session -> returns false
        assertFalse(ASSIGNMENT_1.isSame(TUTORIAL_1));

        // same date, different name -> returns false
        Session assignment1Copy = new SessionBuilder(ASSIGNMENT_1).withName(TUTORIAL_1.getSessionName()).build();
        assertFalse(assignment1Copy.isSame(ASSIGNMENT_1));
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
        // underscores and whitespace are allowed
        assertTrue(Session.isValidSessionName("_LAB 1_"));
        // special characters are not allowed
        assertFalse(Session.isValidSessionName("+/~@#$%^&* "));
        // empty session names are not allowed
        assertFalse(Session.isValidSessionName(""));
    }
}
