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
 * Marks a task in the tasklist as incomplete.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmarkT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as incomplete the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Unmarked Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";
    public static final String MESSAGE_ALREADY_UNMARKED = "This task is already marked as incomplete.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an UnmarkTaskCommand object.
     * @param index of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public UnmarkTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
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

        Task taskToUnmark = lastShownList.get(index.getZeroBased());
        Task unmarkedTask = CommandUtil.createEditedTask(taskToUnmark, editTaskDescriptor);

        if (!taskToUnmark.isSameTask(unmarkedTask) && model.hasTask(unmarkedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (taskToUnmark.equals(unmarkedTask)) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }

        model.setTask(taskToUnmark, unmarkedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        // state check
        UnmarkTaskCommand u = (UnmarkTaskCommand) other;
        return index.equals(u.index)
                && editTaskDescriptor.equals(u.editTaskDescriptor);
    }
}
