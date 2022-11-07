package seedu.hrpro.testutil;

import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKMARK_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKMARK_BRAVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hrpro.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_1 = new TaskBuilder().withDescription("Assignment 2101")
            .withDeadline("2022-07-11").withMark("false").build();
    public static final Task TASK_2 = new TaskBuilder().withDescription("Todo 2102")
            .withDeadline("2022-06-12").withMark("true").build();
    public static final Task TASK_3 = new TaskBuilder().withDescription("Todo 2103")
            .withDeadline("2022-02-16").withMark("false").build();
    public static final Task TASK_4 = new TaskBuilder().withDescription("Todo 2104")
            .withDeadline("2022-04-14").withMark("true").build();
    public static final Task TASK_5 = new TaskBuilder().withDescription("Todo 2105")
            .withDeadline("2022-01-11").withMark("false").build();
    public static final Task TASK_6 = new TaskBuilder().withDescription("Todo 2106")
            .withDeadline("2022-03-15").withMark("true").build();
    public static final Task TASK_7 = new TaskBuilder().withDescription("Todo 2107")
            .withDeadline("2022-05-13").withMark("false").build();

    // Manually added - Staff's details found in {@code CommandTestUtil}

    public static final Task TASK_ALPHA = new TaskBuilder().withDescription(VALID_TASKDESCRIPTION_ALPHA)
            .withDeadline(VALID_TASKDEADLINE_ALPHA).withMark(VALID_TASKMARK_ALPHA).build();
    public static final Task TASK_BRAVO = new TaskBuilder().withDescription(VALID_TASKDESCRIPTION_BRAVO)
            .withDeadline(VALID_TASKDEADLINE_BRAVO).withMark(VALID_TASKMARK_BRAVO).build();

    private TypicalTasks() {
    } // prevents instantiation

    public static List<Task> getTypicalTask() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4, TASK_5, TASK_6, TASK_7));
    }
}
