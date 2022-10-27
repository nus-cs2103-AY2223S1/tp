package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class CalculatorGuiCommand extends Command {

    public static final String COMMAND_WORD = "calc-gui";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calculator in GUI.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CALC_MESSAGE = "Opened calculator.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CALC_MESSAGE, false, false, false, false, true);
    }
}
