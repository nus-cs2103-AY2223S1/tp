package seedu.address.testutil;

import static seedu.address.commons.util.DateUtil.getLocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_FUEL = new TaskBuilder().withTitle("Buy Fuel")
            .withDeadline(getLocalDate("2029-10-29")).withStatus(false).build();
    public static final Task TASK_GINGER = new TaskBuilder().withTitle("Buy Ginger")
            .withDeadline(getLocalDate("2029-02-10"))
            .withStatus(false).build();
    public static final Task TASK_PAYMENT = new TaskBuilder().withTitle("Pay John")
            .withDeadline(getLocalDate("2029-03-10"))
            .withStatus(false).build();
    public static final Task TASK_GARLIC = new TaskBuilder().withTitle("Buy Garlic")
            .withDeadline(getLocalDate("2029-04-10"))
            .withStatus(false).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a TaskList with all typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList taskList = new TaskList();
        getTypicalTasks().forEach(task -> {
            taskList.addTask(task);
        });
        return taskList;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_FUEL, TASK_GARLIC, TASK_GINGER, TASK_PAYMENT));
    }
}
