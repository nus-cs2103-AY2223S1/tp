package modtrekt.logic.commands.tasks;

import java.util.List;
import java.util.Objects;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.IndexConverter;
import modtrekt.logic.parser.converters.PriorityConverter;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Sets the priority of a task to a specified level.
 */
@Parameters(commandDescription = "Assigns a task a priority rating.")
public class PrioritizeTaskCommand extends Command {
    public static final String COMMAND_WORD = "prioritize";

    @Parameter(names = "-t", description = "Index of the task to set the priority",
            required = true, converter = IndexConverter.class)
    private Index index;
    @Parameter(names = "-p", description = "Priority level for the task",
            required = true, converter = PriorityConverter.class)
    private Task.Priority priority;

    /**
     * Returns a new PrioritizeTaskCommand object, with no fields initialized, for use with JCommander.
     */
    public PrioritizeTaskCommand() {
    }

    /**
     * Returns a new PrioritizeTaskCommand object.
     *
     * @param index    the index of the task to prioritize
     * @param priority the priority level to set the task
     */
    public PrioritizeTaskCommand(Index index, Task.Priority priority) {
        this.index = index;
        this.priority = priority;
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
        Task target = tasks.get(index.getZeroBased());

        // Check that the task does not already have the same priority rating.
        if (target.getPriority() == priority) {
            if (priority == Task.Priority.NONE) {
                throw new CommandException(String.format("Task #%d has already had no priority set.",
                        index.getOneBased()
                ));
            }
            throw new CommandException(String.format("Task #%d has already been set to %s priority.",
                    index.getOneBased(),
                    priority
            ));
        }

        // Set the priority rating for the task.
        model.setTask(target, target.setPriority(priority));
        return new CommandResult(String.format("Set task #%d's priority to %s.", index.getOneBased(), priority));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrioritizeTaskCommand)) {
            return false;
        }
        PrioritizeTaskCommand that = (PrioritizeTaskCommand) other;
        return this.index.equals(that.index) && priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, priority);
    }

    @Override
    public String toString() {
        return "PrioritizeTaskCommand{" + "index=" + index + ", priority=" + priority + '}';
    }
}
