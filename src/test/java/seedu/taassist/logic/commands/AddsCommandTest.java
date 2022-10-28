package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;
import seedu.taassist.testutil.ModuleClassBuilder;
import seedu.taassist.testutil.SessionBuilder;


public class AddsCommandTest {

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
}
