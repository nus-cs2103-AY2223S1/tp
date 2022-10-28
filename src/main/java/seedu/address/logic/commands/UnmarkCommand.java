package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Unmark (label as incomplete) a task identified using it's displayed index from the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String FULL_COMMAND_WORD = "t unmark";


    public static final String MESSAGE_USAGE = FULL_COMMAND_WORD
        + ": Indicates the task at the specified INDEX in the displayed task list is completed.\n"
        + "Parameters: INDEX\n"
        + "Example: " + FULL_COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Successfully Unmarked Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_UNMARKED = "The task is already unmarked!";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        if (!taskToUnmark.isComplete()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_UNMARKED);
        }
        Task unmarkedTask = taskToUnmark.unmark();

        model.replaceTask(taskToUnmark, unmarkedTask, true);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UnmarkCommand // instanceof handles nulls
            && targetIndex.equals(((UnmarkCommand) other).targetIndex)); // state check
    }
}
