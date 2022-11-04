package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.task.Task;

/**
 * DeleteTaskCommand is the command that deletes a task from the task list at the specified index.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deltask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a number from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";

    private final Index targetIndex;

    public DeleteTaskCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Task> taskToDelete = model.getTaskWithIndex(targetIndex);
        Task toDelete = taskToDelete.orElseThrow(() ->
                new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX));

        model.deleteTask(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
