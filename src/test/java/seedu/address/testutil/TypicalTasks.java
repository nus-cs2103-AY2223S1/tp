package seedu.address.testutil;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalTasks {

    public static final Task FIRST = new TaskBuilder().withTitle("First title")
            .withDescription("First description").build();
    public static final Task SECOND = new TaskBuilder().withTitle("Second title")
            .withDescription("Second description").build();
    public static final Task THIRD = new TaskBuilder().withTitle("Third title")
            .withDescription("Third description").build();
    public static final Task FOURTH = new TaskBuilder().withTitle("Fourth title")
            .withDescription("Fourth description").build();
    public static final Task FIFTH = new TaskBuilder().withTitle("Fifth title")
            .withDescription("Fifth description").build();

    private TypicalTasks() {}

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
