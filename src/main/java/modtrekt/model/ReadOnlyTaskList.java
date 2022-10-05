package modtrekt.model;

import javafx.collections.ObservableList;
import modtrekt.model.person.Person;
import modtrekt.model.task.Task;

public interface ReadOnlyTaskList {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();
}
