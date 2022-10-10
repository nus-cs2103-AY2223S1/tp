package taskbook.testutil;

import taskbook.model.TaskBook;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task EATING = new TaskBuilder().withPerson(TypicalPersons.ALICE)
            .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(true)
            .build();

    public static final Task SLEEPING = new TaskBuilder().withPerson(TypicalPersons.BENSON)
            .withAssignment(Assignment.FROM).withDescription("sleep early").withIsDone(false)
            .build();

    public static final Task PARTYING = new TaskBuilder().withPerson(TypicalPersons.CARL)
            .withAssignment(Assignment.TO).withDescription("party at kevin's house")
            .withIsDone(true).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskBook} with all the typical persons.
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        for (Task task : getTypicalTasks()) {
            tb.addTask(task);
        }
        return tb;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(EATING, SLEEPING, PARTYING));
    }
}
