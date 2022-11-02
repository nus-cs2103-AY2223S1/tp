package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
import seedu.address.model.team.UniqueTeamList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TruthTable implements ReadOnlyTruthTable {

    private final UniquePersonList persons;
    private final UniqueTeamList teams;
    private ObjectProperty<Team> currentTeam = new SimpleObjectProperty<>();

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
    }

    private TruthTable() {
    }

    /**
     * Creates an TruthTable using the Persons in the {@code toBeCopied}
     */
    public TruthTable(ReadOnlyTruthTable toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Initialises a new TruthTable with a default team.
     */
    public static TruthTable createNewTruthTable() {
        Team defaultTeam = Team.createDefaultTeam();
        TruthTable ab = new TruthTable();
        ab.addTeam(defaultTeam);
        ab.setTeam(defaultTeam);
        return ab;
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
     * Resets the existing data of this {@code TruthTable} with {@code newData}.
     */
    public void resetData(ReadOnlyTruthTable newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setTeams(newData.getTeamList());
        setTeam(newData.getTeam());
    }


    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the TruthTable.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the TruthTable.
     * The person must not already exist in the TruthTable.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the TruthTable.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the TruthTable.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code TruthTable}.
     * {@code key} must exist in the TruthTable.
     */
    public void removePerson(Person key) {
        requireNonNull(key);
        persons.remove(key);
        // need to delete the person from the teams as well. So, check all teams and delete if the person exists.
        teams.removePersonIfExists(key);
    }

    //// team-level operations

    public Team getTeam() {
        return currentTeam.getValue();
    }

    public ObjectProperty<Team> getTeamAsProperty() {
        return currentTeam;
    }

    public void setTeam(Team team) {
        currentTeam.set(team);
    }

    public void addTeam(Team teamToAdd) {
        teams.add(teamToAdd);
    }

    public void deleteTeam(Team teamToDelete) {
        teams.remove(teamToDelete);
    }

    /**
     * Replaces the contents of the team list with {@code teams}.
     * {@code teams} must not contain duplicate persons.
     */
    public void setTeams(List<Team> teams) {
        this.teams.setTeams(teams);
    }

    //// link related operations
    public boolean hasLink(Link link) {
        return getTeam().hasLink(link);
    }

    public void addLink(Link link) {
        getTeam().addLink(link);
    }

    public void setLink(Link target, Link editedLink) {
        requireNonNull(editedLink);
        getTeam().setLink(target, editedLink);
    }

    public void deleteLink(Link link) {
        getTeam().deleteLink(link);
    }

    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        getTeam().setTask(target, editedTask);
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

    public ObservableList<Link> getLinkList() {
        return getTeam().getLinkList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TruthTable // instanceof handles nulls
                && persons.equals(((TruthTable) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
