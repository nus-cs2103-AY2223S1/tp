package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult("Opened help window.", true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpWithValidInput_success() {
        ListCommand testListCommand = new ListCommand();
        CommandResult expectedCommandResult = new CommandResult(testListCommand.MESSAGE_USAGE, false, false);
        assertCommandSuccess(new HelpCommand(testListCommand.MESSAGE_USAGE), model, expectedCommandResult,
                expectedModel);
    }
}
