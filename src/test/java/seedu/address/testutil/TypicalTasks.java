package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task STUDY = new TaskBuilder().withName("Study for exam").withDeadline("02-01-2022")
            .withIsDone(false).build();
    public static final Task COOK = new TaskBuilder().withName("Cook for 4 people").withDeadline("24-10-2022")
            .withIsDone(true).build();
    public static final Task REVIEW = new TaskBuilder().withName("Review PR").withIsDone(false).build();
    public static final Task PACK = new TaskBuilder().withName("Pack Table").withIsDone(true).build();

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(STUDY, COOK, REVIEW));
    }
}
