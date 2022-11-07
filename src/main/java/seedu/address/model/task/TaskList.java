package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * Represents the tasklist.
 */
public class TaskList implements Iterable<Task> {
    private static final Comparator<Task> DATE_COMPARATOR = Comparator.comparing(Task::getDeadline);
    private static final Comparator<Task> ID_COMPARATOR = Comparator.comparing(Task::getId);

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private Boolean isSortByDeadline = false;

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Adds a Task to the TaskList.
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        requireNonNull(task);
        if (contains(task)) {
            throw new DuplicateTaskException();
        }
        internalList.add(task);

        if (isSortByDeadline) {
            sortByDeadline();
        }
    }

    /**
     * Deletes a Task from the TaskList.
     * @param toRemove The Task to be added.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task description of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);

        if (isSortByDeadline) {
            sortByDeadline();
        }
    }

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Sorts the task list by deadline
     */
    public void sortByDeadline() {
        isSortByDeadline = true;
        internalList.sort(DATE_COMPARATOR);
    }

    /**
     * Sorts the task list by id.
     */
    public void sortById() {
        isSortByDeadline = false;
        internalList.sort(ID_COMPARATOR);
    }

    public Boolean isSortByDeadline() {
        return isSortByDeadline;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
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
