package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddTaskCommand(Task task) {
        return "t " + AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE + task.getModule().getModuleCode().moduleCode + " ");
        sb.append(PREFIX_DESCRIPTION + task.getDescription().description + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskCommand.EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description-> sb.append(PREFIX_DESCRIPTION).append(description).append(" "));
        descriptor.getModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module).append(" "));
        return sb.toString();
    }
}
