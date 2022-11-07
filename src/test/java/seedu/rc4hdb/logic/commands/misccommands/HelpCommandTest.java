package seedu.rc4hdb.logic.commands.misccommands;

import static seedu.rc4hdb.logic.commands.misccommands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.rc4hdb.logic.commands.misccommands.MiscCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.CommandResult;

public class HelpCommandTest {

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), expectedCommandResult);
    }

}
