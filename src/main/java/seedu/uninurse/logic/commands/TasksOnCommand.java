package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.task.DateTime;

/**
 * Shows all tasks that are for a particular day.
 */
public class TasksOnCommand extends DisplayTasksGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of tasks for the given date. The date must be in dd-mm-yy format.\n"
            + "Format: " + COMMAND_WORD + " DATE\n"
            + "Example: " + COMMAND_WORD + " 25-4-22";
    public static final String MESSAGE_SUCCESS = "Showing Tasks for %1$s";
    public static final CommandType COMMAND_TYPE = CommandType.SCHEDULE;

    private final DateTime dayToCheck;

    /**
     * Constructs a TasksOnCommand.
     *
     * @param dateTime for the TasksOnCommand.
     */
    public TasksOnCommand(DateTime dateTime) {
        requireAllNonNull(dateTime);
        this.dayToCheck = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        model.setSchedule(new Schedule(model.getPatientList(), dayToCheck));

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
        TasksOnCommand o = (TasksOnCommand) other;
        return dayToCheck.equals(o.dayToCheck);
    }
}
