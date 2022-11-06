package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class SortCommandTest {
    Model model = new ModelManager();
    Model expectedModel = new ModelManager();
    private final Command commandToBeTested = new SortCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested);

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_noSubCommand_throwsError() {
        String resultString = String.format(
                Messages.MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT, SortCommand.COMMAND_WORD);
        assertThrows(CommandException.class, resultString, () -> commandToBeTested.execute(model));
    }

}