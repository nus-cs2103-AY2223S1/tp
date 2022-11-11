package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the task-list level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TaskList implements ReadOnlyTaskList {

    private final UniqueTaskList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueTaskList();
    }

    public TaskList() {}

    /**
     * Creates an TaskList using the Persons in the {@code toBeCopied}
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setPersons(List<Task> tasks) {
        this.persons.setPersons(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        setPersons(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasPerson(Task task) {
        requireNonNull(task);
        return persons.contains(task);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addPerson(Task p) {
        persons.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    public void setPerson(Task target, Task editedTask) {
        requireNonNull(editedTask);

        persons.setPerson(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskList}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Task key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && persons.equals(((TaskList) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
