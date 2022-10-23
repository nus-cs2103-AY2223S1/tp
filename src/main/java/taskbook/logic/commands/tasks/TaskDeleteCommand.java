package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import java.util.List;

import taskbook.commons.core.Messages;
import taskbook.commons.core.index.Index;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task book.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Deletes the task identified by the index number.\n"
            + "\n"
            + "Parameters: " + CliSyntax.PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + TaskCategoryParser.CATEGORY_WORD + " "
            + COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + "1";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates a TaskDeleteCommand to delete a task with the specified {@code Index index}.
     *
     * @param targetIndex Index of the Task in the task book.
     */
    public TaskDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((TaskDeleteCommand) other).targetIndex)); // state check
    }
}
