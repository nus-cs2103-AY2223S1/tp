package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * UnlinkExamCommand represents a command which unlinks the exam from the task.
 */
public class UnlinkExamCommand extends Command {
    public static final String COMMAND_WORD = "unlink";
    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD
            + ": Unlinks the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX\n"
            + "Example: e " + COMMAND_WORD + " 1";
    public static final String EXAM_UNLINKED_SUCCESS = "The exam has been successfully unlinked from the task!";
    public static final String TASK_ALREADY_UNLINKED = "The task is already not linked to any exam.";
    private final Index taskIndex;

    /**
     * The constructor of the UnlinkExamCommand class.
     *
     * @param taskIndex The index of the task to be unlinked.
     */
    public UnlinkExamCommand(Index taskIndex) {
        requireNonNull(taskIndex);
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> tasks = model.getFilteredTaskList();

        if (taskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task toUnlink = tasks.get(taskIndex.getZeroBased());

        if (!toUnlink.isLinked()) {
            throw new CommandException(TASK_ALREADY_UNLINKED);
        }

        Task unlinkedTask = toUnlink.unlinkTask();
        model.replaceTask(toUnlink, unlinkedTask, true);
        return new CommandResult(EXAM_UNLINKED_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnlinkExamCommand // instanceof handles nulls
                && taskIndex.equals(((UnlinkExamCommand) other).taskIndex)); // state check
    }
}
