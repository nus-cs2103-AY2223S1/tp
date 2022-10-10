package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays all the members in the application
 */
public class DisplayUserCommand extends Command {

    public static final String COMMAND_WORD = "userlist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all the users in the application.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_DISPLAY_USER_MESSAGE = "Opened user window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_DISPLAY_USER_MESSAGE, false, false, true);
    }
}

