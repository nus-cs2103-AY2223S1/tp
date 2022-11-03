package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.team.Task;

public class TaskUtil {
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.FULL_COMMAND + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(task.getName().toString()).append("\" ");
        sb.append(FLAG_ASSIGNEE_STR).append(" 1 2").append(" "); //TODO add proper indexes
        sb.append(FLAG_DEADLINE_STR + " ").append(task.getDeadlineStorage());
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTaskDetailsWithNameFlag(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(FLAG_NAME_STR + "\" ").append(task.getName().toString()).append("\" ");
        sb.append(FLAG_ASSIGNEE_STR).append(" 1 2").append(" "); //TODO add proper indexes
        sb.append(FLAG_DEADLINE_STR + " ").append(task.getDeadlineStorage());
        return sb.toString();
    }
}
