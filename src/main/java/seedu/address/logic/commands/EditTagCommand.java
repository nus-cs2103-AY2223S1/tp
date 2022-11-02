package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.Task;

/**
 * EditTagCommand represents the edit tag command which edits the priority status of priority
 * tag or the deadline of the deadline tag.
 */
public class EditTagCommand extends Command {
    public static final String COMMAND_WORD = "tagedit";
    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD + ": edits the tags of a task "
            + "in the task list.\n"
            + "Parameters: INDEX " + "[" + PREFIX_PRIORITY_STATUS + "PRIORITY_STATUS]* "
            + "[" + PREFIX_DEADLINE + "DEADLINE]*\n"
            + "Example: " + "t " + COMMAND_WORD + " 1 " + PREFIX_PRIORITY_STATUS + "HIGH";
    public static final String PRIORITY_TAG_DOES_NOT_EXIST = "The task does not have a priority tag.";
    public static final String DEADLINE_TAG_DOES_NOT_EXIST = "The task does not have a deadline tag.";
    public static final String PRIORITY_TAG_UNCHANGED = "The priority status provided is "
            + "the same as the current priority status for the task.";
    public static final String DEADLINE_TAG_UNCHANGED = "The deadline provided is"
            + " the same as the current deadline for the task.";
    private static final String TAG_EDITED_SUCCESSFULLY = "The tag(s) has/have been edited successfully.";

    private final Index index;
    private final PriorityTag priorityTag;
    private final DeadlineTag deadlineTag;

    /**
     * The constructor of the EditTagCommand. Sets the index, the priority tag
     * and the deadline tag.
     *
     * @param index The index of the task in the task list.
     * @param priorityTag The priority status of the priority tag.
     * @param deadlineTag The deadline of the deadline tag,.
     */
    public EditTagCommand(Index index, PriorityTag priorityTag,
            DeadlineTag deadlineTag) {
        requireNonNull(index);
        this.index = index;
        this.deadlineTag = deadlineTag;
        this.priorityTag = priorityTag;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> tasks = model.getFilteredTaskList();
        if (index.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task currTask = tasks.get(index.getZeroBased());
        checkTags(currTask);
        Task taggedTask = currTask;
        if (priorityTag != null) {
            taggedTask = taggedTask.replacePriorityTag(priorityTag);
        }
        if (deadlineTag != null) {
            taggedTask = taggedTask.replaceDeadlineTag(deadlineTag);
        }
        model.replaceTask(currTask, taggedTask, true);
        return new CommandResult(TAG_EDITED_SUCCESSFULLY);
    }

    private void checkTags(Task task) throws CommandException {
        boolean hasPriorityTag = task.hasPriorityTag();
        boolean hasDeadlineTag = task.hasDeadlineTag();
        PriorityTag currTaskPriorityTag = task.getPriorityTag();
        DeadlineTag currTaskDeadlineTag = task.getDeadlineTag();
        if (!hasPriorityTag && priorityTag != null) {
            throw new CommandException(PRIORITY_TAG_DOES_NOT_EXIST);
        }
        if (!hasDeadlineTag && deadlineTag != null) {
            throw new CommandException(DEADLINE_TAG_DOES_NOT_EXIST);
        }
        if (currTaskPriorityTag != null
                && priorityTag != null
                && currTaskPriorityTag.compareTo(priorityTag) == 0) {
            throw new CommandException(PRIORITY_TAG_UNCHANGED);
        }
        if (currTaskDeadlineTag != null
                && deadlineTag != null
                && currTaskDeadlineTag.compareTo(deadlineTag) == 0) {
            throw new CommandException(DEADLINE_TAG_UNCHANGED);
        }
    }
}
