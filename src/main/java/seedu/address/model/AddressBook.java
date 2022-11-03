package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.team.Team;
import seedu.address.model.team.UniqueTeamList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTaskList tasks;
    private final UniqueTeamList teams;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        teams = new UniqueTeamList();
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

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setTeams(List<Team> teams) {
        this.teams.setTeams(teams);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setTeams(newData.getTeamList());
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
        for (Team t : teams) {
            if (t.containMember(target)) {
                t.setMember(target, editedPerson);
            }
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// team-level operations

    /**
     * Returns true if a team with the same name as {@code team} exists in the address book.
     */
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return teams.contains(team);
    }

    /**
     * Adds a team to the address book.
     * The team must not already exist in the address book.
     */
    public void addTeam(Team t) {
        teams.add(t);
    }

    /**
     * Removes {@code t} from this {@code AddressBook}.
     * {@code t} must exist in the address book.
     */
    public void removeTeam(Team t) {
        teams.remove(t);
    }

    /**
     * Adds person to the given team.
     */
    public void addPersonToTeam(Person person, Team team) {
        team.addMember(person);
    }

    /**
     * Removes person from the given team.
     */
    public void removePersonFromTeam(Person person, Team team) {
        team.removeMember(person);
    }

    /**
     * Changes team name to given name.
     */
    public void setTeamName(Index targetIndex, seedu.address.model.team.Name newTeamName) {
        requireNonNull(newTeamName);
        teams.setTeamName(targetIndex.getZeroBased(), newTeamName);
    }

    //// task-level operations

    public boolean teamHasTask(Index index, Task task) {
        return teams.teamHasTask(index.getZeroBased(), task);
    }

    public void addTask(Index index, Task task) {
        teams.addTask(index.getZeroBased(), task);
    }

    public void markTask(Index teamIndex, Index taskIndex) {
        teams.markTask(teamIndex.getZeroBased(), taskIndex.getZeroBased());
    }

    public void unmarkTask(Index teamIndex, Index taskIndex) {
        teams.unmarkTask(teamIndex.getZeroBased(), taskIndex.getZeroBased());
    }

    public void deleteTask(Index teamIndex, Index taskIndex) {
        teams.deleteTask(teamIndex.getZeroBased(), taskIndex.getZeroBased());
    }

    public void editTask(Index teamIndex, Index taskIndex, Name newName, LocalDate newDeadline) {
        teams.editTask(teamIndex.getZeroBased(), taskIndex.getZeroBased(), newName, newDeadline);
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
    public ObservableList<Team> getTeamList() {
        return teams.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && teams.equals(((AddressBook) other).teams));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
