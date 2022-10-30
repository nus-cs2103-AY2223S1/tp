package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task from Contactmation.
 *
 * @author connlim
 * @author mohamedsaf1
 */
public class DeleteTaskCommand extends TaskCommand {
    public static final String SUBCOMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = TaskCommand.getFullCommand(SUBCOMMAND_WORD)
        + ": Delete the selected task\n"
        + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1\n";

    public static final String DELETE_SUCCESS = " task %s is deleted%n";

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();
        if (targetIndex == null && task == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        if (task == null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            task = lastShownList.get(targetIndex.getZeroBased());
        }

        model.deleteTask(task);
        return new CommandResult(String.format(DELETE_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        DeleteTaskCommand c = (DeleteTaskCommand) other;
        if (targetIndex == null) {
            if (c.targetIndex != null) {
                return false;
            }
        } else if (!targetIndex.equals(c.targetIndex)) {
            return false;
        }

        if (task == null) {
            if (c.task != null) {
                return false;
            }
        } else if (!task.equals(c.task)) {
            return false;
        }

        return true;
    }

}
