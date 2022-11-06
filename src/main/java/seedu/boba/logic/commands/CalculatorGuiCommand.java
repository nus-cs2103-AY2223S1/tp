package seedu.boba.logic.commands;

import seedu.boba.model.BobaBotModel;


/**
 * Format full help instructions for every command for display.
 */
public class CalculatorGuiCommand extends Command {

    public static final String COMMAND_WORD = "calc-gui";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calculator in GUI.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CALC_MESSAGE = "Opened calculator.";

    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) {
        return new CommandResult(SHOWING_CALC_MESSAGE, false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof CalculatorGuiCommand; // instanceof handles nulls
    }
}
