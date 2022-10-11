package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a specific member in the team.
 */
public class SetDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "set_deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sets a deadline to the specified existing task.\n"
        + "Parameters: INDEX of task (must be a positive integer) \n"
            + "Parameters: Deadline (format: YYYY-MM-DD) \n"
        + "Example: " + COMMAND_WORD + " 1" + " 2023-02-25";

    public static final String MESSAGE_SET_DEADLINE_SUCCESS = "Set Deadline: %1$s %2$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    private final int taskIndex;
    private final LocalDate deadline;

    /**
     * Returns a command that adds a task to the current team.
     * @param taskIndex the name of the task to be added.
     * @param deadline deadline of task
     * @throws ParseException when the task name does not follow the format specified.
     */
    public SetDeadlineCommand(int taskIndex, LocalDate deadline) {
        this.taskIndex = taskIndex;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        if (taskIndex >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex + 1));
        }
        taskList.get(taskIndex).setDeadline(deadline);
        return new CommandResult(String.format(MESSAGE_SET_DEADLINE_SUCCESS,
                taskList.get(taskIndex).getName(), taskList.get(taskIndex).getDeadline()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SetDeadlineCommand // instanceof handles nulls
            && taskIndex == (((SetDeadlineCommand) other).taskIndex))
                && deadline.equals(((SetDeadlineCommand) other).deadline); // state check
    }
}
