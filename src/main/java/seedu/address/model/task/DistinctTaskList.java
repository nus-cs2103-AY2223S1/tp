package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;

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
