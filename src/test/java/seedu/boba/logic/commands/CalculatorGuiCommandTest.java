package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.boba.logic.commands.CalculatorGuiCommand.SHOWING_CALC_MESSAGE;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

public class CalculatorGuiCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager();
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_calcGui_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_CALC_MESSAGE, false, false, false, false, true);
        assertCommandSuccess(new CalculatorGuiCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }

    @Test
    public void equals() {
        final CalculatorGuiCommand calculatorGuiCommand = new CalculatorGuiCommand();
        assertEquals(calculatorGuiCommand, calculatorGuiCommand);

        final CalculatorGuiCommand cmd1 = new CalculatorGuiCommand();
        final CalculatorGuiCommand cmd2 = new CalculatorGuiCommand();
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void not_equals() {
        final CalculatorGuiCommand calculatorGuiCommand = new CalculatorGuiCommand();

        assertNotEquals(null, calculatorGuiCommand);

        final HelpCommand helpCommand = new HelpCommand();
        assertNotEquals(helpCommand, calculatorGuiCommand);

        final ExitCommand exitCommand = new ExitCommand();
        assertNotEquals(exitCommand, calculatorGuiCommand);

        final CalculateCommand calculateCommand = new CalculateCommand("1+1");
        assertNotEquals(calculateCommand, calculatorGuiCommand);
    }
}
