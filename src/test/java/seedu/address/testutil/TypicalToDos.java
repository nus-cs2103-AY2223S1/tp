package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * A utility class containing a list of {@code ToDo} objects to be used in tests.
 */
public class TypicalToDos {

    public static final ToDo FIRST = new ToDoBuilder().withTitle("Grade assignments")
            .withDescription("Complete by tonight").build();
    public static final ToDo SECOND = new ToDoBuilder().withTitle("Prepare slides for studio")
            .withDescription("Topic Environment Model").build();
    public static final ToDo THIRD = new ToDoBuilder().withTitle("Review CS1101S topics with Adam")
            .withDescription("Essence of Recursion").build();
    public static final ToDo FOURTH = new ToDoBuilder().withTitle("Collect robot")
            .withDescription("At MakersLab").build();
    public static final ToDo FIFTH = new ToDoBuilder().withTitle("Go through tutorial sheet")
            .withDescription("By this friday").build();

    private TypicalToDos() {}

    /**
     * Returns a {@code TaskBook} with all the typical tasks.
     * @return
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        for (Task task : getTypicalTodos()) {
            tb.addTask(task);
        }
        return tb;
    }

    public static List<Task> getTypicalTodos() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH));
    }
}
