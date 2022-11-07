package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;

/**
 * Lists all tasks in the task book to the user.
 */
public class TaskListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_USAGE = TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
        + ": Shows you a list of all assigned tasks in your TaskBook in the order they were added.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskListPredicate(Model.PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
