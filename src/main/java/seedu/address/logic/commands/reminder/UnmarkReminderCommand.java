package seedu.address.logic.commands.reminder;
import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Unmarks a reminder in ModQuik as undone.
 */
public class UnmarkReminderCommand extends Command {

    public static final String COMMAND_WORD = "unmark reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the reminder identified by the index number used in the reminder list as incomplete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_REMINDER_SUCCESS = "Reminder is unmarked as incomplete: %1$s";
    public static final String MESSAGE_REMINDER_ALREADY_UNMARKED = "Reminder is yet to be done. "
            + "There is nothing to unmark.";

    private final Index targetIndex;

    public UnmarkReminderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> lastShownList = model.getFilteredReminderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToUnmark = lastShownList.get(targetIndex.getZeroBased());
        if (!model.reminderIsMarked(reminderToUnmark)) {
            throw new CommandException(MESSAGE_REMINDER_ALREADY_UNMARKED);
        }

        model.unmarkReminder(reminderToUnmark);
        return new CommandResult(String.format(MESSAGE_UNMARK_REMINDER_SUCCESS, reminderToUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkReminderCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkReminderCommand) other).targetIndex)); // state check
    }
}
