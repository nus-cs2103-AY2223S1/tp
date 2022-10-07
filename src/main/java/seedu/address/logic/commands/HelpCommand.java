package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    private static final String USER_GUIDE_URL =
            "https://ay2223s1-cs2103t-f11-4.github.io/tp/UserGuide.html#quick-start";

    public static final String SHOWING_HELP_MESSAGE = "Sample usage:\n\t"
            + AddCommand.MESSAGE_USAGE + "\n\t"
            + DeleteCommand.MESSAGE_USAGE + "\n\t"
            + EditCommand.MESSAGE_USAGE + "\n\t"
            + FindCommand.MESSAGE_USAGE + "\n\t"
            + HelpCommand.MESSAGE_USAGE + "\n\n"
            + "Other Commands:\n\t"
            + ClearCommand.COMMAND_WORD + "\n\t"
            + ListCommand.COMMAND_WORD + "\n\t"
            + ExitCommand.COMMAND_WORD + "\n\n"
            + "Further help:\n"
            + USER_GUIDE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
