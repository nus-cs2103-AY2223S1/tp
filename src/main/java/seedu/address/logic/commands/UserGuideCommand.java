package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class UserGuideCommand extends Command {

    public static final String COMMAND_WORD = "user_guide";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the user guide url for the program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened User Guide window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
