package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.Task;

/**
 * Marks a task as done. The task is identified using its displayed index from the task book.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "marktask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done. The task is identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked task as done: %1$s";

    private final Index taskIndex;

    /**
     * Creates a MarkTaskCommand to mark the task at the specified index as done.
     */
    public MarkTaskCommand(Index taskIndex) {
        requireNonNull(taskIndex);
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (taskIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(taskIndex.getZeroBased());
        taskToMark.markAsDone();
        model.setTask(taskToMark, taskToMark);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToMark));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof MarkTaskCommand)) { // instanceof handles nulls
            return false;
        }

        MarkTaskCommand otherMarkTask = (MarkTaskCommand) other;

        return taskIndex.equals(otherMarkTask.taskIndex);
    }
}

