package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;
import seedu.address.model.team.exceptions.TeamNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Team> filteredTeams;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTeams = new FilteredList<>(this.addressBook.getTeamList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return addressBook.hasTeam(team);
    }

    @Override
    public void deleteTeam(Team team) {
        addressBook.removeTeam(team);
    }

    @Override
    public void addTeam(Team team) {
        addressBook.addTeam(team);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    @Override
    public void addPersonToTeam(Person person, Team team) {
        addressBook.addPersonToTeam(person, team);
    }

    @Override
    public void removePersonFromTeam(Person person, Team team) {
        addressBook.removePersonFromTeam(person, team);
    }

    @Override
    public void addTask(Index index, Task task) {
        requireAllNonNull(index, task);
        addressBook.addTask(index, task);
    }

    @Override
    public void markTask(Index index, Task task) {
        requireAllNonNull(index, task);
        addressBook.markTask(index, task);
    }

    @Override
    public void unmarkTask(Index index, Task task) {
        requireAllNonNull(index, task);
        addressBook.unmarkTask(index, task);
    }

    @Override
    public void deleteTask(Index teamIndex, Index taskIndex) {
        requireAllNonNull(teamIndex, taskIndex);
        addressBook.deleteTask(teamIndex, taskIndex);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTeamList(Predicate<Team> predicate) {
        requireNonNull(predicate);
        filteredTeams.setPredicate(predicate);
    }

    @Override
    public Person getPerson(seedu.address.model.person.Name name) {
        List<Person> persons = getFilteredPersonList();
        requireNonNull(name);
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getName().equals(name)) {
                return person;
            }
        }
        throw new PersonNotFoundException();
    }

    @Override
    public seedu.address.model.person.Name getPersonName(Index personIndex) {
        requireNonNull(personIndex);
        Person person = getPersonUsingIndex(personIndex);
        return person.getName();
    }

    @Override
    public Person getPersonUsingIndex(Index personIndex) {
        List<Person> persons = getFilteredPersonList();
        requireNonNull(personIndex);
        return persons.get(personIndex.getZeroBased());
    }

    //=========== Filtered Team List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Team} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Team> getFilteredTeamList() {
        return filteredTeams;
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    public Team getTeam(seedu.address.model.team.Name name) {
        List<Team> teams = getFilteredTeamList();
        requireNonNull(name);
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getName().equals(name)) {
                return team;
            }
        }
        throw new TeamNotFoundException();
    }

    public Name getTeamName(Index teamIndex) {
        requireNonNull(teamIndex);
        Team team = getTeamUsingIndex(teamIndex);
        return team.getName();
    }

    @Override
    public Team getTeamUsingIndex(Index teamIndex) {
        List<Team> teams = getFilteredTeamList();
        requireNonNull(teamIndex);
        return teams.get(teamIndex.getZeroBased());
    }

    // todo implementation of updateFilteredTeamList

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTeams.equals(other.filteredTeams);
    }

}
