package seedu.address.logic.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.task.exceptions.DuplicateTaskException;
import seedu.address.logic.task.exceptions.TaskNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of tasks
 * uses Task#isSameTask(Task) for equality to ensure that the task being added or updated is unique in terms of
 * identity in the TaskList. However, the removal of a task uses Task#equals(Object) to ensure that the task with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     *
     * @param toCheck Task to check against
     * @return true if the list contains an equivalent task as the given argument
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     *
     * @param toAdd Task to be added to the list
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     *
     * @param toDelete Task to be deleted from the list
     */
    public void delete(Task toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Replaces the task {@code toEdit} in the list with {@code editedTask}.
     * {@code toEdit} must exist in the list.
     * The task fields of {@code editedTask} must not be the same as another existing task in the list.
     *
     * @param toEdit     Task to be replaced by the task {@code editedTask}
     * @param editedTask Task to replace the task {@code toEdit}
     */
    public void edit(Task toEdit, Task editedTask) {
        requireAllNonNull(toEdit, editedTask);

        int index = internalList.indexOf(toEdit);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (toEdit.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Returns a Task from the list which corresponds with {@code toFind}.
     * {@code toFind} must exist in the list.
     *
     * @param toFind the Task to find in the list
     * @return a Task from the list which corresponds with {@code toFind}
     */
    public Task find(Task toFind) {
        requireNonNull(toFind);
        if (!contains(toFind)) {
            throw new TaskNotFoundException();
        }
        return internalList.get(internalList.indexOf(toFind));
    }

    /**
     * Sorts the task list based on priority.
     */
    public void sortByPriority() {
        internalList.sorted(new SortByPriority());
    }

    /**
     * Sorts the task list based on category.
     */
    public void sortByCategory() {
        internalList.sorted(new SortByCategory());
    }

    /**
     * Sorts the task list based on deadline.
     */
    public void sortByDeadline() {
        internalList.sorted(new SortByDeadline());
    }

    /**
     * Sorts the task list based on the respective people that the tasks are assigned to.
     */
    public void sortByPerson() {
        internalList.sorted(new SortByPerson());
    }

    /**
     * Sorts the task list based on status.
     */
    public void sortByStatus() {
        internalList.sorted(new SortByStatus());
    }

    /**
     * Filters the task list based on the given Priority {@code toFilter}.
     *
     * @param toFilter Priority to filter the task list by
     */
    public void filter(Priority toFilter) {
        requireNonNull(toFilter);
        Predicate<Task> filterer = i -> (i.getPriority().equals(toFilter));
        internalList.filtered(filterer);
    }

    /**
     * Filters the task list based on the given Category {@code toFilter}.
     *
     * @param toFilter Category to filter the task list by
     */
    public void filter(TaskCategory toFilter) {
        requireNonNull(toFilter);
        Predicate<Task> filterer = i -> (i.getCategory().equals(toFilter));
        internalList.filtered(filterer);
    }

    /**
     * Filters the task list based on the given Deadline {@code toFilter}.
     *
     * @param toFilter Deadline to filter the task list by
     */
    public void filter(TaskDeadline toFilter) {
        requireNonNull(toFilter);
        Predicate<Task> filterer = i -> (i.getDeadline().equals(toFilter));
        internalList.filtered(filterer);
    }

    /**
     * Filters the task list based on the given assigned Person {@code toFilter}.
     *
     * @param toFilter Person to filter the task list by
     */
    public void filter(Person toFilter) {
        requireNonNull(toFilter);
        Predicate<Task> filterer = i -> (i.getPerson().equals(toFilter));
        internalList.filtered(filterer);
    }

    /**
     * Filters the task list based on the given Status {@code toFilter}.
     *
     * @param toFilter Status to filter the task list by
     */
    public void filter(boolean toFilter) {

        Predicate<Task> filterer = i -> (i.isDone() == toFilter);
        internalList.filtered(filterer);
    }

    public void setTasks(TaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     *
     * @param tasks new list of tasks to replace the current list of tasks
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof TaskList && internalList.equals(((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique tasks.
     *
     * @param tasks List of tasks to check
     * @return true if {@code tasks} contains only unique tasks.
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Task t : internalList) {
            str.append("\n" + t);
        }
        return str.toString();
    }
}
