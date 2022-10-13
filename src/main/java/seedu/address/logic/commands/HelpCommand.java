package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Helps the user to understand what other commands do, and how to use it.
 * Command can be used on specific command (e.g. help add) or get an overview of all commands (e.g. help).
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the usage of commands"
            + "Usage: help [COMMAND]\n"
            + "Examples:\n"
            + "- help\n"
            + "- help add";

    // All commands summary
    public static final String ALL_COMMANDS_MESSAGE = "Here are all the valid commands:\n"
            + "add n/COMPANY loc/ADDRESS\n"
            + "create n/NAME coy/COMPANY hp/NUMBER e/EMAIL\n"
            + "buy INDEX q/QUANTITY g/GOODS price/PRICE\n"
            + "sell INDEX q/QUANTITY g/GOODS price/PRICE\n"
            + "edit INDEX a/NEW_ADDRESS\n"
            + "view INDEX\n"
            + "delete INDEX\n"
            + "find KEYWORD\n"
            + "exit\n"
            + "help\n"
            + "list\n"
            + "user_guide\n"
            + "clear";

    private final String helpMessage;

    public HelpCommand(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public HelpCommand() {
        this.helpMessage = ALL_COMMANDS_MESSAGE;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(helpMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && helpMessage.equals(((HelpCommand) other).helpMessage));
    }
}
