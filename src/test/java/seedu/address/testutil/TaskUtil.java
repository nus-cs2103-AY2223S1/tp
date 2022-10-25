package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add task string for adding the {@code task}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getName().fullName + " ");
        sb.append(PREFIX_MODULE + task.getModule().moduleName + " ");
        sb.append(PREFIX_DEADLINE + task.getDeadline().toString() + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskCommand.EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module.moduleName).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.deadlineString())
                .append(" "));
        return sb.toString();
    }
}
