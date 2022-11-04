package seedu.uninurse.model.task;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.ModificationType;
import seedu.uninurse.model.task.exceptions.DuplicateTaskException;

/**
 * Represents a list of tasks for a particular person.
 * Supports a minimal set of list operations.
 */
public class TaskList implements GenericList<Task> {
    private List<Task> internalTaskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        internalTaskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks to initially put within TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        requireAllNonNull(tasks);
        internalTaskList = tasks;
        internalTaskList.sort(Comparator.comparing(Task::getDateTime));
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task to be added.
     * @return The updated {@code TaskList} containing the added task.
     */
    @Override
    public TaskList add(Task task) {
        requireAllNonNull(task);

        if (this.internalTaskList.contains(task)) {
            throw new DuplicateTaskException();
        }

        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.add(task);
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        return new TaskList(updatedTasks);
    }

    /**
     * Edits a task in the TaskList.
     *
     * @param index of the task to be edited.
     * @param task that is updated.
     * @return The updated TaskList.
     */
    @Override
    public TaskList edit(int index, Task task) {
        assert(index >= 0 && index <= this.size());

        if (this.internalTaskList.contains(task)) {
            throw new DuplicateTaskException();
        }

        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.set(index, task);
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        return new TaskList(updatedTasks);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param index of the task to be deleted.
     * @return The updated TaskList.
     */
    @Override
    public TaskList delete(int index) {
        assert(index >= 0 && index <= this.size());
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.remove(index);
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        return new TaskList(updatedTasks);
    }

    /**
     * Gets the Task at the given index.
     *
     * @param index to retrieve from.
     * @return The Task at the given index.
     */
    public Task get(int index) {
        assert(index >= 0 && index <= this.size());
        return internalTaskList.get(index);
    }

    /**
     * @return The size of the list.
     */
    @Override
    public int size() {
        return internalTaskList.size();
    }

    /**
     * @return True if the task list is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.internalTaskList.isEmpty();
    }

    /**
     * @return The internal List of Tasks (unmodifiable).
     */
    @Override
    public List<Task> getInternalList() {
        return Collections.unmodifiableList(internalTaskList);
    }

    @Override
    public List<ListModificationPair> getDiff(GenericList<Task> otherTaskList) {
        List<ListModificationPair> listModificationPairs = new ArrayList<>();

        if (equals(otherTaskList)) {
            return listModificationPairs;
        }

        if (size() == 0 && otherTaskList.size() == 1) {
            listModificationPairs.add(new ListModificationPair(ModificationType.ADD, 0));
            return listModificationPairs;
        }

        if (size() == 1 && otherTaskList.size() == 0) {
            listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, 0));
            return listModificationPairs;
        }

        int pointer = 0;
        while (get(pointer).equals(otherTaskList.get(pointer))) {
            pointer++;
            if (pointer == size() || pointer == otherTaskList.size()) {
                if (size() < otherTaskList.size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.ADD, pointer));
                } else if (otherTaskList.size() < size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, pointer));
                }
                break;
            }
        }
        listModificationPairs.add(new ListModificationPair(ModificationType.EDIT, pointer));
        return listModificationPairs;
    }

    /**
     * Returns a list of Tasks that are due on a given day.
     *
     * @param day to get the Tasks on.
     * @return The List of Tasks on the given Day.
     */
    public List<Task> getAllTasksOnDay(DateTime day) {
        return internalTaskList.stream().filter(t -> t.isTaskOnDay(day)).collect(Collectors.toList());
    }

    /**
     * @return The List of Tasks that are for today.
     */
    public List<Task> getAllTasksToday() {
        return internalTaskList.stream().filter(Task::isTaskToday).collect(Collectors.toList());
    }

    /**
     * @return If the TaskList contains any Tasks that are for today.
     */
    public boolean containsTaskToday() {
        return !this.getAllTasksToday().isEmpty();
    }

    /**
     * Checks whether the TaskList has the given Task.
     *
     * @param task to check for.
     * @return Whether the given Task is in TaskList.
     */
    public boolean hasTask(Task task) {
        return internalTaskList.contains(task);
    }

    /**
     * Updates this TaskList with all the new tasks generated because of RecurringTasks
     * and their next RecurringTask once past the task date.
     */
    public void updateTasks() {
        List<Task> updatedTasks = new ArrayList<>(internalTaskList);
        Set<Task> tmpTasks = new HashSet<Task>();
        for (Task task : internalTaskList) {
            if (task.passedTaskDate()) {
                tmpTasks.addAll(task.updateTask());
            }
        }
        updatedTasks.addAll(tmpTasks.stream().filter(task -> !updatedTasks.contains(task))
                .collect(Collectors.toList()));
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        internalTaskList = updatedTasks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int index = 0; index < internalTaskList.size(); index++) {
            Task t = internalTaskList.get(index);
            if (index == 0) {
                sb.append(index + 1)
                        .append(". ")
                        .append(t);
            } else {
                sb.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(t);
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && internalTaskList.equals(((TaskList) other).internalTaskList));
    }

    @Override
    public int hashCode() {
        return internalTaskList.hashCode();
    }
}
