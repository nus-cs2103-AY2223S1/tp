package swift.testutil;

import static swift.logic.commands.CommandTestUtil.EMPTY_DEADLINE;
import static swift.logic.commands.CommandTestUtil.EMPTY_DESCRIPTION;
import static swift.logic.commands.CommandTestUtil.VALID_DEADLINE_1;
import static swift.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static swift.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static swift.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_4;
import static swift.logic.commands.CommandTestUtil.VALID_TASK_NAME_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import swift.model.AddressBook;
import swift.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final String TASK_ID_1 = "bfbf250c-fd58-49b4-be15-ca12095ca2ee";
    public static final String TASK_ID_2 = "41478d11-dbba-441f-ab0f-99ddf097eac1";
    public static final String TASK_ID_3 = "384308a6-6c01-4e7a-a9eb-8147f6502764";
    public static final String TASK_ID_4 = "8aa2ee94-728a-47b5-8864-474ea71612fb";

    public static final Task BUY_MILK = new TaskBuilder()
            .withId(TASK_ID_1)
            .withTaskName(VALID_TASK_NAME_1)
            .withDescription(EMPTY_DESCRIPTION)
            .withDeadline(VALID_DEADLINE_1)
            .withIsDone(true).build();

    public static final Task CS2103T = new TaskBuilder()
            .withId(TASK_ID_2)
            .withTaskName(VALID_TASK_NAME_2)
            .withDescription(VALID_DESCRIPTION_1)
            .withDeadline(VALID_DEADLINE_2)
            .withIsDone(false).build();

    public static final Task CS2101 = new TaskBuilder()
            .withId(TASK_ID_3)
            .withTaskName(VALID_TASK_NAME_4)
            .withDescription(VALID_DESCRIPTION_2)
            .withDeadline(EMPTY_DEADLINE)
            .withIsDone(true).build();

    public static final Task CS3230 = new TaskBuilder()
            .withId(TASK_ID_4)
            .withTaskName(VALID_TASK_NAME_5)
            .withDescription(EMPTY_DESCRIPTION)
            .withDeadline(EMPTY_DEADLINE)
            .withIsDone(false).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        ab.setTasks(getTypicalTasks());
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BUY_MILK, CS2103T, CS2101, CS3230));
    }
}
