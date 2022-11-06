package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new ClearCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested);
    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_emptyTruthTable_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyTruthTable_success() {
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

}
