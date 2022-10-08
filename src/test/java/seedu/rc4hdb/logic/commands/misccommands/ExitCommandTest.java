package seedu.rc4hdb.logic.commands.misccommands;

import static seedu.rc4hdb.logic.commands.misccommands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.rc4hdb.logic.commands.misccommands.MiscCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.CommandResult;

public class ExitCommandTest {

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), expectedCommandResult);
    }

}
