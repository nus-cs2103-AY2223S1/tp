package seedu.address.model.module.task;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;

/**
 * A list of tasks that supports a minimal set of list operations. Uniqueness
 * of tasks is not enforced.
 */
public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructs an empty {@code TaskList}
     */
    public TaskList() {}

    /**
     * Constructs a {@code TaskList} containing the tasks in the
     * {@code List} of {@code Task} provided.
     * @param taskList List of tasks to be added.
     */
    public TaskList(List<Task> taskList) {
        internalList.addAll(taskList);
    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Index indexToDelete) {
        requireNonNull(indexToDelete);
        int zeroBasedIndexOfTaskToDelete = indexToDelete.getZeroBased();
        Boolean indexIsNonNegative = zeroBasedIndexOfTaskToDelete >= 0;
        Boolean indexIsInList =
                zeroBasedIndexOfTaskToDelete < internalList.size();
        assert indexIsNonNegative = true;
        assert indexIsInList = true;
        internalList.remove(zeroBasedIndexOfTaskToDelete);
    }

    /**
     * Swaps the tasks at the two indexes given.
     */
    public void swapTasks(Index indexOfFirstTask,
                          Index indexOfSecondTask) {
        Collections.swap(internalList,
                indexOfFirstTask.getZeroBased(),
                indexOfSecondTask.getZeroBased());
    }

    /**
     * Replaces all {@code Task}s in the {@code internalList} with those
     * in the given {@code TaskList}
     * @param replacement The {@code TaskList} with the tasks we wish to
     *                    replace with.
     */
    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if the task list contains a duplicate task.
     */
    public Boolean containsDuplicate() {
        return !tasksAreUnique(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && internalList.equals(((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

