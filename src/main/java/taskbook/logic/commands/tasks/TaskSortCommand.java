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
                    + " s/SORT_TYPE: Sorts list of tasks in some order.\n"
                    + "Available SORT_TYPEs:\n"
                    + "a: sorts description of tasks in alphabetical order.\n"
                    + "ra: sorts description of tasks in reverse alphabetical order.\n"
                    + "ca: sorts chronologically, by time tasks were added.\n"
                    + "cd: sorts chronologically, by dates associated with tasks. Tasks with no dates at end of list.\n"
                    + "rcd: sorts reverse chronologically, by dates associated with tasks. "
                    + "Tasks with no dates at end of list";
    public static final String MESSAGE_SORT_TASK_SUCCESS = "Tasks sorted";
    public final String messageSortType;
    private final Comparator<Task> comparator;

    /**
     * Creates a tasks sorting command.
     * @param comparator
     * @param sortType
     */
    public TaskSortCommand(Comparator<Task> comparator, String sortType) {
        this.comparator = comparator;
        this.messageSortType = sortType;
    }

    public Comparator<Task> getComparator() {
        return comparator;
    }
}
