package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_QUERY;

import java.util.function.Predicate;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.task.Task;

/**
 * Finds all tasks matching a given query exactly in name or description.
 */
public class TaskFindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Only displaying tasks with given query: ";
    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Searches all task descriptions and names that contain the given query.\n"
            + "Parameters:\n"
            + PREFIX_QUERY + "QUERY\n"
            + "Only tasks with exact matches with QUERY will be displayed. Can be multiple words.";
    private final Predicate<Task> predicate;
    private final String query;

    /**
     * Creates a TaskMarkCommand to mark a task with the specified {@code String query}.
     *
     * @param query
     */
    public TaskFindCommand(String query) {
        requireNonNull(query);
        predicate = (t1) -> t1.isWordInTask(query);
        this.query = query;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTaskListPredicate(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS + this.query));
    }
}
