package seedu.address.testutil;

import seedu.address.logic.commands.task.AddTaskCommand;
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
     * Returns the part of command string for the given {@code teammate}'s details.
     */
    public static String getTaskDetails(Task task) {
        return task.getTitle().toString() + " ";
    }
}
