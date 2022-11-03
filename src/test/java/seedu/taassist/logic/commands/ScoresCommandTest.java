package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_SESSION;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_TUT3;
import static seedu.taassist.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;

class ScoresCommandTest {

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScoresCommand(null));
    }

    @Test
    void execute_notInFocusMode_throwsException() {
        ScoresCommand scoresCommand = new ScoresCommand(new Session(VALID_SESSION_LAB1));
        ModelStubNeverInFocusMode modelStub = new ModelStubNeverInFocusMode();
        String expectedMessage =
                String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, ScoresCommand.COMMAND_WORD);
        assertThrows(CommandException.class, expectedMessage, () -> scoresCommand.execute(modelStub));
    }

    @Test
    void execute_sessionDoesNotExist_throwsException() {
        // Focused class has no sessions.
        ScoresCommand scoresCommand = new ScoresCommand(new Session(VALID_SESSION_LAB1));
        ModelStubInFocusMode modelStub = new ModelStubInFocusMode();
        String expectedMessage =
                String.format(MESSAGE_INVALID_SESSION, VALID_SESSION_LAB1, modelStub.getFocusedClass());
        assertThrows(CommandException.class, expectedMessage, () -> scoresCommand.execute(modelStub));

        // Focused class has sessions, but not the one being queried.
        ModelStubInFocusMode modelStub2 = new ModelStubInFocusMode(new Session(VALID_SESSION_TUT3));
        String expectedMessage2 =
                String.format(MESSAGE_INVALID_SESSION, VALID_SESSION_LAB1, modelStub2.getFocusedClass());
        assertThrows(CommandException.class, expectedMessage2, () -> scoresCommand.execute(modelStub));
    }

    @Test
    void execute_sessionExists_success() throws CommandException {
        Session lab1Session = new Session(VALID_SESSION_LAB1);
        ScoresCommand scoresCommand = new ScoresCommand(lab1Session);
        ModelStubInFocusMode modelStub = new ModelStubInFocusMode(lab1Session);

        CommandResult commandResult = scoresCommand.execute(modelStub);

        assertEquals(String.format(ScoresCommand.MESSAGE_SUCCESS, lab1Session.getSessionName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    void equals() {
        Session session = new Session("Midterms");
        ScoresCommand scoresCommand = new ScoresCommand(session);

        // same object -> returns true
        assertTrue(scoresCommand.equals(scoresCommand));

        // same value -> returns true
        ScoresCommand scoresCommandCopy = new ScoresCommand(session);
        assertTrue(scoresCommand.equals(scoresCommandCopy));

        // different types -> returns false
        assertFalse(scoresCommand.equals(new UnfocusCommand()));

        // null -> returns false
        assertFalse(scoresCommand.equals(null));

        // different session -> returns false
        Session differentSession = new Session("Finals");
        assertFalse(scoresCommand.equals(new ScoresCommand(differentSession)));
    }

    /**
     * A Model stub that pretends to be in focus mode.
     */
    private static class ModelStubInFocusMode extends ModelStub {

        private ModuleClass focusedClass;

        // Default constructor. Doesn't contain any sessions.
        public ModelStubInFocusMode() {
            super();
            this.focusedClass = new ModuleClass("CS2103T");
        }

        // Constructor that adds a single Session to the focused class.
        public ModelStubInFocusMode(Session session) {
            super();
            ArrayList<Session> sessionList = new ArrayList<>(1);
            sessionList.add(session);
            this.focusedClass = new ModuleClass("CS2103T", sessionList);
        }

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }

        @Override
        public void querySessionData(Session target) {

        }
    }
}
