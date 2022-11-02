package seedu.address.testutil;

import java.util.List;

import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_1 = new Task(new TaskName("task"), List.of(), false, null);
    public static final Task TASK_1_DUPLICATED = new Task(new TaskName("task"), List.of(), false, null);
    public static final Task TASK_2 = new Task(new TaskName("task 123"), List.of(), false, null);
}
