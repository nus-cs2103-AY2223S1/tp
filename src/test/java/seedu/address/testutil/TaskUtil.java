package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.team.Task;

/**
 * Utility class to convert {@code Task} to String arguments for testing
 */
public class TaskUtil {

    /**
     * Converts {@code Task} to required arguments, along with assignee index and deadline.
     */
    public static String[] convertTaskToArgs(Task task, int assigneeIndex, String deadlineDate, String deadlineTime) {
        List<String> argList = new ArrayList<>();
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add(String.valueOf(assigneeIndex));
        argList.add(FLAG_DEADLINE_STR);
        argList.add(deadlineDate);
        argList.add(deadlineTime);
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Task} to required arguments, along with assignee index.
     */
    public static String[] convertPartialTaskToArgs(Task task, int assigneeIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add(String.valueOf(assigneeIndex));
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Task} to required arguments, along with task index, and assignee index.
     */
    public static String[] convertEditTaskToArgs(Task task, int taskIndex, int assigneeIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(taskIndex));
        argList.add(FLAG_NAME_STR);
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add(String.valueOf(assigneeIndex));
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Task} to required arguments, along with assignee index.
     */
    public static String[] convertEditPartialTaskToArgs(Task task, int taskIndex, int assigneeIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(taskIndex));
        argList.add(FLAG_NAME_STR);
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add(String.valueOf(assigneeIndex));
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Task} to required arguments, along with task index, and assignee index.
     */
    public static String[] convertEditPartialNoNameTaskToArgs(Task task, int taskIndex, int assigneeIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(taskIndex));
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add(String.valueOf(assigneeIndex));
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }
}
