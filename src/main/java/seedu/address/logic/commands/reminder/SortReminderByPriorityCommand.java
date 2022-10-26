package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sort reminders by their priority from "HIGH" to "LOW".
 * Reminders with same priority will be sorted lexicographically by their names.
 */
public class SortReminderByPriorityCommand extends Command {

    public static final String COMMAND_WORD = "sort reminder priority";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort reminders by their priority level ";

    public static final String MESSAGE_SUCCESS = "Reminders sorted by priority";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortReminderByPriority();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
