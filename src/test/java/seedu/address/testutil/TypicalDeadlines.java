package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Deadline} objects to be used in tests.
 */
public class TypicalDeadlines {
    public static final Task FIRST = new DeadlineBuilder().withTitle("Grade assignments")
            .withDescription("Complete by tonight").withDate("2012-11-01").build();
    public static final Task SECOND = new DeadlineBuilder().withTitle("Prepare slides for studio")
            .withDescription("Topic Environment Model").withDate("2018-08-01").build();
    public static final Task THIRD = new DeadlineBuilder().withTitle("Review CS1101S topics with Adam")
            .withDescription("Essence of Recursion").withDate("2019-01-01").build();
    public static final Task FOURTH = new DeadlineBuilder().withTitle("Collect robot")
            .withDescription("At MakersLab").withDate("2021-02-10").build();
    public static final Task FIFTH = new DeadlineBuilder().withTitle("Go through tutorial sheet")
            .withDescription("By this friday").withDate("2022-11-02").build();

    private TypicalDeadlines() {
    }

    /**
     * Returns a {@code TaskBook} with all the typical tasks.
     *
     * @return
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        for (Task task : getTypicalTasks()) {
            tb.addTask(task);
        }
        return tb;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH));
    }
}
