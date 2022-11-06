package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.ListTasksCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code Task}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD_FULL + " " + getTaskDetails(task);
    }

    /**
     * Returns a list command string for adding the {@code Task}.
     */
    public static String getListTasksCommand() {
        return ListTasksCommand.COMMAND_WORD_FULL;
    }

    /**
     * Returns the part of command string for the given {@code teammate}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTitle().toString() + " ");
        sb.append(PREFIX_DEADLINE + task.getDeadline().toString() + " ");
        return sb.toString();
    }
}
