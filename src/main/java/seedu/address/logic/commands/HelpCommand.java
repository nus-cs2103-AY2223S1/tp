package seedu.address.logic.commands;

import seedu.address.logic.commands.CommandResult.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String INVALID_COMMAND_TYPE = "Unable to execute help for this command type.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the usage of valid commands.\n"
            + "\nKey in help [command] to see the exact usage of the specified command.\n"
            + "\nUsage:\n"
            + "help add\n"
            + "help assign\n"
            + "help clear\n"
            + "help delete\n"
            + "help edit\n"
            + "help exit\n"
            + "help find\n"
            + "help list\n"
            + "help nok\n"
            + "help show\n"
            + "help sort\n"
            + "help unassign\n"
            + "\n Please refer to our user guide for full information.\n";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private CommandType commandType;

    public HelpCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public HelpCommand() {
        this.commandType = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.commandType == null) {
            return new CommandResult(SHOWING_HELP_MESSAGE, CommandType.HELP);
        }

        if (this.commandType == CommandType.EXIT
                || this.commandType == CommandType.HELP
                || this.commandType == CommandType.OTHER) {
            throw new CommandException(String.format(INVALID_COMMAND_TYPE));
        }

        return this.commandType == null
                ? new CommandResult(SHOWING_HELP_MESSAGE, CommandType.HELP)
                : new CommandResult(SHOWING_HELP_MESSAGE, CommandType.HELP, commandType);
    }
}
