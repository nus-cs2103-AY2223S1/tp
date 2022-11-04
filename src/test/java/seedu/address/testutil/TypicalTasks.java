package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.team.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_1 = new Task("task", List.of(), false, null);
    public static final Task TASK_1_DUPLICATED = new Task("task", List.of(), false, null);
    public static final Task TASK_2 = new Task("task 123", List.of(), false, null);
    public static final Task TASK_3 = new Task("task 123", List.of(TypicalPersons.ALICE), false,
            LocalDateTime.of(2022, 12, 12, 23, 59));
    public static final Task TASK_3_NO_DEADLINE = new Task("task 123", List.of(TypicalPersons.ALICE), false,
            null);
}
