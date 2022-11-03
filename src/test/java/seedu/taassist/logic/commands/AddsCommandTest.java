package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_TUT3;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
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


public class AddsCommandTest {

    private final Model model = new ModelManager(getTypicalTaAssist(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());

    //==================================== Unit Tests ================================================================

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddsCommand(null));
    }

    @Test
    public void execute_notInFocusMode_throwsCommandException() {
        ModelStubNeverInFocusMode modelStub = new ModelStubNeverInFocusMode();
        Session validSession = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        Set<Session> validSessions = new HashSet<>(List.of(validSession));
        assertThrows(CommandException.class, () -> new AddsCommand(validSessions).execute(modelStub));
    }

    @Test
    public void execute_validSessionsInFocusMode_success() throws CommandException {
        ModelStubAcceptingSessions modelStub = new ModelStubAcceptingSessions();
        Session validSession1 = new SessionBuilder().withName("lab1").build();
        Session validSession2 = new SessionBuilder().withName("tut1").build();
        Set<Session> validSessions = new HashSet<>(List.of(validSession1, validSession2));
        AddsCommand command = new AddsCommand(validSessions);
        CommandResult commandResult = command.execute(modelStub);
        assertEquals(AddsCommand.getCommandMessage(validSessions, new HashSet<>()),
                commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_validSessionsWithDuplicates_success() throws CommandException {
        ModelStubWithFocusedClass modelStub = new ModelStubWithFocusedClass();
        Session validSession = new SessionBuilder().withName(VALID_SESSION_TUT3).build();
        Session duplicatedSession = new SessionBuilder().withName(VALID_SESSION_LAB1).build();

        Set<Session> allSessions = new HashSet<>(List.of(validSession, duplicatedSession));
        Set<Session> validSessions = new HashSet<>(List.of(validSession));
        Set<Session> duplicatedSessions = new HashSet<>(List.of(duplicatedSession));

        AddsCommand command = new AddsCommand(allSessions);
        CommandResult commandResult = command.execute(modelStub);

        assertEquals(AddsCommand.getCommandMessage(validSessions, duplicatedSessions),
                commandResult.getFeedbackToUser());
    }

    //==================================== Integration Tests =========================================================

    @BeforeEach
    public void enterFocusMode() {
        ModuleClass moduleClassToFocus = model.getModuleClassList().get(0);
        model.enterFocusMode(moduleClassToFocus);
        expectedModel.enterFocusMode(moduleClassToFocus);
    }

    @Test
    public void execute_addSingleNewSession_success() throws CommandException {
        ModuleClass focusedClass = model.getFocusedClass();
        Session validSession = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        assert !focusedClass.hasSession(validSession);

        Set<Session> validSessions = new HashSet<>(List.of(validSession));
        AddsCommand command = new AddsCommand(validSessions);
        command.execute(expectedModel);

        assertCommandSuccess(command, model, AddsCommand.getCommandMessage(validSessions, new HashSet<>()),
                expectedModel);
    }

    @Test
    public void execute_addMultipleNewSessions_success() throws CommandException {
        ModuleClass focusedClass = model.getFocusedClass();

        Session validSessionLab = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        Session validSessionTut = new SessionBuilder().withName(VALID_SESSION_TUT3).build();

        assert !focusedClass.hasSession(validSessionLab);

        Set<Session> validSessions = new HashSet<>(List.of(validSessionLab, validSessionTut));
        AddsCommand command = new AddsCommand(validSessions);
        command.execute(expectedModel);

        assertCommandSuccess(command, model, AddsCommand.getCommandMessage(validSessions, new HashSet<>()),
                expectedModel);
    }

    @Test
    public void execute_addDuplicate_showsDuplicateMessage() throws CommandException {
        ModuleClass focusedClass = model.getFocusedClass();
        Session validSessionLab = new SessionBuilder().withName(VALID_SESSION_LAB1).build();

        assert !focusedClass.hasSession(validSessionLab);

        Set<Session> validSessions = new HashSet<>(List.of(validSessionLab));
        model.addSessions(focusedClass, validSessions);
        expectedModel.addSessions(focusedClass, validSessions);

        AddsCommand command = new AddsCommand(validSessions);
        command.execute(expectedModel);

        assertCommandSuccess(command, model, AddsCommand.getCommandMessage(new HashSet<>(), validSessions),
                expectedModel);
    }

    //==================================== Model Stubs ==============================================================

    private static class ModelStubAcceptingSessions extends ModelStub {
        private final ModuleClass focusedClass = new ModuleClassBuilder().build();

        @Override
        public void addSessions(ModuleClass moduleClass, Set<Session> sessions) {
        }

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }
    }

    private static class ModelStubWithFocusedClass extends ModelStub {
        private final ModuleClass focusedClass = new ModuleClassBuilder()
                .withName(VALID_CLASS_CS1101S)
                .withSessions(new Session(VALID_SESSION_LAB1)).build();

        @Override
        public void addSessions(ModuleClass moduleClass, Set<Session> sessions) {
        }

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }
    }
}
