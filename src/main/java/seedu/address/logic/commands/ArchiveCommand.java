package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ArchiveCommand extends Command{

    public final String COMMAND_WORD = "archive";
    private final Index index;

    public final String MESSAGE_USAGE = COMMAND_WORD
            + ": archives the task identified by the index number used in the displayed task list.\n"
            + "parameters:\n"
            + "1. INDEX (must be a positive integer)\n"
            + "2. DATE (format of YYYY-MM-DD)\n"
            + "EXAMPLE:\n"
            + "1. archive 1\n"
            + "2. archive 2022-10-22";

    public static final String MESSAGE_ARCHIVED_TASK_SUCCESS = "Task archived!\n" + "Task: %1$s";


    public ArchiveCommand (Index index) {
        requireNonNull(index);
        this.index = index;
    }

    public Task createArchivedTask(Task taskToArchive) {
        assert taskToArchive != null;

        Name currName = taskToArchive.getName();
        Module currModule = taskToArchive.getModule();
        Deadline currDeadLine = taskToArchive.getDeadline();
        Set<Tag> currTag = taskToArchive.getTags();
        Boolean isDone = taskToArchive.isDone();

        return new Task(currName, currModule, currDeadLine, currTag, isDone, true);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToArchive = lastShownList.get(index.getZeroBased());
        Task archivedTask = createArchivedTask(taskToArchive);

        model.setTask(taskToArchive, archivedTask);

        return new CommandResult(String.format(MESSAGE_ARCHIVED_TASK_SUCCESS, taskToArchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && index.equals(((ArchiveCommand) other).index)); // state check
    }

}
