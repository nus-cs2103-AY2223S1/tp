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
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.Task;

/**
 * Unmarks the completion status of an existing task in the task book.
 */
public class TaskUnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
                    + ": Marks the task identified by the index number as undone.\n"
                    + "Parameters: " + CliSyntax.PREFIX_INDEX + "INDEX (must be a positive integer)\n"
                    + "Example: " + TaskCategoryParser.CATEGORY_WORD + " "
                    + COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + "1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Task unmarked: %1$s";

    private final Index targetIndex;

    /**
     * Creates a TaskUnmarkCommand to mark a task with the specified {@code Index index}.
     *
     * @param targetIndex Index of the Task in the task book.
     */
    public TaskUnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getSortedTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setIsDone(false);
        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Task unmarkedTask = taskToUnmark.createEditedCopy(editTaskDescriptor);
        model.setTask(taskToUnmark, unmarkedTask);
        model.updateFilteredTaskListPredicate(Model.PREDICATE_SHOW_ALL_TASKS);
        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskUnmarkCommand // instanceof handles nulls
                && targetIndex.equals(((TaskUnmarkCommand) other).targetIndex)); // state check
    }
}
