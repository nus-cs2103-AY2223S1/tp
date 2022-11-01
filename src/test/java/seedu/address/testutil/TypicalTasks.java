package seedu.address.testutil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASKONE = new TaskBuilder().withName("Task one").withModule("CS2030S").build();
    public static final Task TASKTWO = new TaskBuilder().withName("Task two").withModule("CS2030S").build();
    public static final Task HOMEWORK = new TaskBuilder().withName("homework").withModule("CS2030s").build();
    public static final Task PYP = new TaskBuilder().withName("PAST YEAR PAPER").withModule("CS2030s").build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */

    public static AddressBook getTypicalAddressBookForTask() {
        AddressBook ab = new AddressBook();
        for (Module module: getTypicalModules()) {
            ab.addModule(module);
        }
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }

        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASKONE,TASKTWO,HOMEWORK,PYP));
    }
}
