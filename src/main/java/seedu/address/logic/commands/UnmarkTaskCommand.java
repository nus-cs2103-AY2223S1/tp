package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class UnmarkTaskCommand extends Command{
    public final static String COMMAND_WORD = "unmarktask";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Task at %1$s set as uncompleted!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task at specified index in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public UnmarkTaskCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        model.unmarkTask(targetIndex);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, targetIndex.getOneBased()));
    }

}
