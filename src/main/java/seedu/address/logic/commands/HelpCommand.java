package seedu.address.logic.commands;

import seedu.address.logic.commands.CommandResult.CommandType;
import seedu.address.model.Model;


/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows list of valid commands.\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "See 'help <command> to read about a specific command.\n"
            + "Example: " + COMMAND_WORD + " " + AddCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public CommandType commandType;

    public HelpCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public HelpCommand() {
        this.commandType = null;
    }

    @Override
    public CommandResult execute(Model model) {
        return this.commandType == null
                ? new CommandResult(SHOWING_HELP_MESSAGE, CommandType.HELP)
                : new CommandResult(SHOWING_HELP_MESSAGE, CommandType.HELP, commandType);
    }
}
