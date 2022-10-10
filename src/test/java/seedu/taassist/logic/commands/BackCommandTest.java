package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.ModelStub;
import seedu.taassist.model.moduleclass.ModuleClass;

class BackCommandTest {

    @Test
    void execute_notInFocusMode_throwsCommandException() throws CommandException {
        BackCommand backCommand = new BackCommand();
        ModelStubNotInFocusMode modelStub = new ModelStubNotInFocusMode();
        String expectedMessage = String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, BackCommand.COMMAND_WORD);
        assertThrows(CommandException.class, expectedMessage, () -> backCommand.execute(modelStub));
    }

    @Test
    void execute_inFocusMode_success() throws CommandException {
        BackCommand backCommand = new BackCommand();
        ModelStubInFocusMode modelStub = new ModelStubInFocusMode();

        CommandResult commandResult = backCommand.execute(modelStub);

        assertEquals(String.format(BackCommand.MESSAGE_SUCCESS, modelStub.getFocusedClass()),
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
