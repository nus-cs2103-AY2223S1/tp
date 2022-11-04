package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in
 * tests.
 */
public class TypicalTasks {

    public static final Task FIX_BUG = new TaskBuilder().withName("Fix bug")
            .withTags("BUG", "URGENT").withAttribute("Storage", "Store tasks in hard disk")
            .build();
    public static final Task DO_PAPERWORK = new TaskBuilder().withName("Do paperwork").withTags("URGENT")
            .withAttribute("Paperwork for management", "Organize paperwork neatly").build();
    public static final Task BUY_PRINTER = new TaskBuilder().withName("Buy printer")
            .withTags("URGENT").withAttribute("Priority", "High").build();

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
        return new ArrayList<>(Arrays.asList(FIX_BUG, DO_PAPERWORK, BUY_PRINTER));
    }
}
