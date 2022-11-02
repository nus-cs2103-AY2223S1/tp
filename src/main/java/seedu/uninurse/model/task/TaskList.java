package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.ModificationType;

/**
 * Represents a list of tasks for a particular person.
 * Supports a minimal set of list operations.
 */
public class TaskList implements GenericList<Task> {
    private List<Task> internalTaskList;
    private List<Task> filteredTaskLists;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        internalTaskList = new ArrayList<>();
        filteredTaskLists = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with a given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        requireNonNull(tasks);
        internalTaskList = tasks;
        internalTaskList.sort(Comparator.comparing(Task::getDateTime));
        filteredTaskLists = new ArrayList<>(tasks);
        filteredTaskLists.sort(Comparator.comparing(Task::getDateTime));
    }

    /**
     * Adds a task to the {@code TaskList},
     * @param task The task to be added.
     * @return The updated {@code TaskList} containing the added task.
     */
    @Override
    public TaskList add(Task task) {
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.add(task);
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        return new TaskList(updatedTasks);
    }

    /**
     * Edits a task in the {@code TaskList}.
     *
     * @param index of the task to be edited.
     * @param task that is updated.
     * @return The updated {@code TaskList}.
     */
    @Override
    public TaskList edit(int index, Task task) {
        assert(index >= 0 && index <= this.size());
        ArrayList<Task> updatedTasks = new ArrayList<>(internalTaskList);
        updatedTasks.set(index, task);
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        return new TaskList(updatedTasks);
    }

    /**
     * Removes a task from the {@code TaskList}.
     *
     * @param index The index of the task to be deleted.
     * @return The updated {@code TaskList}.
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
     * @return the task at the specified index.
     */
    public Task get(int index) {
        assert(index >= 0 && index <= this.size());
        return internalTaskList.get(index);
    }

    /**
     * Returns the size of the list.
     */
    @Override
    public int size() {
        return internalTaskList.size();
    }

    /**
     * Returns true if the task list is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.internalTaskList.isEmpty();
    }

    /**
     * Returns the internal task list.
     */
    public List<Task> getTasks() {
        return filteredTaskLists;
    }

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
     * Returns a list of {@code Task}s that are due on a given day.
     */
    public List<Task> getAllTasksOnDay(DateTime test) {
        return internalTaskList.stream().filter(t -> t.isTaskOnDay(test)).collect(Collectors.toList());
    }

    /**
     * Returns a list of {@code Task}s that are due today.
     */
    public List<Task> getAllTasksToday() {
        return internalTaskList.stream().filter(Task::isTaskToday).collect(Collectors.toList());
    }

    /**
     * Returns true if there are tasks today.
     */
    public boolean containsTaskToday() {
        return !this.getAllTasksToday().isEmpty();
    }

    public boolean hasTask(Task task) {
        return internalTaskList.contains(task);
    }

    /**
     * Sets the filteredTaskList to contain the tasks that passes the filter.
     */
    public void filterTasks(Predicate<Task> filter) {
        filteredTaskLists = (ArrayList<Task>) internalTaskList.stream().filter(filter).collect(Collectors.toList());
        filteredTaskLists.sort(Comparator.comparing(Task::getDateTime));
    }

    public void showAllTasks() {
        filterTasks(t -> true);
    }

    /**
     * Updates this TaskList with all the new tasks generated because of RecurringTasks
     * and their next RecurringTask once past the task date.
     */
    public void updateTasks() {
        List<Task> updatedTasks = new ArrayList<>(internalTaskList);
        for (Task task : internalTaskList) {
            if (task.passedTaskDate()) {
                updatedTasks = task.updateTask(updatedTasks);
            }
        }
        updatedTasks.sort(Comparator.comparing(Task::getDateTime));
        internalTaskList = updatedTasks;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int index = 0; index < filteredTaskLists.size(); index++) {
            Task t = filteredTaskLists.get(index);
            if (index == 0) {
                builder.append(index + 1)
                        .append(". ")
                        .append(t);
            } else {
                builder.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(t);
            }
        }
        return builder.toString();
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
