package taskbook.logic.commands.tasks;

import taskbook.logic.commands.Command;
import taskbook.logic.parser.tasks.TaskCategoryParser;

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
}
