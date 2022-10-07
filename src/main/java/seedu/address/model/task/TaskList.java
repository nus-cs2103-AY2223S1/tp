package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the tasklist.
 */
public class TaskList implements Iterable<Task> {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Adds a Task to the TaskList.
     * @param task The Task tp be added.
     * @return A String describing the number of tasks in the TaskList.
     */
    public String addTask(Task task) {
        requireNonNull(task);

        internalList.add(task);

        return TaskUi.addText(internalList.get(internalList.size() - 1).toString(), internalList.size());
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }
}
