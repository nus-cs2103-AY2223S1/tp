package seedu.address.testutil;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

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
    public static String getEditStaffDescriptorDetails() {
        //todo
        return new String();
    }
}
