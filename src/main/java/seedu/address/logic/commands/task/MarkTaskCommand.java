package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandUtil;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task in the tasklist as completed.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "markT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as complete the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";
    public static final String MESSAGE_ALREADY_MARKED = "This task is already marked as completed.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an MarkTaskCommand object.
     * @param index of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public MarkTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(index.getZeroBased());
        Task markedTask = CommandUtil.createEditedTask(taskToMark, editTaskDescriptor);

        if (!taskToMark.isSameTask(markedTask) && model.hasTask(markedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (taskToMark.equals(markedTask)) {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }

        model.setTask(taskToMark, markedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        // state check
        MarkTaskCommand m = (MarkTaskCommand) other;
        return index.equals(m.index)
                && editTaskDescriptor.equals(m.editTaskDescriptor);
    }
}
