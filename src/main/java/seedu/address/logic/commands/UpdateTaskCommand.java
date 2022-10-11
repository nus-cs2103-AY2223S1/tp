package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Updates a task to the taskList at the specified index with specified details.
 */
public class UpdateTaskCommand extends Command {
    public static final String COMMAND_WORD = "updateTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates a task in the taskList\n"
            + "Parameters: Index, Task\n"
            + "Example: " + COMMAND_WORD + "1 d/Purchase milk dl/2022-10-18 t/Food";
    public static final String MESSAGE_UPDATE_TASK_SUCCESS = "Task update complete: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Index index;
    private final Task task;

    /**
     * Creates an UpdateTaskCommand to add the specified {@code Task} at the specified {@code Index}.
     */
    public UpdateTaskCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);
        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (model.hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(task, index);
        return new CommandResult(String.format(MESSAGE_UPDATE_TASK_SUCCESS, task));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateTaskCommand // instanceof handles nulls
                && index.equals(((UpdateTaskCommand) other).index) && task.equals(((UpdateTaskCommand) other).task));
    }
}
