package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

/**
 * Marks a specified task as complete.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the specified existing task as complete.\n"
        + "Parameters: INDEX of task (must be a positive integer) \n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_SUCCESS = "Marked as complete: [x] %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist. "
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_ALREADY_MARKED = "This task has already been marked as complete.";
    private final int taskIndex;

    /**
     * Returns a command that adds a task to the current team.
     * @param taskIndex The index of the task to be added.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        if (taskIndex >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex + 1));
        }
        if (taskList.get(taskIndex).isComplete()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }
        taskList.get(taskIndex).mark(true);
        return new CommandResult(String.format(MESSAGE_MARK_SUCCESS,
                taskList.get(taskIndex).getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MarkCommand // instanceof handles nulls
            && taskIndex == (((MarkCommand) other).taskIndex)); // state check
    }
}
