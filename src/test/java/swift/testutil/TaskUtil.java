package swift.testutil;

import static swift.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static swift.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;

import swift.logic.commands.AddTaskCommand;
import swift.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add task command string for adding the {@code task}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        String taskDetails = PREFIX_NAME + task.getName().fullName;
        if (task.getDescription().isPresent()) {
            taskDetails += " " + PREFIX_DESCRIPTION + task.getDescription().get();
        }
        if (task.getDeadline().isPresent()) {
            taskDetails += " " + PREFIX_DEADLINE + task.getDeadline().get();
        }
        return taskDetails;
    }
}
