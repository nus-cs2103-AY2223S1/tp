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
 * Marks a reminder in ModQuik as done.
 */
public class MarkReminderCommand extends Command {

    public static final String COMMAND_WORD = "mark reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the reminder identified by the index number used in the reminder list as complete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_REMINDER_SUCCESS = "Reminder is marked as completed: %1$s";
    public static final String MESSAGE_REMINDER_ALREADY_MARKED = "You have already completed this reminder.";

    private final Index targetIndex;

    public MarkReminderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> lastShownList = model.getFilteredReminderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToMark = lastShownList.get(targetIndex.getZeroBased());
        if (model.reminderIsMarked(reminderToMark)) {
            throw new CommandException(MESSAGE_REMINDER_ALREADY_MARKED);
        }

        model.markReminder(reminderToMark);
        return new CommandResult(String.format(MESSAGE_MARK_REMINDER_SUCCESS, reminderToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkReminderCommand // instanceof handles nulls
                && targetIndex.equals(((MarkReminderCommand) other).targetIndex)); // state check
    }

}
