package modtrekt.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modtrekt.logic.commands.CommandTestUtil;
import modtrekt.model.TaskBook;
import modtrekt.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_1 = new TaskBuilder().withDescription("Do Math Quiz")
            .withModCode("CS2103T").build();
    public static final Task TASK_2 = new TaskBuilder().withDescription("Submit assignment")
            .withModCode("CS2103T").build();
    public static final Task TASK_3 = new TaskBuilder().withDescription("Submit academic declaration")
            .withModCode("CS2103T").build();
    public static final Task TASK_4 = new TaskBuilder().withDescription("Rewrite resume")
            .withModCode("CS2103T").build();
    public static final Task TASK_5 = new TaskBuilder().withDescription("Lookup SWE internships")
            .withModCode("CS2103T").build();
    public static final Task TASK_6 = new TaskBuilder().withDescription("Submit course survey")
            .withModCode("CS2103T").build();
    public static final Task TASK_7 = new TaskBuilder().withDescription("Buy Groceries")
            .withModCode("CS2103T").build();

    // Manually added
    public static final Task TASK_8 = new TaskBuilder().withDescription("Clean room").build();
    public static final Task TASK_9 = new TaskBuilder().withDescription("Review peer IPs").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task ADD_TASK_1 =
            new TaskBuilder().withDescription(CommandTestUtil.VALID_DESC_1).build();
    public static final Task ADD_TASK_2 =
            new TaskBuilder().withDescription(CommandTestUtil.VALID_DESC_2).build();

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical tasks.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook ab = new TaskBook();
        for (Task t : getTypicalTasks()) {
            ab.addTask(t);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4, TASK_5, TASK_6, TASK_7));
    }
}
