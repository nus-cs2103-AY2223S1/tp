package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.commons.core.Messages.MESSAGE_SESSION_DOES_NOT_EXIST;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;
import seedu.taassist.testutil.ModuleClassBuilder;
import seedu.taassist.testutil.SessionBuilder;

public class DeletesCommandTest {

    private final Model model = new ModelManager(getTypicalTaAssist(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());

    //==================================== Unit Tests ================================================================

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletesCommand(null));
    }

    @Test
    public void execute_notInFocusMode_throwsCommandException() {
        ModelStubNeverInFocusMode modelStub = new ModelStubNeverInFocusMode();
        Session validSession = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        Set<Session> validSessions = new HashSet<>(List.of(validSession));
        assertThrows(CommandException.class, () -> new DeletesCommand(validSessions).execute(modelStub));
    }

    @Test
    public void execute_existingSessionInFocusMode_success() throws CommandException {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        assert !modelStub.getFocusedClass().getSessions().isEmpty();
        Session existingSession = modelStub.getFocusedClass().getSessions().get(0);
        Set<Session> existingSessions = new HashSet<>(List.of(existingSession));
        DeletesCommand command = new DeletesCommand(existingSessions);
        CommandResult commandResult = command.execute(modelStub);
        assertEquals(DeletesCommand.getCommandMessage(existingSessions), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_multipleExistingSessionsInFocusMode_success() throws CommandException {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        assert modelStub.getFocusedClass().getSessions().size() > 1;
        Set<Session> existingSessions = new HashSet<>(modelStub.getFocusedClass().getSessions());
        DeletesCommand command = new DeletesCommand(existingSessions);
        CommandResult commandResult = command.execute(modelStub);
        assertEquals(DeletesCommand.getCommandMessage(existingSessions), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonExistingSessionInFocusMode_success() {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        assert !modelStub.getFocusedClass().hasSession(TUTORIAL_1);
        Set<Session> nonExistingSessions = new HashSet<>(List.of(TUTORIAL_1));
        DeletesCommand command = new DeletesCommand(nonExistingSessions);
        assertThrows(CommandException.class, () -> command.execute(modelStub));
    }

    //==================================== Integration Tests =========================================================

    @BeforeEach
    public void enterFocusMode() {
        ModuleClass moduleClassToFocus = model.getModuleClassList().get(0);
        model.enterFocusMode(moduleClassToFocus);
        expectedModel.enterFocusMode(moduleClassToFocus);
    }

    @Test
    public void execute_singleExistingSession_success() {
        ModuleClass focusedClass = model.getFocusedClass();
        model.setModuleClass(focusedClass, focusedClass.addSession(LAB_1));
        Set<Session> sessions = new HashSet<>(List.of(LAB_1));
        DeletesCommand command = new DeletesCommand(sessions);
        assertCommandSuccess(command, model, DeletesCommand.getCommandMessage(sessions), expectedModel);
    }

    @Test
    public void execute_multipleExistingSessions_success() {
        ModuleClass focusedClass = model.getFocusedClass();
        model.setModuleClass(focusedClass, focusedClass.addSession(LAB_1).addSession(TUTORIAL_1));
        assert model.getFocusedClass().hasSession(LAB_1);
        assert model.getFocusedClass().hasSession(TUTORIAL_1);
        Set<Session> sessions = new HashSet<>(List.of(LAB_1, TUTORIAL_1));
        DeletesCommand command = new DeletesCommand(sessions);
        assertCommandSuccess(command, model, DeletesCommand.getCommandMessage(sessions), expectedModel);
    }

    @Test
    public void execute_nonExistingSession_success() {
        Session nonExistingSession = LAB_1;
        ModuleClass focusedClass = model.getFocusedClass();
        assert !focusedClass.hasSession(nonExistingSession);
        Set<Session> sessions = new HashSet<>(List.of(nonExistingSession));
        DeletesCommand command = new DeletesCommand(sessions);
        assertCommandFailure(command, model, String.format(MESSAGE_SESSION_DOES_NOT_EXIST,
                nonExistingSession.getSessionName(), focusedClass));
    }

    //==================================== Model Stubs ===============================================================

    private static class ModelStubWithModuleClassWithSessions extends ModelStub {
        private final ModuleClass moduleClass = new ModuleClassBuilder().withSessions(LAB_1, ASSIGNMENT_1).build();

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public void removeSessions(ModuleClass moduleClass, Set<Session> sessions) {
        }

        @Override
        public ModuleClass getFocusedClass() {
            return moduleClass;
        }
    }
}
