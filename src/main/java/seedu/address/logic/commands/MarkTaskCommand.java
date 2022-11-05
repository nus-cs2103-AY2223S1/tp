package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * Marks a task as done.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as completed.\n"
            + "Parameters: {task_index} (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Task marked as completed: %1$s";
    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "This task is already marked as completed.";

    private final Index targetIndex;

    public MarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());

        if (taskToMark.isTaskComplete()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_COMPLETED);
        }

        Task markedTask = createMarkedTask(taskToMark);
        model.setTask(taskToMark, markedTask);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask), true);
    }

    /**
     * Creates and returns a {@code Task} with the status of {@code taskToMark} set to complete.
     */
    private static Task createMarkedTask(Task taskToMark) {
        assert taskToMark != null;

        TaskName name = taskToMark.getName();
        Module module = taskToMark.getModule();
        Deadline deadline = taskToMark.getDeadline();
        Status updatedStatus = new Status(true);

        return new Task(name, module, deadline, updatedStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((MarkTaskCommand) other).targetIndex)); // state check
    }
}
