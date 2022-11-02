package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
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
 * AddTagCommand represents a command which adds a tag to the existing task.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "tagadd";
    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD + ": tags a task in the task list.\n"
            + "Parameters: INDEX " + "[" + PREFIX_PRIORITY_STATUS + "PRIORITY_STATUS]* "
            + "[" + PREFIX_DEADLINE + "DEADLINE]*\n"
            + "Example: " + "t " + COMMAND_WORD + " 1"
            + " " + PREFIX_PRIORITY_STATUS + "HIGH";

    public static final String TAG_ADDED_SUCCESS = "The tag(s) has/have been added successfully";

    public static final String PRIORITY_TAG_ALREADY_EXIST = "The priority tag already exists";
    public static final String DEADLINE_TAG_ALREADY_EXIST = "The deadline tag already exists";

    private final PriorityTag priorityTag;
    private final DeadlineTag deadlineTag;
    private final Index index;

    /**
     * Constructor of the AddTagCommand. Sets the Priority tag, Deadline tag and
     * the index to add the tag.
     *
     * @param priorityTag The priority tag to be added.
     * @param deadlineTag The deadline tag to be added.
     * @param index The index of the tag.
     */
    public AddTagCommand(PriorityTag priorityTag, DeadlineTag deadlineTag, Index index) {
        requireNonNull(index);
        this.priorityTag = priorityTag;
        this.deadlineTag = deadlineTag;
        this.index = index;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Task taggedTask = null;
        List<Task> taskList = model.getFilteredTaskList();
        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task currentTask = taskList.get(index.getZeroBased());
        if (priorityTag != null) {
            if (currentTask.hasPriorityTag()) {
                throw new CommandException(PRIORITY_TAG_ALREADY_EXIST);
            }
            taggedTask = currentTask.setPriorityTag(priorityTag);
        }

        if (deadlineTag != null) {
            if (currentTask.hasDeadlineTag()) {
                throw new CommandException(DEADLINE_TAG_ALREADY_EXIST);
            }
            taggedTask = requireNonNullElse(taggedTask, currentTask).setDeadlineTag(deadlineTag);
        }
        model.replaceTask(currentTask, taggedTask, true);
        return new CommandResult(TAG_ADDED_SUCCESS);
    }
}
