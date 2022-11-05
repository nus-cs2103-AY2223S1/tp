package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * @param task
     * @return String array containing task fields
     */
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

    /**
     * @param task
     * @return String array containing task fields
     */
    public static String[] convertPartialTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        return argList.toArray(new String[0]);
    }

    /**
     * @param task
     * @return String array containing task fields
     */
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

    /**
     * @param task
     * @return String array containing task fields
     */
    public static String[] convertEditPartialTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("1");
        argList.add(FLAG_NAME_STR);
        argList.add(task.getName());
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        return argList.toArray(new String[0]);
    }

    /**
     * @param task
     * @return String array containing task fields
     */
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

    /**
     * @param task
     * @return String array containing task fields
     */
    public static String[] convertEditOutOfBoundsTaskToArgs(Task task) {
        List<String> argList = new ArrayList<>();
        argList.add("3");
        argList.add(FLAG_ASSIGNEE_STR);
        argList.add("1");
        argList.add(FLAG_DEADLINE_STR);
        argList.add(task.getDateInputAsString());
        argList.add(task.getTimeInputAsString());
        return argList.toArray(new String[0]);
    }

    /**
     * @param model
     * @return String output of tasks summary
     */
    public static String getTaskSummary(Model model) {
        StringBuilder taskSummary = new StringBuilder();
        List<Person> members = model.getTeam().getTeamMembers();
        Map<Person, Integer> tasks = model.getTeam().getNumTasksPerPerson();
        for (int i = 0; i < members.size(); i++) {
            taskSummary.append(i + 1).append(". ").append(members.get(i).getName()).append(": ")
                    .append(tasks.getOrDefault(members.get(i), 0)).append(" task(s) assigned\n");
        }
        return taskSummary.toString();
    }
}
