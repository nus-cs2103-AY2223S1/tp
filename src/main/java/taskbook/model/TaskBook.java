package taskbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.person.UniquePersonList;
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.Task;
import taskbook.model.task.TaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TaskBook implements ReadOnlyTaskBook {

    private final UniquePersonList persons;
    private final TaskList tasks;

    /**
     * Creates a new TaskBook
     */
    public TaskBook() {
        this.persons = new UniquePersonList();
        this.tasks = new TaskList();
    }

    /**
     * Creates a TaskBook using the Persons in the {@code toBeCopied}
     */
    public TaskBook(ReadOnlyTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTasks(newData.getTaskList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the task book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns the person with the same name as {@code name} if they exist in the task book.
     */
    public Person findPerson(Name name) {
        requireNonNull(name);
        return persons.find(name);
    }

    /**
     * Adds a person to the task book.
     * The person must not already exist in the task book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the task book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the task book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        propagateNameChange(target, editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Propagates name change to all associated tasks.
     */
    private void propagateNameChange(Person original, Person edited) {
        Name name = original.getName();
        Name editedName = edited.getName();

        if (name.equals(editedName)) {
            return;
        }

        for (Task task : tasks) {
            if (!task.getName().equals(name)) {
                continue;
            }

            EditTaskDescriptor descriptor = new EditTaskDescriptor();
            descriptor.setName(editedName);
            Task editedTask;
            try {
                editedTask = task.createEditedCopy(descriptor);
            } catch (CommandException e) {
                // Should not happen because name exists on all task types.
                String logMessage = String.format("Failed to propagate name change to task %s.", task);
                Logger.getGlobal().warning(logMessage);
                continue;
            }
            tasks.setTask(task, editedTask);
        }
    }

    /**
     * Returns true if a person can be deleted.
     * A person cannot be deleted if there are tasks associated with that person.
     * Returns false if the person does not exist in the {@code TaskBook}.
     *
     * @param key Key of the person.
     * @return True if a person can be deleted.
     */
    public boolean canDeletePerson(Person key) {
        requireNonNull(key);

        if (!hasPerson(key)) {
            return false;
        }

        Name name = key.getName();

        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Removes {@code key} from this {@code TaskBook}.
     * {@code key} must exist in the task book.
     * {@code key} must be deletable as per {@link TaskBook#canDeletePerson(Person)}.
     */
    public void removePerson(Person key) {
        if (!canDeletePerson(key)) {
            return;
        }

        persons.remove(key);
    }

    //// task-level operations
    /**
     * Returns true if a task {@code task} exists in the task book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the task book.
     * The task must not already exist in the task book.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task {@code editedTask} must not be the same as another existing task in the task book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Deletes the given task.
     * Task must exist in the task book.
     */
    public void deleteTask(Task t) {
        tasks.remove(t);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBook // instanceof handles nulls
                && persons.equals(((TaskBook) other).persons)
                && tasks.equals(((TaskBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, tasks);
    }
}
