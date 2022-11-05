package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .weakEquality comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList teams;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid
     * duplication between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid
     * duplication among constructors.
     */
    {
        persons = new UniquePersonList();
        teams = new UniqueGroupList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}. {@code persons} must not contain
     * duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}. {@code persons} must not contain
     * duplicate persons.
     */
    public void setGroups(List<Group> groups) {
        this.teams.setItems(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setGroups(newData.getTeamsList());
        setTasks(newData.getTasksList());
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
     * Adds a person to the address book. The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        persons.sort();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}. {@code target}
     * must exist in the address book. The person identity of {@code editedPerson} must not be the same
     * as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes all person that satisfies the predicate
     *
     * @param predicate
     */
    public void removePersonIf(Predicate<Person> predicate) {
        persons.removeIf(predicate);
    }

    /**
     * Applies effect for each person
     */
    public void forEachPerson(Consumer<? super Person> consumer) {
        persons.forEach(consumer);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasGroup(Group team) {
        requireNonNull(team);
        return teams.contains(team);
    }

    /**
     * Adds a person to the address book. The person must not already exist in the address book.
     */
    public void addTeam(Group g) {
        teams.add(g);
        teams.sort();
    }

    /**
     * Removes {@code grp} from this {@code AddressBook}. {@code grp} must exist in the address book.
     */
    public void removeTeam(Group grp) {
        teams.remove(grp);
    }

    /**
     * Removes all teams that satisfies the predicate
     *
     * @param predicate
     */
    public void removeTeamIf(Predicate<Group> predicate) {
        teams.removeIf(predicate);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the team in the address book.
     */
    public void addTask(Task task) {
        requireNonNull(task);
        tasks.add(task);
        tasks.sort();
    }

    /**
     * Replaces the contents of the task list with {@code tasks}. {@code tasks} must not contain
     * duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setItems(tasks);
    }

    /**
     * Removes {@code task} from its group. Task must exist in address book.
     */
    public void removeTask(Task task) {
        requireNonNull(task);
        tasks.remove(task);
    }

    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
        tasks.sort();
    }

    /**
     * Removes all tasks that satisfies the predicate
     *
     * @param predicate
     */
    public void removeTaskIf(Predicate<Task> predicate) {
        tasks.removeIf(predicate);
    }

    //// util methods

    @Override
    public String toString() {
        return String.format("%d persons, %d teams, %d task", persons.asUnmodifiableObservableList().size(),
            teams.asUnmodifiableObservableList().size(), tasks.asUnmodifiableObservableList().size());
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && teams.equals(((AddressBook) other).teams)
                && tasks.equals(((AddressBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() ^ teams.hashCode() ^ tasks.hashCode();
    }

    @Override
    public ObservableList<Group> getTeamsList() {
        return teams.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTasksList() {
        return tasks.asUnmodifiableObservableList();
    }
}
