package seedu.taassist.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class StudentModuleDataTest {

    @Test
    public void findSessionData_sessionDoesntExist_returnsEmptyOptional() {
        StudentModuleData studentModuleData = new StudentModuleData(CS1231S);
        assertFalse(studentModuleData.findSessionData(LAB_1).isPresent());
    }

    @Test
    public void findSessionData_sessionExists_returnsSessionData() {
        SessionData sessionData = new SessionData(LAB_1, 100);
        StudentModuleData studentModuleData = new StudentModuleData(CS1231S, List.of(sessionData));
        Optional<SessionData> sessionDataOptional = studentModuleData.findSessionData(LAB_1);
        assertTrue(sessionDataOptional.isPresent() && sessionDataOptional.get().equals(sessionData));
    }

    @Test
    public void removeSession_sessionDoesntExist_returnsOriginal() {
        StudentModuleData originalData = new StudentModuleData(CS1231S);
        StudentModuleData updatedData = originalData.removeSession(LAB_1);
        assertTrue(updatedData.equals(originalData));
    }

    @Test
    public void removeSession_sessionExists_removesSession() {
        SessionData sessionData = new SessionData(LAB_1, 100);
        StudentModuleData originalData = new StudentModuleData(CS1231S, List.of(sessionData));

        StudentModuleData updatedData = originalData.removeSession(LAB_1);
        StudentModuleData expectedData = new StudentModuleData(CS1231S);
        assertTrue(expectedData.equals(updatedData));
    }

    @Test
    public void updateGrade_sessionDoesntExist_addsNewGrade() {
        StudentModuleData originalData = new StudentModuleData(CS1231S);
        StudentModuleData updatedData = originalData.updateGrade(LAB_1, 100);
        StudentModuleData expectedData = new StudentModuleData(CS1231S, List.of(new SessionData(LAB_1, 100)));
        assertTrue(expectedData.equals(updatedData));
    }

    @Test
    public void updateGrade_sessionExists_updatesGrade() {
        SessionData sessionData = new SessionData(LAB_1, 100);
        StudentModuleData originalData = new StudentModuleData(CS1231S, List.of(sessionData));
        StudentModuleData updatedData = originalData.updateGrade(LAB_1, 50);
        StudentModuleData expectedData = new StudentModuleData(CS1231S, List.of(new SessionData(LAB_1, 50)));
        assertTrue(expectedData.equals(updatedData));
    }

}
