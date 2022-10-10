package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Gets reminders of all upcoming birthdays for the user.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets remind for upcoming birthdays.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remind command not implemented yet!";

    public static final String SHOWING_REMINDER_MESSAGE = "Displayed reminders.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(SHOWING_REMINDER_MESSAGE, true);
    }
}
