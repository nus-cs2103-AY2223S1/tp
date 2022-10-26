package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniquePersonList;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ArchivedTaskBook implements ReadOnlyAddressBook {

    private final UniquePersonList archivedTasks;

    public ArchivedTaskBook() {
        archivedTasks = new UniquePersonList();
    }

    public ArchivedTaskBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setArchivedTasks(List<Task> archivedTasks) {
        this.archivedTasks.setPersons(archivedTasks);
    }

    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setArchivedTasks(newData.getPersonList());
    }

    public boolean hasTask(Task task) {
        requireNonNull(task);
        return archivedTasks.contains(task);
    }

    public void addTask(Task task) {
        archivedTasks.add(task);
    }

    private void setTasks(Task targetTask, Task editedTask) {
        requireNonNull(editedTask);
        archivedTasks.setPerson(targetTask, editedTask);
    }

    private void removeTask(Task task) {
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
                || (other instanceof ArchivedTaskBook // instanceof handles nulls
                && archivedTasks.equals(((ArchivedTaskBook) other).archivedTasks));
    }

    @Override
    public int hashCode() {
        return archivedTasks.hashCode();
    }
}
