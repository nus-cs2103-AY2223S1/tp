package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_AS_DONE_SUCCESS = "Marked as done! Task: %1$s";

    private final Index index;

    public MarkCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(index.getZeroBased());
        Task markedTask = createMarkedTask(taskToMark);

        model.setTask(taskToMark, markedTask);

        return new CommandResult(String.format(MESSAGE_MARK_AS_DONE_SUCCESS, markedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToMark}
     * with its isDone status set as true.
     */
    private static Task createMarkedTask(Task taskToMark) {
        assert taskToMark != null;

        Name updatedName = taskToMark.getName();
        Module updatedModule = taskToMark.getModule();
        Deadline updatedDeadline = taskToMark.getDeadline();
        Set<Tag> updatedTags = taskToMark.getTags();

        return new Task(updatedName, updatedModule, updatedDeadline, updatedTags, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && index.equals(((MarkCommand) other).index)); // state check
    }
}
