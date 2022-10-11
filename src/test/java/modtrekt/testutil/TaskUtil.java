package modtrekt.testutil;

import static modtrekt.logic.parser.CliSyntax.PREFIX_TASK;

import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task t) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(t);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task t) {
        return PREFIX_TASK + t.getDescription().description + " -c " + t.getModule()
                .toString();
    }
}
