package seedu.address.testutil;

import seedu.address.model.team.Task;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.logic.parser.CliSyntax.*;

public class TaskUtil {

    public static String[] convertTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        argList.add(FLAG_DEADLINE_STR);
        argList.add("2022-12-12");
        argList.add("23:59");
        return argList.toArray(new String[0]);
    }
    public static String[] convertPartialTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        return argList.toArray(new String[0]);
    }

    public static String[] convertEditTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("1");
        argList.add(FLAG_NAME_STR);
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }

    public static String[] convertEditPartialTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("1");
        argList.add(FLAG_NAME_STR);
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        return argList.toArray(new String[0]);
    }

    public static String[] convertEditPartialNoNameTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("1");
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }

    public static String[] convertEditOOBTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("3");
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }
}
