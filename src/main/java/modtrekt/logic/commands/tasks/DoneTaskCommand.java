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
 * Marks a task in the task book as done.
 */
@Parameters(commandDescription = "Marks a task as done.")
public class DoneTaskCommand extends Command {
    public static final String COMMAND_WORD = "done task";

    @Parameter(description = "<task index>", required = true, converter = IndexConverter.class)
    private Index index;

    /**
     * Returns a new DoneTaskCommand object, with no fields initialized, for use with JCommander.
     */
    public DoneTaskCommand() {
    }

    /**
     * Returns a new DoneTaskCommand object.
     *
     * @param index the index of the task to be marked as done
     */
    public DoneTaskCommand(Index index) {
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

        // Check that the task is not already marked as done.
        if (target.isDone()) {
            throw new CommandException(String.format("Task #%d is already marked as done.", index.getOneBased()));
        }

        // Mark the task as done.
        model.setTask(target, target.setAsDone());
        return new CommandResult("Yay! I successfully marked this task as done!");
    }
}
