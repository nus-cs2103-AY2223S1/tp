package modtrekt.model;

import javafx.collections.ObservableList;
import modtrekt.model.task.Task;

/**
 * Unmodifiable view of an task book
 */
public interface ReadOnlyTaskBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();


}
