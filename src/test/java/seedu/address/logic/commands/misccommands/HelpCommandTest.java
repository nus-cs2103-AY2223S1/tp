package seedu.address.logic.commands.misccommands;

import static seedu.address.logic.commands.misccommands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.logic.commands.misccommands.MiscCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;

public class HelpCommandTest {

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), expectedCommandResult);
    }

}
