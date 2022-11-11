package taskbook.model;

import javafx.collections.ObservableList;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

/**
 * Unmodifiable view of an task book
 */
public interface ReadOnlyTaskBook {

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
}
