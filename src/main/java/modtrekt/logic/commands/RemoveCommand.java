package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import modtrekt.commons.core.Messages;
import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Removed Task: %1$s";

    private final Index targetIndex;

    public RemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)); // state check
    }
}
