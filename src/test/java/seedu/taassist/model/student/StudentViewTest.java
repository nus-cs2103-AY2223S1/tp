package seedu.taassist.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BOB;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.session.SessionData;

public class StudentViewTest {

    public static final Student ALICE_WITH_SESSIONDATA = ALICE.
            updateGrade(CS1231S, LAB_1, 100.0);

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentView(null));
    }

    @Test
    public void getSessionData_notQueried_throwsIllegalStateException() {
        StudentView studentView = new StudentView(ALICE);
        assertThrows(IllegalStateException.class, () -> studentView.getSessionData());
    }

    @Test
    public void getSessionData_queriedButEmpty_returnsEmptyOptional() {
        StudentView studentView = new StudentView(ALICE).withSession(CS1231S, LAB_1);
        assertEquals(Optional.empty(), studentView.getSessionData());

    }

    @Test
    public void getSessionData_queriedWithData_success() {
        StudentView studentView = new StudentView(ALICE_WITH_SESSIONDATA).withSession(CS1231S, LAB_1);
        Optional<SessionData> sessionData = Optional.of(new SessionData(LAB_1, 100.0));
        assertEquals(sessionData, studentView.getSessionData());
    }

    @Test
    public void withSession_isImmutable_success() {
        StudentView oriStudentView = new StudentView(ALICE);
        StudentView studentView = new StudentView(ALICE);
        StudentView modifiedStudentView = new StudentView(ALICE).withSession(CS1231S, LAB_1);
        assertEquals(studentView, oriStudentView);
    }

    @Test
    public void equals() {

        StudentView studentView = new StudentView(ALICE);

        // same object -> true
        assertTrue(studentView.equals(studentView));

        // same contents -> true
        StudentView studentViewDuplicate = new StudentView(ALICE);
        assertTrue(studentView.equals(studentViewDuplicate));

        // null -> false
        assertFalse(studentView.equals(null));

        // different student -> false
        StudentView bobView = new StudentView(BOB);
        assertFalse(studentView.equals(bobView));

        // different session states -> false
        StudentView queriedStudentView = new StudentView(ALICE).withSession(CS1231S, LAB_1);
        assertFalse(studentView.equals(queriedStudentView));

    }
}
