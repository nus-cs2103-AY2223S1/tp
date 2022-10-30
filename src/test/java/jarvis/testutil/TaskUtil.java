package jarvis.testutil;


import static jarvis.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static jarvis.logic.parser.CliSyntax.PREFIX_TASK_DESC;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

import jarvis.logic.commands.AddTaskCommand;
import jarvis.model.Task;

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
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_DESC + task.getDesc().taskDesc + " ");
        if (task.hasDeadline()) {
            sb.append(PREFIX_DEADLINE + task.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return sb.toString();
    }
}