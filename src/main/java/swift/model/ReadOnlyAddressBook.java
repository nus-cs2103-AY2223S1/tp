package swift.model;

import javafx.collections.ObservableList;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the person-task bridge list.
     * This list will not contain any duplicate bridges.
     */
    ObservableList<PersonTaskBridge> getBridgeList();
}
