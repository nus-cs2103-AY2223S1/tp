package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * DeleteTagCommand removes the tag linked to the task in the task list.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "tagdel";
    public static final String MESSAGE_USAGE = "t " + COMMAND_WORD + ": deletes tags linked to the task.\n"
            + "Parameters: " + "INDEX " + PREFIX_TAG + "KEYWORD [SECOND_KEYWORD]\n"
            + "Example: " + "t " + COMMAND_WORD + " 1 " + "t/" + "priority";
    public static final String NO_PRIORITY_TAG_TO_DELETE =
            "There is no priority tag to delete from the task.";
    public static final String NO_DEADLINE_TAG_TO_DELETE =
            "There is no deadline tag to delete form the task.";
    public static final String TAG_DELETED_SUCCESSFULLY =
            "The tag(s) has/have been deleted from the task.";

    private final Index index;
    private final Set<String> keywords;

    /**
     * The constructor of the DeleteTagCommand class. Sets the index and
     * the keywords of the tags to be removed.
     *
     * @param index The index of the task
     * @param keywords The keywords which identify the tags to be removed.
     */
    public DeleteTagCommand(Index index, Set<String> keywords) {
        requireAllNonNull(index, keywords);
        this.index = index;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getFilteredTaskList();
        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task currentTask = taskList.get(index.getZeroBased());
        Task changedTask = currentTask;
        boolean hasPriorityKeyword = keywords.contains("priority");
        boolean hasDeadlineKeyword = keywords.contains("deadline");
        if (hasPriorityKeyword) {
            if (!currentTask.hasPriorityTag()) {
                throw new CommandException(NO_PRIORITY_TAG_TO_DELETE);
            }
            changedTask = changedTask.deletePriorityTag();
        }
        if (hasDeadlineKeyword) {
            if (!currentTask.hasDeadlineTag()) {
                throw new CommandException(NO_DEADLINE_TAG_TO_DELETE);
            }
            changedTask = changedTask.deleteDeadlineTag();
        }
        model.replaceTask(currentTask, changedTask, true);
        return new CommandResult(TAG_DELETED_SUCCESSFULLY);
    }

    @Override
    public boolean equals(Object otherDeleteTagCommand) {
        return otherDeleteTagCommand == this
                || (otherDeleteTagCommand instanceof DeleteTagCommand
                && keywords.equals(((DeleteTagCommand) otherDeleteTagCommand).keywords)
                && index.equals(((DeleteTagCommand) otherDeleteTagCommand).index));
    }
}
