package seedu.address.testutil;

import seedu.address.model.team.Task;

import java.util.List;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_1 = new Task("task", List.of(), false, null);
    public static final Task TASK_1_DUPLICATED = new Task("task", List.of(), false, null);
    public static final Task TASK_2 = new Task("task 123", List.of(), false, null);
}
