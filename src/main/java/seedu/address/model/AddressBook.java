package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .weakEquality comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList teams;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        teams = new UniqueGroupList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}. {@code persons} must not contain duplicate
     * persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}. {@code persons} must not contain duplicate persons.
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
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}. {@code target} must exist in the
     * address book. The person identity of {@code editedPerson} must not be the same as another existing person in the
     * address book.
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
    public void addGroup(Group g) {
        teams.add(g);
    }

    /**
     * Removes {@code grp} from this {@code AddressBook}. {@code grp} must exist in the address book.
     */
    public void removeTeam(Group grp) {
        teams.remove(grp);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        Group parent = task.getParentGroup();
        return hasGroup(parent) && parent.hasTask(task);
    }

    /**
     * Adds a task to the team in the address book.
     */
    public void addTask(Task task) {
        requireNonNull(task);
        Group parent = task.getParentGroup();
        if (hasGroup(parent)) {
            Group myGroup = teams.get(parent);
            task.setParent(myGroup);
            myGroup.addTask(task);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Removes {@code task} from its group. Task must exist in address book.
     */
    public void removeTask(Task task) {
        requireNonNull(task);
        Group parent = task.getParentGroup();
        if (hasGroup(parent)) {
            Group myGroup = teams.get(parent);
            myGroup.removeTask(task);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Adds a {@code field} to the each person in the address book.
     * Field must not exist in the address book.
     */
    public void addField(String fieldName) {
        persons.addField(fieldName);
    }
    //// util methods

    @Override
    public String toString() {
        return String.format("%d persons, %d task", persons.asUnmodifiableObservableList().size(),
                teams.asUnmodifiableObservableList().size());
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
                && persons.equals(((AddressBook) other).persons) && teams.equals(((AddressBook) other).teams));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() ^ teams.hashCode();
    }

    @Override
    public ObservableList<Group> getTeamsList() {
        return teams.asUnmodifiableObservableList();
    }
}
