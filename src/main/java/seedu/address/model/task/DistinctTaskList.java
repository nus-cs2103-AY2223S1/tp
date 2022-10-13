package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskIdentityModifiedException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * This class represents a list which contains Tasks objects which are distinct from
 * each other. Tasks are distinct from each other when they have different descriptions and
 * module codes.
 */
public class DistinctTaskList implements Iterable<Task> {

    public final ObservableList<Task> taskList = FXCollections.observableArrayList();
    public final ObservableList<Task> unmodifiableTaskList = FXCollections
            .unmodifiableObservableList(taskList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return taskList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Returns true if the list contains a task with an equivalent module as the given argument.
     */
    public boolean containsModule(Module toCheck) {
        requireNonNull(toCheck);
        return taskList.stream().map(Task::getModule).anyMatch(toCheck::isSameModuleCode);
    }

    /**
     * Adds the task to the taskList.
     * The task must not already exist in the list.
     *
     * @param taskAdded The task to be added.
     */
    public void addTask(Task taskAdded) {
        requireNonNull(taskAdded);
        //Might have to add address book as param to check if addressBook already has module
        if (contains(taskAdded)) {
            throw new DuplicateTaskException();
        }
        taskList.add(taskAdded);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} should be the same as task identity of {@code target}.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = taskList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask)) {
            throw new TaskIdentityModifiedException();
        }

        taskList.set(index, editedTask);
    }

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     *
     * @throws DuplicateTaskException if task identity of {@code editedTask} is the same as another task
     * in the list (other than {@code target}).
     */
    public void replaceTask(Task target, Task editedTask) throws DuplicateTaskException {
        requireAllNonNull(target, editedTask);

        int index = taskList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }
        if (contains(editedTask) && !editedTask.isSameTask(target)) {
            throw new DuplicateTaskException();
        }
        taskList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the tasklist.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!taskList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    @Override
    public boolean equals(Object otherTask) {
        return otherTask == this
                || (otherTask instanceof DistinctTaskList
                && taskList.equals(((DistinctTaskList) otherTask).taskList));
    }

    public ObservableList<Task> getUnmodifiableTaskList() {
        return unmodifiableTaskList;
    }

    public void setTasks(List<Task> tasks) {
        requireNonNull(tasks);
        taskList.setAll(tasks);
    }
}
