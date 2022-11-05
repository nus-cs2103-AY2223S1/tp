package seedu.address.testutil;

import java.time.LocalDateTime;
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
    public static final Task TASK_2_EDITED = new Task(new TaskName("task 123"), List.of(TypicalPersons.ALICE),
            false, LocalDateTime.of(2022, 12, 12, 23, 59));
    public static final Task TASK_3 = new Task(new TaskName("task three"), List.of(TypicalPersons.ALICE), false,
            LocalDateTime.of(2022, 12, 12, 23, 59));
    public static final Task TASK_3_NO_DEADLINE = new Task(new TaskName("task three"), List.of(TypicalPersons.ALICE),
            false,
            null);
}
