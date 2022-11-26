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
    public static final String MESSAGE_EXAM_UNLINKED_SUCCESS = "The exam has been successfully unlinked from the task!";
    public static final String MESSAGE_TASK_ALREADY_UNLINKED = "The task is already not linked to any exam.";
    public static final String MESSAGE_NO_TASK_IN_LIST =
            "There is no task in the task list so unlink operation cannot be done!";
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

        if (tasks.size() == 0) {
            throw new CommandException(MESSAGE_NO_TASK_IN_LIST);
        }

        if (taskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, tasks.size() + 1));
        }

        Task toUnlink = tasks.get(taskIndex.getZeroBased());

        if (!toUnlink.isLinked()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_UNLINKED);
        }

        Task unlinkedTask = toUnlink.unlinkTask();
        model.replaceTask(toUnlink, unlinkedTask, true);
        return new CommandResult(MESSAGE_EXAM_UNLINKED_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnlinkExamCommand // instanceof handles nulls
                && taskIndex.equals(((UnlinkExamCommand) other).taskIndex)); // state check
    }
}
