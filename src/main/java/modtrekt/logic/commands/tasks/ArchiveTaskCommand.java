package modtrekt.logic.commands.tasks;

import java.util.List;

import com.beust.jcommander.Parameter;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.IndexConverter;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Archives a task in the task book belonging to a module.
 */
public class ArchiveTaskCommand extends Command {
    public static final String COMMAND_WORD = "archive";

    @Parameter(names = "-t", description = "Index of the task to archive",
            required = true, converter = IndexConverter.class)
    private Index index;

    /**
     * Returns a new ArchiveTaskCommand object, with no fields initialized, for use with JCommander.
     */
    public ArchiveTaskCommand() {
    }

    /**
     * Returns a new ArchiveTaskCommand object.
     *
     * @param index the index of the task to be archived
     */
    public ArchiveTaskCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> tasks = model.getFilteredTaskList();

        // Check that there is at least one task.
        if (tasks.size() == 0) {
            throw new CommandException("There are no tasks.");
        }
        // Check that the task index is not out of bounds.
        // The 0-based index is guaranteed by the Index class invariant to be >= 0.
        if (index.getZeroBased() >= tasks.size()) {
            throw new CommandException(String.format("Task index must an integer between 1 and %d inclusive.",
                    tasks.size()));
        }

        Task target = model.getFilteredTaskList().get(index.getZeroBased());

        // Check that the task is not already unarchived.
        if (target.isArchived()) {
            throw new CommandException(String.format("Task #%d is already archived.", index.getOneBased()));
        }

        // Archive the task.
        model.setTask(target, target.archive());
        return new CommandResult("Archived task.");
    }
}
