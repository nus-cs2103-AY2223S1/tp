package seedu.address.testutil;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK1 = new TaskBuilder().withModule("CS2001")
            .withTaskDescription("description 1").build();
}
