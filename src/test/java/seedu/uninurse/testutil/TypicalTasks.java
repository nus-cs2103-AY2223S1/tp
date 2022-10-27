package seedu.uninurse.testutil;

import static seedu.uninurse.testutil.TypicalDateTime.DATE_TIME_ONE;

import seedu.uninurse.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_INSULIN = new Task("Administer 1 unit of insulin", DATE_TIME_ONE);
    public static final Task TASK_HEALTH_RECORDS = new Task("Update health records");
    public static final Task TASK_CARE_PLAN = new Task("Discuss care plan with family");
}
