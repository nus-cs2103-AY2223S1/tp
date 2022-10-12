package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task FIRST = new TaskBuilder().withTitle("Grade assignments")
            .withDescription("Complete by tonight").build();
    public static final Task SECOND = new TaskBuilder().withTitle("Prepare slides for studio")
            .withDescription("Topic Environment Model").build();
    public static final Task THIRD = new TaskBuilder().withTitle("Review CS1101S topics with Adam")
            .withDescription("Essence of Recursion").build();
    public static final Task FOURTH = new TaskBuilder().withTitle("Collect robot")
            .withDescription("At MakersLab").build();
    public static final Task FIFTH = new TaskBuilder().withTitle("Go through tutorial sheet")
            .withDescription("By this friday").build();

    private TypicalTasks() {}

    /**
     * Returns a {@code TaskBook} with all the typical tasks.
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
