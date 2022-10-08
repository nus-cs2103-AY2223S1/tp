package modtrekt.testutil;

import static modtrekt.logic.parser.CliSyntax.PREFIX_NAME;

import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.model.task.Task;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Task t) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(t);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTaskDetails(Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + t.getDescription().description + " ");
        return sb.toString();
    }
}
