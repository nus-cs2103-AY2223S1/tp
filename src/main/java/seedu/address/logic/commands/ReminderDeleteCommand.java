package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;
import seedu.address.ui.SecondaryPaneState;

/**
 *  Deletes a reminder in the reminder list.
 */
public class ReminderDeleteCommand extends ReminderCommandGroup {
    public static final String COMMAND_SPECIFIER = "delete";
    public static final String COMMAND_SPECIFIER_ALIAS = "d";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a reminder from the currently displayed reminder list using its index.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Reminder deleted:\n%1$s";

    private final Index index;

    /**
     * @param index of the reminder in the reminder list to delete
     */
    public ReminderDeleteCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Reminder> reminderList = model.getCurrentReminderList();

        if (index.getZeroBased() >= reminderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder toDelete = reminderList.get(index.getZeroBased());
        model.deleteReminder(toDelete);

        if (!model.hasTargetPerson()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete), SecondaryPaneState.WELCOME);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderDeleteCommand)) {
            return false;
        }

        // state check
        ReminderDeleteCommand o = (ReminderDeleteCommand) other;
        return index.equals(o.index);
    }
}
