package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.parser.TruthTableParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    Model model = new ModelManager();

    Model expectedModel = new ModelManager();
    private Command commandToBeTested;

    private CommandLine commandLine;

    @BeforeEach
    public void setUp() throws Exception {
        commandToBeTested = (HelpCommand) new TruthTableParser().parseCommand(HelpCommand.COMMAND_WORD);
        commandLine = new CommandLine(commandToBeTested);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_help_success() {

    }
}
