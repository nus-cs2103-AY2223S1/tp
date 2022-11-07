package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK1 = new TaskBuilder().withTaskName("Task 1")
            .withTaskDescription("Due Today")
            .withTaskDeadline("10/10/2020")
            .build();
    public static final Task TASK2 = new TaskBuilder().withTaskName("Task 2")
            .withTaskDescription("Due Tomorrow")
            .withTaskDeadline("10/10/2020")
            .build();

    // Manually added
    public static final Task TASK3 = new TaskBuilder().withTaskName("Task 3").withTaskDescription("Due Now")
            .withTaskDeadline("10/10/2020").build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }

        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK1, TASK2));
    }
}
