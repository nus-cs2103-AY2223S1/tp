package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CalculatorGuiCommand.SHOWING_CALC_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;

public class CalculatorGuiCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager();
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager();

    @Test
    public void execute_calc_gui_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_CALC_MESSAGE, false, false, false, false, true);
        assertCommandSuccess(new CalculatorGuiCommand(), bobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }

    @Test
    public void equals_same_object() {
        final CalculatorGuiCommand calculatorGuiCommand = new CalculatorGuiCommand();
        assertEquals(calculatorGuiCommand, calculatorGuiCommand);
    }

    @Test
    public void equals_different_object() {
        final CalculatorGuiCommand cmd1 = new CalculatorGuiCommand();
        final CalculatorGuiCommand cmd2 = new CalculatorGuiCommand();
        assertEquals(cmd1, cmd2);
    }
}
