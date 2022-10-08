package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_FUEL = new TaskBuilder().withTitle("Buy Fuel").withDeadline("29 Oct 2029")
            .withStatus(false).build();
    public static final Task TASK_GINGER = new TaskBuilder().withTitle("Buy Ginger").withDeadline("20 Oct 2021")
            .withStatus(false).build();
    public static final Task TASK_PAYMENT = new TaskBuilder().withTitle("Pay John").withDeadline("29 Oct 2090")
            .withStatus(false).build();
    public static final Task TASK_GARLIC = new TaskBuilder().withTitle("Buy Garlic").withDeadline("21 Oct 2129")
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
