package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of Project and Task objects to be used in tests.
 */
public class TypicalAddressBook {

    /**
     * Returns an {@code AddressBook} with all the typical projects and tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project project : TypicalProjects.getTypicalProjects()) {
            ab.addProject(project);
        }
        for (Task task : TypicalTasks.getTypicalTask()) {
            ab.addTask(task);
        }
        return ab;
    }
}
