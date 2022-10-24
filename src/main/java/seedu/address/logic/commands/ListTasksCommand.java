package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASKS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASKS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_FILTER_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASKS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASKS_STR_LONG;
import static seedu.address.model.team.TaskList.NO_TASKS;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all tasks of the current team.
 */
@CommandLine.Command(name = "tasks")
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the tasks of the current team.\n"
            + "To view only incomplete or completed tasks, add "
            + "-" + FLAG_FILTER_STR + " i (for incomplete tasks) or c (for completed tasks) \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_LIST_TASK_SUCCESS = "Tasks: \n%1$s";

    private String filter;

    @CommandLine.Option(names = {FLAG_COMPLETE_TASKS_STR, FLAG_COMPLETE_TASKS_STR_LONG})
    private boolean complete;

    @CommandLine.Option(names = {FLAG_INCOMPLETE_TASKS_STR, FLAG_INCOMPLETE_TASKS_STR_LONG})
    private boolean incomplete;

    public ListTasksCommand() {
    }

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

        if (complete && incomplete) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, tasks));
        } else if (complete) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, completedTasks));
        } else if (incomplete) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, incompleteTasks));
        }
        return new CommandResult("Invalid command format!\n" + MESSAGE_USAGE);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTasksCommand); // instanceof handles nulls
    }
}
