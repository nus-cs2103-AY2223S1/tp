package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.moduleclass.ModuleClass;

class UnfocusCommandTest {

    @Test
    void execute_notInFocusMode_throwsCommandException() {
        UnfocusCommand unfocusCommand = new UnfocusCommand();
        ModelStubNotInFocusMode modelStub = new ModelStubNotInFocusMode();
        String expectedMessage = String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, UnfocusCommand.COMMAND_WORD);
        assertThrows(CommandException.class, expectedMessage, () -> unfocusCommand.execute(modelStub));
    }

    @Test
    void execute_inFocusMode_success() throws CommandException {
        UnfocusCommand unfocusCommand = new UnfocusCommand();
        ModelStubInFocusMode modelStub = new ModelStubInFocusMode();

        CommandResult commandResult = unfocusCommand.execute(modelStub);

        assertEquals(String.format(UnfocusCommand.MESSAGE_SUCCESS, modelStub.getFocusedClass()),
                commandResult.getFeedbackToUser());
        assertFalse(modelStub.isInFocusMode());
    }

    /**
     * A Model stub that is always not in focus mode.
     */
    private class ModelStubNotInFocusMode extends ModelStub {

        @Override
        public boolean isInFocusMode() {
            return false;
        }
    }

    /**
     * A Model stub that is focusing on a class.
     */
    private class ModelStubInFocusMode extends ModelStub {

        private boolean inFocusMode = true;

        @Override
        public boolean isInFocusMode() {
            return inFocusMode;
        }

        @Override
        public void exitFocusMode() {
            inFocusMode = false;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return new ModuleClass("CS2103T");
        }
    }

}
