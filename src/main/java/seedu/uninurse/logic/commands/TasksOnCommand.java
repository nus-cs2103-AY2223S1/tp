package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.task.DateTime;

/**
 * Shows all tasks that are for a particular day.
 */
public class TasksOnCommand extends DisplayTasksGenericCommand {

    public static final String COMMAND_WORD = "tasksOn";

    public static final CommandType COMMAND_TYPE = CommandType.SCHEDULE;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of tasks for that day";

    public static final String MESSAGE_SUCCESS = "Showing Tasks for %1$s";

    private final DateTime dayToCheck;

    /**
     * Creates a TasksOnCommand to get the tasks on the specified {@code DateTime}.
     */
    public TasksOnCommand(DateTime dateTime) {
        requireNonNull(dateTime);
        dayToCheck = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.setDayOfInterest(dayToCheck);

        return new CommandResult(String.format(MESSAGE_SUCCESS, dayToCheck.getDate()), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TasksOnCommand)) {
            return false;
        }

        // state check
        TasksOnCommand e = (TasksOnCommand) other;
        return dayToCheck.equals(e.dayToCheck);
    }
}
