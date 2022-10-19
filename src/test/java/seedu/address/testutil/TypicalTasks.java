package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {


    public static final Task TASK_A = new Task(VALID_TASK_A);
    public static final Task TASK_B = new Task(VALID_TASK_B);
    public static final Task TASK_C = new Task(VALID_TASK_C);

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a {@code List} with all the typical tasks.
     */
    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_A, TASK_B, TASK_C));
    }
}
