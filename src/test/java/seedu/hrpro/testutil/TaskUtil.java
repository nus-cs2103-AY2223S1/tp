package seedu.hrpro.testutil;

import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.hrpro.logic.commands.AddTaskCommand;
import seedu.hrpro.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.hrpro.model.task.Task;

/**
 * Utility class for task test cases.
 */
public class TaskUtil {

    /**
     * Returns an add task command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetail(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetail(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_DEADLINE + task.getTaskDeadline().toString() + " ");
        sb.append(PREFIX_TASK_DESCRIPTION + task.getTaskDescription().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTaskDescription().ifPresent(taskDescription -> sb.append(PREFIX_TASK_DESCRIPTION)
                .append(taskDescription).append(" "));
        descriptor.getTaskDeadline().ifPresent(deadline -> sb.append(PREFIX_TASK_DEADLINE).append(
                deadline).append(" "));
        return sb.toString();
    }
}
