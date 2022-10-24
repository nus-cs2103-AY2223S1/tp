package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task STUDY = new TaskBuilder().withName("Study for exam").withDeadline("2022-01-02")
            .withIsDone(false).build();
    public static final Task COOK = new TaskBuilder().withName("Cook for the family").withDeadline("2022-10-24")
            .withIsDone(true).build();

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(STUDY, COOK));
    }
}
