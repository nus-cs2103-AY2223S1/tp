package modtrekt.logic.commands.tasks;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.IndexConverter;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Marks a task in the task book as undone.
 */
@Parameters(commandDescription = "Marks a task as undone.")
public class UndoneTaskCommand extends Command {
    public static final String COMMAND_WORD = "undone task";

    @Parameter(description = "Index of the task to mark as undone", required = true,
            converter = IndexConverter.class)
    private Index index;

    /**
     * Returns a new UndoneTaskCommand object, with no fields initialized, for use with JCommander.
     */
    public UndoneTaskCommand() {
    }

    /**
     * Returns a new UndoneTaskCommand object.
     *
     * @param index the index of the task to be mark as undone
     */
    public UndoneTaskCommand(Index index) {
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
                    tasks.size()
            ));
        }
        Task target = model.getFilteredTaskList().get(index.getZeroBased());

        // Check that the task is not already marked as undone.
        if (!target.isDone()) {
            throw new CommandException(String.format("Task #%d is already marked as undone.", index.getOneBased()));
        }

        // Mark the task as undone..
        model.setTask(target, target.setAsUndone());
        return new CommandResult("Marked the task as undone.");
    }
}
