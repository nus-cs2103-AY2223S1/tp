package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task in the taskList at the specified Index.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deleteTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a task in taskList\n"
            + "Parameters: Index\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task delete complete: %1$s";

    public static final String MESSAGE_NOT_EXIST_TASK = "There is no such task";

    private final Index index;

    /**
     * Creates a DeleteTaskCommand to delete task at the specified {@code Index}.
     */
    public DeleteTaskCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task task = lastShownTaskList.get(index.getZeroBased());
        model.deleteTask(index);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && index.equals(((DeleteTaskCommand) other).index));
    }
}
