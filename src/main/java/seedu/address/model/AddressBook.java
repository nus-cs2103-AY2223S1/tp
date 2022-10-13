package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.DistinctModuleList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.DistinctTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final DistinctModuleList modules;
    private final DistinctTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new DistinctModuleList();
        tasks = new DistinctTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
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

    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
        setModules(newData.getModuleList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void addModule(Module mod) {
        modules.addModule(mod);
    }

    //// task-level operations

    /**
     * Returns true if a task with the same module and description as {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    public boolean hasTaskWithModule(Module module) {
        return tasks.containsModule(module);
    }

    /**
     * Adds a task to the task list.
     * The task must not already exist in the task list.
     */
    public void addTask(Task task) {
        tasks.addTask(task);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must be the same as task identity of {@code target}.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     *
     * @throws DuplicateTaskException if task identity of {@code editedTask} is the same as another task
     *     in the list (other than {@code target}).
     */
    public void replaceTask(Task target, Task editedTask) throws DuplicateTaskException {
        requireAllNonNull(target, editedTask);

        tasks.replaceTask(target, editedTask);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + "\n" + modules.getUnmodifiableModuleList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Checks whether the module list contains the module.
     *
     * @param module The module that is being checked.
     * @return true if the module list contains the module; else returns false.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.containsModule(module);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     * {@code key} must not be tied to any tasks in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.getUnmodifiableModuleList();
    }

    /**
     * Replaces the given Module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module list.
     *
     * @throws DuplicateModuleException if module identity of {@code editedModule} is the same as another module
     *     in the list (other than {@code target}).
     */
    public void replaceModule(Module target, Module editedModule) throws DuplicateModuleException {
        requireAllNonNull(target, editedModule);

        modules.replaceModule(target, editedModule);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.getUnmodifiableTaskList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
