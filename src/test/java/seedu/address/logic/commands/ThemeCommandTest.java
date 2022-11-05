package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ThemeCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new ThemeCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested);

    @Test
    public void execute_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedResult = new CommandResult(ThemeCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false,
                false, true);
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
