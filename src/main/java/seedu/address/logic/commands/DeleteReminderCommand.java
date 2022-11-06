package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;

/**
 * Deletes a reminder identified using it's displayed index from the reminder list.
 */
public class DeleteReminderCommand extends Command {

    public static final String COMMAND_WORD = "deleteR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reminder identified by the index number used in the displayed reminder list\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Deleted Reminder: %1$s";

    private final Index targetIndex;

    /**
     * @param targetIndex Index of the person in the filtered person list to delete
     */
    public DeleteReminderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert (targetIndex != null) : "targetIndex should not be null";

        Reminder deletedReminder = model.deleteReminder(targetIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS, deletedReminder));
    }
}

