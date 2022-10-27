package seedu.address.logic.commands;

import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.model.Model;

/**
 * Format full help instructions for all add commands for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    private static final String USER_GUIDE_URL =
            "https://ay2223s1-cs2103t-f11-4.github.io/tp/UserGuide.html#quick-start";

    public static final String SHOWING_HELP_MESSAGE = "Sample usage:\n\n"
            + AddContactCommand.MESSAGE_USAGE + "\n\n"
            + AddTaskCommand.MESSAGE_USAGE + "\n\n"
            + AddTagCommand.MESSAGE_USAGE + "\n\n"
            + "For other commands and further help:\n"
            + USER_GUIDE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
