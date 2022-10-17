package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_BRAVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_1 = new TaskBuilder().withDescription("Todo 2101")
            .withDeadline("2022-01-11").build();
    public static final Task TASK_2 = new TaskBuilder().withDescription("Todo 2102")
            .withDeadline("2022-02-16").build();
    public static final Task TASK_3 = new TaskBuilder().withDescription("Todo 2103")
            .withDeadline("2022-03-15").build();
    public static final Task TASK_4 = new TaskBuilder().withDescription("Todo 2104")
            .withDeadline("2022-04-14").build();
    public static final Task TASK_5 = new TaskBuilder().withDescription("Todo 2105")
            .withDeadline("2022-05-13").build();
    public static final Task TASK_6 = new TaskBuilder().withDescription("Todo 2106")
            .withDeadline("2022-06-12").build();
    public static final Task TASK_7 = new TaskBuilder().withDescription("Todo 2107")
            .withDeadline("2022-07-11").build();

    // Manually added - Staff's details found in {@code CommandTestUtil}

    public static final Task TASK_ALPHA = new TaskBuilder().withDescription(VALID_TASKDESCRIPTION_ALPHA)
            .withDeadline(VALID_TASKDEADLINE_ALPHA).build();
    public static final Task TASK_BRAVO = new TaskBuilder().withDescription(VALID_TASKDESCRIPTION_BRAVO)
            .withDeadline(VALID_TASKDEADLINE_BRAVO).build();

    private TypicalTasks() {
    } // prevents instantiation

    public static List<Task> getTypicalTask() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4, TASK_5, TASK_6, TASK_7));
    }
}
