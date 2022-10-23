package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

/**
 * Marks a specified task as incomplete.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the specified existing task as incomplete.\n"
        + "Parameters: INDEX of task (must be a positive integer) \n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_SUCCESS = "Marked as incomplete: [ ] %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_ALREADY_UNMARKED = "This task has not been marked as done.";
    private final int taskIndex;

    /**
     * Returns a command that adds a task to the current team.
     * @param taskIndex The index of the task to be added.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        if (taskIndex >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex + 1));
        }
        if (!taskList.get(taskIndex).isComplete()) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }
        taskList.get(taskIndex).mark(false);
        return new CommandResult(String.format(MESSAGE_MARK_SUCCESS,
                taskList.get(taskIndex).getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UnmarkCommand // instanceof handles nulls
            && taskIndex == (((UnmarkCommand) other).taskIndex)); // state check
    }
}
