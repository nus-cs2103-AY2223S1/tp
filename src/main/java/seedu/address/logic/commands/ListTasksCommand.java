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

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Tasks: \n%1$s";

    public ListTasksCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String tasks = model.getTeam().getTasksAsString();
        if (tasks.equals(NO_TASKS)) {
            return new CommandResult(NO_TASKS);
        }
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, tasks));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTasksCommand); // instanceof handles nulls
    }

    @Override
    public void run() {

    }
}
