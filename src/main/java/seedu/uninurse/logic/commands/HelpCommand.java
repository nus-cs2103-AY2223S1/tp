package seedu.uninurse.logic.commands;

import seedu.uninurse.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Opened help window.";
    public static final CommandType HELP_COMMAND_TYPE = CommandType.HELP;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, HELP_COMMAND_TYPE);
    }
}
