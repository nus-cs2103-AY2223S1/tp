package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static taskbook.logic.parser.CliSyntax.PREFIX_DONE;
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
 * Query matching is case insensitive.
 */
public class TaskFindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Displaying matching tasks.";
    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Searches all task descriptions and names that contain the given query.\n"
            + "Parameters:\n"
            + PREFIX_QUERY + "QUERY\n"
            + PREFIX_ASSIGNMENT + "ASSIGNMENT\n"
            + PREFIX_DONE + "DONE\n"
            + "Only tasks with exact matches with QUERY will be displayed. Can be multiple words. Case insensitive.\n"
            + "ASSIGNMENT is either FROM or TO for Assigned by or Assigned to respectively.\n"
            + "DONE is either X or O for done or not done respectively.\n"
            + "Parameters can be in any ordering, and only one must be present.";
    private Predicate<Task> predicate;
    private String query;
    private String assignment;
    private String done;

    /**
     * Creates a TaskFindCommand to search for tasks with the specified {@code Predicate<Task> query}.
     * @param query
     */
    public TaskFindCommand(Predicate<Task> predicate, String query, String asssignment, String done) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.query = query;
        this.assignment = asssignment;
        this.done = done;
    }

    public String getFilters() {
        StringBuilder builder = new StringBuilder();
        if (query != null) {
            builder.append("Query: " + query + "\n");
        }
        if (assignment != null) {
            builder.append("Assignment: " + assignment + "\n");
        }
        if (done != null) {
            builder.append(done.equalsIgnoreCase("x") ? "Tasks are done." : "Task are not done.");
        }
        return builder.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTaskListPredicate(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS + "\n"
                                + model.getFilteredTaskList().size() + " tasks listed.\n"
                                + getFilters()));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TaskFindCommand)) {
            return false;
        }
        TaskFindCommand command = (TaskFindCommand) other;
        boolean sameQuery = this.query == null || command.query == null
                            ? this.query == command.query
                            : this.query.toUpperCase().equals(command.query.toUpperCase());
        boolean sameAssignment = this.assignment == null || command.assignment == null
                                    ? this.assignment == command.assignment
                                    : this.assignment.equals(command.assignment);
        boolean sameDone = this.done == null || command.done == null
                            ? this.done == command.done
                            : this.done.equals(command.done);
        return sameQuery && sameAssignment && sameDone;
    }
}
