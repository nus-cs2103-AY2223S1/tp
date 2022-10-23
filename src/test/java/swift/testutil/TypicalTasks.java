package swift.testutil;

import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import swift.model.AddressBook;
import swift.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final String TASK1_UUID = "bfbf250c-fd58-49b4-be15-ca12095ca2ee";
    public static final String TASK2_UUID = "41478d11-dbba-441f-ab0f-99ddf097eac1";

    public static final Task BUY_MILK = new TaskBuilder()
            .withTaskName(VALID_TASK_NAME_1)
            .withId(TASK1_UUID).build();

    public static final Task CS2103T = new TaskBuilder()
            .withTaskName(VALID_TASK_NAME_2)
            .withId(TASK2_UUID).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BUY_MILK, CS2103T));
    }
}
