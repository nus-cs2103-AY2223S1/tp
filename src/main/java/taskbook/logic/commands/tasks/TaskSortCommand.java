package taskbook.logic.commands.tasks;

import java.util.Comparator;

import taskbook.logic.commands.Command;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.task.Task;

/**
 * Sorts the list of tasks.
 */
public abstract class TaskSortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
                    + ": Sorts list of tasks in some order.\n"
                    + "Available sorting commands:\n"
                    + "a: sorts description of tasks in alphabetical order";
    public static final String MESSAGE_SORT_TASK_SUCCESS = "Tasks sorted";
    private final Comparator<Task> comparator;

    public TaskSortCommand(Comparator<Task> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Task> getComparator() {
        return comparator;
    }
}
