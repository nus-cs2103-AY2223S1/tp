package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.stubs.ModelStubInFocusMode;
import seedu.taassist.model.stubs.ModelStubNeverInFocusMode;

class UnfocusCommandTest {

    @Test
    void execute_notInFocusMode_throwsCommandException() {
        UnfocusCommand unfocusCommand = new UnfocusCommand();
        ModelStubNeverInFocusMode modelStub = new ModelStubNeverInFocusMode();
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
}
