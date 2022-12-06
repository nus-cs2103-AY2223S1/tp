package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Helps the user to understand what other commands do, and how to use it.
 * Command can be used on specific command (e.g. help add) or get an overview of all commands (e.g. help).
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the usage of commands.\n"
            + "Parameters: [COMMAND]\n"
            + "Example: help add";

    // All commands summary
    public static final String ALL_COMMANDS_MESSAGE = "Here are all the valid commands:\n"
            + "1. add n/CLIENT a/ADDRESS p/PHONE e/EMAIL [t/TAG]...\n"
            + "2. buy INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]\n"
            + "3. sell INDEX q/QUANTITY g/GOODS price/PRICE [d/DATE]\n"
            + "4. edit INDEX m/MODE FIELDS [MORE_FIELDS]... (MODE must be 'client', 'remark' or 'transaction')\n"
            + "5. view INDEX\n"
            + "6. delete INDEX m/MODE (MODE must be 'client', 'remark' or 'transaction')\n"
            + "7. find KEYWORD [MORE_KEYWORDS]...\n"
            + "8. filter TYPE (TYPE must be 'buy' or 'sell')\n"
            + "9. exit\n"
            + "10. help [COMMAND]\n"
            + "11. list\n"
            + "12. remark INDEX REMARK\n"
            + "13. sort INDEX ORDER (ORDER must be 'latest' or 'oldest')\n"
            + "14. user_guide\n"
            + "15. clear";

    private final String helpMessage;

    public HelpCommand(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public HelpCommand() {
        this.helpMessage = ALL_COMMANDS_MESSAGE;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(helpMessage, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && helpMessage.equals(((HelpCommand) other).helpMessage));
    }
}
