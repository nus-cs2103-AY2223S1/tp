package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.team.TaskList.NO_TASKS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;



/**
 * Lists all tasks of the current team.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list_tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Lists all the tasks of the current team.\n"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_LIST_TASK_SUCCESS = "Tasks: \n%1$s";

    private String filter;

    public ListTasksCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String tasks = model.getTeam().getTasksAsString();
        String completedTasks = model.getTeam().getCompletedTasksAsString();
        String incompleteTasks = model.getTeam().getIncompleteTasksAsString();
        if (tasks.equals(NO_TASKS)) {
            return new CommandResult(NO_TASKS);
        }
        if (filter.equals("complete") || filter.equals("c")) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, completedTasks));
        }
        if (filter.equals("incomplete") || filter.equals("i")) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, incompleteTasks));
        }
        return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, tasks));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListTasksCommand); // instanceof handles nulls
    }
}
