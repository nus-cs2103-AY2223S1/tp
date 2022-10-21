package seedu.workbook.logic.commands;

import seedu.workbook.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    /** Command word to execute the help command */
    public static final String COMMAND_WORD = "help";

    /** Help message to execute the help command */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /** Message string displaying successful execution of the help command */
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
