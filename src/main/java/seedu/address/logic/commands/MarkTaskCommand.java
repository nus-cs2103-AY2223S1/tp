package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks the task at the specified index to be completed.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "marktask";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Task at index %1$s set as completed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task at specified index of the currently displayed tasks.\n"
            + "Parameters: INDEX (must be a number from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public MarkTaskCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        model.markTask(targetIndex);

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((MarkTaskCommand) other).targetIndex)); // state check
    }

}
