package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.commons.core.Messages.MESSAGE_SESSION_DOES_NOT_EXIST;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalSessions.TUTORIAL_1;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.ModuleClassBuilder;

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
        Set<Session> validSessions = new HashSet<>(List.of(LAB_1));
        assertThrows(CommandException.class, () -> new DeletesCommand(validSessions).execute(modelStub));
    }

    @Test
    public void execute_existingSessionInFocusMode_success() throws CommandException {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        assert !modelStub.getFocusedClass().getSessions().isEmpty();

        // getSessions() returns an unmodifiable list
        List<Session> expectedSessions = new ArrayList<>(modelStub.getFocusedClass().getSessions());
        Session sessionToRemove = modelStub.getFocusedClass().getSessions().get(0);
        Set<Session> sessionsToRemove = new HashSet<>(List.of(sessionToRemove));
        expectedSessions.remove(sessionToRemove);

        DeletesCommand command = new DeletesCommand(sessionsToRemove);
        CommandResult commandResult = command.execute(modelStub);

        assertEquals(DeletesCommand.getCommandMessage(sessionsToRemove), commandResult.getFeedbackToUser());
        assertEquals(expectedSessions, modelStub.getFocusedClass().getSessions());
    }

    @Test
    public void execute_multipleExistingSessionsInFocusMode_success() throws CommandException {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        assert modelStub.getFocusedClass().hasSession(LAB_1);
        assert modelStub.getFocusedClass().hasSession(ASSIGNMENT_1);

        List<Session> expectedSessions = new ArrayList<>(modelStub.getFocusedClass().getSessions());
        Set<Session> sessionsToRemove = new HashSet<>(List.of(LAB_1, ASSIGNMENT_1));
        expectedSessions.removeAll(sessionsToRemove);

        DeletesCommand command = new DeletesCommand(sessionsToRemove);
        CommandResult commandResult = command.execute(modelStub);

        assertEquals(DeletesCommand.getCommandMessage(sessionsToRemove), commandResult.getFeedbackToUser());
        assertEquals(expectedSessions, modelStub.getFocusedClass().getSessions());

    }

    @Test
    public void execute_nonExistingSessionInFocusMode_failure() {
        ModelStubWithModuleClassWithSessions modelStub = new ModelStubWithModuleClassWithSessions();
        ModuleClass focusedClass = modelStub.getFocusedClass();
        assert !focusedClass.hasSession(TUTORIAL_1);
        Set<Session> nonExistingSessions = new HashSet<>(List.of(TUTORIAL_1));
        DeletesCommand command = new DeletesCommand(nonExistingSessions);

        assertThrows(CommandException.class, () -> command.execute(modelStub));
        assertCommandFailure(command, modelStub, String.format(MESSAGE_SESSION_DOES_NOT_EXIST,
                TUTORIAL_1.getSessionName(), focusedClass));
    }

    //==================================== Integration Tests =========================================================

    @BeforeEach
    public void enterFocusMode() {
        ModuleClass moduleClassToFocus = model.getModuleClassList().get(0);
        model.enterFocusMode(moduleClassToFocus);
        expectedModel.enterFocusMode(moduleClassToFocus);
    }

    @Test
    public void execute_singleExistingSession_success() throws CommandException {
        ModuleClass focusedClass = model.getFocusedClass();
        model.setModuleClass(focusedClass, focusedClass.addSession(LAB_1));
        expectedModel.setModuleClass(focusedClass, focusedClass.addSession(LAB_1));
        assert model.getFocusedClass().hasSession(LAB_1);
        Set<Session> sessions = new HashSet<>(List.of(LAB_1));
        DeletesCommand command = new DeletesCommand(sessions);
        command.execute(expectedModel);
        assertCommandSuccess(command, model, DeletesCommand.getCommandMessage(sessions), expectedModel);
    }

    @Test
    public void execute_multipleExistingSessions_success() throws CommandException {
        ModuleClass focusedClass = model.getFocusedClass();

        model.setModuleClass(focusedClass, focusedClass.addSession(LAB_1).addSession(TUTORIAL_1));
        expectedModel.setModuleClass(focusedClass, focusedClass.addSession(LAB_1).addSession(TUTORIAL_1));

        assert model.getFocusedClass().hasSession(LAB_1);
        assert model.getFocusedClass().hasSession(TUTORIAL_1);

        Set<Session> sessions = new HashSet<>(List.of(LAB_1, TUTORIAL_1));
        DeletesCommand command = new DeletesCommand(sessions);
        command.execute(expectedModel);

        assertCommandSuccess(command, model, DeletesCommand.getCommandMessage(sessions), expectedModel);
    }

    @Test
    public void execute_nonExistingSession_failure() {
        Session nonExistingSession = ASSIGNMENT_1;
        ModuleClass focusedClass = model.getFocusedClass();
        assert !focusedClass.hasSession(nonExistingSession);
        Set<Session> sessions = new HashSet<>(List.of(nonExistingSession));
        DeletesCommand command = new DeletesCommand(sessions);
        assertCommandFailure(command, model, String.format(MESSAGE_SESSION_DOES_NOT_EXIST,
                nonExistingSession.getSessionName(), focusedClass));
    }

    //==================================== Model Stubs ===============================================================

    private static class ModelStubWithModuleClassWithSessions extends ModelStub {
        private List<Session> sessions = new ArrayList<>(List.of(LAB_1, ASSIGNMENT_1));

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public void removeSessions(ModuleClass moduleClass, Set<Session> sessions) {
            this.sessions = this.sessions.stream().filter(session -> !sessions.contains(session))
                    .collect(Collectors.toList());
        }

        @Override
        public ModuleClass getFocusedClass() {
            ModuleClassBuilder classBuilder = new ModuleClassBuilder().withSessions(sessions);
            return classBuilder.build();
        }

        @Override
        public void enterFocusMode(ModuleClass moduleClass) {
        }

        @Override
        public TaAssist getTaAssist() {
            return new TaAssist();
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList();
        }
    }
}
