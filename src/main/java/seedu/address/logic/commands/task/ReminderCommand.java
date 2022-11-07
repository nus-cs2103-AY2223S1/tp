package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskUntilDeadlinePredicate;

/**
 * Lists all tasks with deadlines up to and including the given deadline
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "remindT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tasks with deadlines up to and including the "
            + "date provided.\n"
            + "Parameters: DEADLINE (must be in dd-mm-yyyy format)\n"
            + "Example: " + COMMAND_WORD + " "
            + "12-09-2022";

    public static final String MESSAGE_REMINDER_SUCCESS = "Here are your tasks with deadlines up to including the "
                                                    + "specified date.";

    private final TaskUntilDeadlinePredicate predicate;

    /**
     * Creates a ReminderCommand to with the specified {@code TaskUntilDeadlinePredicate}
     * @param predicate
     */
    public ReminderCommand(TaskUntilDeadlinePredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(MESSAGE_REMINDER_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderCommand // instanceof handles nulls
                && predicate.equals(((ReminderCommand) other).predicate));
    }

}
