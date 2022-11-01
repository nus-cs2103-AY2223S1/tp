package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASKS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASKS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASKS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASKS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASK_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.team.TaskList.NO_TASKS;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all tasks of the current team.
 */
@CommandLine.Command(name = "tasks", aliases = {"t"}, mixinStandardHelpOptions = true)
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the tasks of the current team.\n"
            + "To view only incomplete or completed tasks, add "
            + FLAG_INCOMPLETE_TASKS_STR + "(for incomplete tasks) or "
            + FLAG_COMPLETE_TASKS_STR + " (for completed tasks) \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_LIST_TASK_SUCCESS = "Tasks: \n%1$s";

    @CommandLine.Option(names = {FLAG_COMPLETE_TASKS_STR, FLAG_COMPLETE_TASKS_STR_LONG},
            description = FLAG_COMPLETE_TASK_DESCRIPTION)
    private boolean hasCompleteFlag;

    @CommandLine.Option(names = {FLAG_INCOMPLETE_TASKS_STR, FLAG_INCOMPLETE_TASKS_STR_LONG}, description =
            FLAG_INCOMPLETE_TASK_DESCRIPTION)
    private boolean hasIncompleteFlag;

    public ListTasksCommand() {
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

        if ((hasCompleteFlag == hasIncompleteFlag)) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, tasks));
        } else if (hasCompleteFlag) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, completedTasks));
        } else if (hasIncompleteFlag) {
            return new CommandResult(String.format(MESSAGE_LIST_TASK_SUCCESS, incompleteTasks));
        }
        return new CommandResult("Invalid command format!\n" + MESSAGE_USAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTasksCommand
                && hasCompleteFlag == ((ListTasksCommand) other).hasCompleteFlag
                && hasIncompleteFlag == ((ListTasksCommand) other).hasIncompleteFlag); // instanceof handles nulls
    }
}
