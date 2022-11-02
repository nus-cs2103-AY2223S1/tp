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
 * Marks a task identified using it's displayed index from the task list as not done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks the task identified by the index number used in the displayed task list as NOT done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_AS_NOT_DONE_SUCCESS = "Marked as NOT done! Task: %1$s";

    private final Index index;

    public UnmarkCommand(Index index) {
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
        Task markedTask = createUnmarkedTask(taskToMark);
        model.setTask(taskToMark, markedTask);

        return new CommandResult(String.format(MESSAGE_MARK_AS_NOT_DONE_SUCCESS, markedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToMark}
     * with its isDone status set as false.
     */
    private static Task createUnmarkedTask(Task taskToUnmark) {
        assert taskToUnmark != null;

        Name updatedName = taskToUnmark.getName();
        Module updatedModule = taskToUnmark.getModule();
        Deadline updatedDeadline = taskToUnmark.getDeadline();
        Set<Tag> updatedTags = taskToUnmark.getTags();

        return new Task(updatedName, updatedModule, updatedDeadline, updatedTags, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && index.equals(((UnmarkCommand) other).index)); // state check
    }
}
