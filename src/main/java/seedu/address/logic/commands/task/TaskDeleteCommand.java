package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "task delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index numbers (seperated by whitespace)"
            + " used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer and NOT TOO BIG)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index[] targetIndexes;

    public TaskDeleteCommand(Index[] targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        ArrayList<Task> tasksToDelete = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
            Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
            tasksToDelete.add(taskToDelete);
        }

        for (Task task : tasksToDelete) {
            model.deleteTask(task);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, tasksToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && Arrays.equals(this.targetIndexes, ((TaskDeleteCommand) other).targetIndexes)); // state check
    }
}
