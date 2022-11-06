package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniquePersonList;

/**
 * Wraps all data at ArchivedTaskBook level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ArchivedTaskList implements ReadOnlyAddressBook {

    private final UniquePersonList archivedTasks;

    public ArchivedTaskList() {
        archivedTasks = new UniquePersonList();
    }

    /**
     * Creates an ArchivedTaskBook using the Persons in the {@code toBeCopied}
     */
    public ArchivedTaskList(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setArchivedTasks(List<Task> archivedTasks) {
        this.archivedTasks.setPersons(archivedTasks);
    }

    /**
     * Resets the existing data of this {@code ArchivedTaskBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setArchivedTasks(newData.getPersonList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the ArchivedTaskBook.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return archivedTasks.contains(task);
    }

    /**
     * Adds a task to the ArchivedTaskBook.
     * The task must not already exist in the ArchivedTaskBook.
     */
    public void addTask(Task task) {
        archivedTasks.add(task);
    }


    private void setTasks(Task targetTask, Task editedTask) {
        requireNonNull(editedTask);
        archivedTasks.setPerson(targetTask, editedTask);
    }

    public void removeTask(Task task) {
        archivedTasks.remove(task);
    }

    @Override
    public String toString() {
        Iterator<Task> iterator = archivedTasks.iterator();
        StringBuilder str = new StringBuilder();
        while (iterator.hasNext()) {
            str.append(iterator.next());
        }
        return archivedTasks.asUnmodifiableObservableList().size() + "Tasks:\n" + str.toString();
    }

    @Override
    public ObservableList<Task> getPersonList() {
        return archivedTasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchivedTaskList // instanceof handles nulls
                && archivedTasks.equals(((ArchivedTaskList) other).archivedTasks));
    }

    @Override
    public int hashCode() {
        return archivedTasks.hashCode();
    }
}
