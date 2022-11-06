package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in
 * tests.
 */
public class TypicalTasks {

    private static final Group WRAPPER_GROUP = new GroupBuilder().withName("t1").build();

    public static final Task FIX_BUG = new TaskBuilder().withName("Fix bug")
            .withDescription("Bug related to the Alpha command")
            .withCompletedTime(LocalDateTime.of(2022, 11, 4, 22, 7, 53, 947688600))
            .withAttribute("Logic", "Check null value in command").withParent(WRAPPER_GROUP).build();
    public static final Task DO_PAPERWORK = new TaskBuilder().withName("Do paperwork")
            .withDescription("Administrative tasks by management").withParent(WRAPPER_GROUP).build();
    public static final Task BUY_PRINTER = new TaskBuilder().withName("Buy printer")
            .withDescription("Buy printer from XYZ street")
            .withAttribute("Priority", "High").withParent(WRAPPER_GROUP).build();

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addTeam(WRAPPER_GROUP);
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(FIX_BUG, DO_PAPERWORK, BUY_PRINTER));
    }
}
