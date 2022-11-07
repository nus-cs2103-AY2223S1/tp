package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;

/**
 * Wraps all data at the Task List level
 */
public class TaskList implements ReadOnlyTaskList {

    private final ObservableList<Task> tasks;

    {
        tasks = FXCollections.observableArrayList();
    }

    public TaskList() {}

    /**
     * Creates the TaskList using Tasks in the {@code toBeCopied}
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return FXCollections.unmodifiableObservableList(tasks);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code task} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i));
        }
    }
    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);
        setTasks(newData.getTaskList());
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task t) {
        requireNonNull(t);
        int index = findIndexOfTask(t);
        tasks.add(index, t);
    }

    /**
     * Checks a task for duplicates
     */
    public boolean hasTask(Task t) {
        requireNonNull(t);
        return tasks.contains(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     */
    public void setTask(Task target, Index targetIndex) {
        requireNonNull(target);
        requireNonNull(targetIndex);
        tasks.remove(targetIndex.getZeroBased());
        addTask(target);
    }

    /**
     * Deletes the task at the specified {@code Index}.
     */
    public void deleteTask(Index index) {
        requireNonNull(index);
        tasks.remove(index.getZeroBased());
    }

    /**
     * Finds the index of the task to be inserted into the taskList using binary search.
     * Order of insertion is according to deadline. The taskList will be sorted in ascending order based on
     * deadline.
     *
     * @param task task to be inserted.
     * @return Returns the index of the position the new task should be added in.
     */
    public int findIndexOfTask(Task task) {
        requireNonNull(task);
        return binarySearch(0, tasks.size(), task);
    }

    /**
     * Runs binary search function to add task in ascending order using deadline.
     * Given that there are multiple task of the same deadline, this function will
     * return the index most left of the task that has the same deadline.
     *
     * @param start Starting index of the taskList.
     * @param end Number of task in the taskList.
     * @param task This is the task to be inserted.
     * @return Returns the index of the position the new task should be added in.
     */
    private int binarySearch(int start, int end, Task task) {
        int low = start;
        int high = end - 1;
        LocalDate givenDate = task.getDeadline();
        while (low <= high) {
            int mid = low + (high - low) / 2;

            LocalDate currentDate = tasks.get(mid).getDeadline();

            if (currentDate.isAfter(givenDate) || currentDate.isEqual(givenDate)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    @Override
    public int hashCode() {
        return this.tasks.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if they are the same object.
            || (other instanceof TaskList
            && tasks.hashCode() == (((TaskList) other).tasks).hashCode()); // temp only TODO: Implement UniqueTaskList
    }
}
