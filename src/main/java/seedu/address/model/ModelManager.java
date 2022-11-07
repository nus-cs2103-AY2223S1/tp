package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;
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

    }

    /**
     * Creates a ModelManager
     */
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

    /**
     * Returns true if addressBook has person.
     *
     * @param person Person object.
     * @return boolean
     */
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    /**
     * Deleted a person from addressBook.
     *
     * @param target Person object to be removed from addressBook.
     */
    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    /**
     * Adds a person to addressBook.
     *
     * @param person Person object to be added.
     */
    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Sets an existing person to a new person.
     *
     * @param target Person object.
     * @param editedPerson Edited Person object.
     */
    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    /**
     * Returns true is addressBook has team.
     *
     * @param team Team object.
     * @return boolean true if addressBook has team.
     */
    @Override
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return addressBook.hasTeam(team);
    }

    /**
     * Sets team name of a team.
     *
     * @param targetIndex Index of team.
     * @param newTeamTeamName New name of team
     */
    @Override
    public void setTeamName(Index targetIndex, TeamName newTeamTeamName) {
        requireAllNonNull(targetIndex, newTeamTeamName);
        addressBook.setTeamName(targetIndex, newTeamTeamName);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Deletes a team from addressBook.
     *
     * @param team Team object which is to be deleted.
     */
    @Override
    public void deleteTeam(Team team) {
        addressBook.removeTeam(team);
    }

    /**
     * Adds a team to addressBook.
     *
     * @param team Team object which is added.
     */
    @Override
    public void addTeam(Team team) {
        addressBook.addTeam(team);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Adds a person to a team.
     *
     * @param person Person object needed to be added.
     * @param team Team object.
     */
    @Override
    public void addPersonToTeam(Person person, Team team) {
        addressBook.addPersonToTeam(person, team);
    }

    /**
     * Removes a person from a team.
     *
     * @param person Person object that needs to be removed.
     * @param team Team object that needs to be removed.
     */
    @Override
    public void removePersonFromTeam(Person person, Team team) {
        addressBook.removePersonFromTeam(person, team);
    }

    /**
     * Returns true if team has a task.
     *
     * @param index Index of team.
     * @param task Index of task.
     * @return boolean if team has a task.
     */
    @Override
    public boolean teamHasTask(Index index, Task task) {
        requireAllNonNull(index, task);
        return addressBook.teamHasTask(index, task);
    }

    /**
     * Adds a task to a team.
     *
     * @param index Index of team in which task has to be added.
     * @param task Task that needs to be added.
     */
    @Override
    public void addTask(Index index, Task task) {
        requireAllNonNull(index, task);
        addressBook.addTask(index, task);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Edits the task name and deadline (optional) in a team.
     *
     * @param teamIndex Index of team whose task needs to be edited.
     * @param taskIndex Index of task that needs to be edited.
     * @param newTaskName New name of task.
     * @param newDeadline New deadline of task.
     */
    @Override
    public void editTask(Index teamIndex, Index taskIndex,
                         TaskName newTaskName, LocalDate newDeadline) {
        requireAllNonNull(teamIndex, taskIndex);
        addressBook.editTask(teamIndex, taskIndex, newTaskName, newDeadline);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Marks a task in a team.
     *
     * @param teamIndex Index of team whose task needs to be marked.
     * @param taskIndex Index of task which needs to be marked.
     */
    @Override
    public void markTask(Index teamIndex, Index taskIndex) {
        requireAllNonNull(teamIndex, taskIndex);
        addressBook.markTask(teamIndex, taskIndex);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Removes mark from a task in a team.
     *
     * @param teamIndex Index of team whose task needs to be unmarked.
     * @param taskIndex Index of task which needs to be unmarked.
     */
    @Override
    public void unmarkTask(Index teamIndex, Index taskIndex) {
        requireAllNonNull(teamIndex, taskIndex);
        addressBook.unmarkTask(teamIndex, taskIndex);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    /**
     * Deletes a task from a team.
     *
     * @param teamIndex Index of team whose task needs to be deleted.
     * @param taskIndex Index of task that needs to be deleted.
     */
    @Override
    public void deleteTask(Index teamIndex, Index taskIndex) {
        requireAllNonNull(teamIndex, taskIndex);
        addressBook.deleteTask(teamIndex, taskIndex);
        updateFilteredTeamList(unused -> false);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
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

    /**
     * Returns person object which has given name.
     *
     * @param name Name of person whose person object is needed.
     * @return Person object.
     */
    @Override
    public Person getPerson(PersonName name) {
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

    /**
     * Returns person name from given person index.
     *
     * @param personIndex Index of person whose name is needed.
     * @return Name of person.
     */
    @Override
    public PersonName getPersonName(Index personIndex) {
        requireNonNull(personIndex);
        Person person = getPersonUsingIndex(personIndex);
        return person.getName();
    }

    /**
     * Returns person object which has given index.
     *
     * @param personIndex Index of person .
     * @return Person object.
     */
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

    /**
     * Returns the team object which has the given team name.
     *
     * @param teamName Name of team.
     * @return Team object.
     */
    @Override
    public Team getTeam(TeamName teamName) {
        List<Team> teams = getFilteredTeamList();
        requireNonNull(teamName);
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new TeamNotFoundException();
    }

    /**
     * Returns true if the given team name exists.
     *
     * @param teamName Name of team.
     * @return boolean true if team name exists
     */
    @Override
    public boolean teamNameExists(TeamName teamName) {
        List<Team> teams = getFilteredTeamList();
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            TeamName teamname = team.getName();
            if (teamname.equals(teamName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns team name from given team index.
     *
     * @param teamIndex Index of team.
     * @return Name of team.
     */
    @Override
    public TeamName getTeamName(Index teamIndex) {
        requireNonNull(teamIndex);
        Team team = getTeamUsingIndex(teamIndex);
        return team.getName();
    }

    /**
     * Returns team object which has given index.
     *
     * @param teamIndex Index of team whose team object is needed.
     * @return Team object.
     */
    @Override
    public Team getTeamUsingIndex(Index teamIndex) {
        List<Team> teams = getFilteredTeamList();
        requireNonNull(teamIndex);
        return teams.get(teamIndex.getZeroBased());
    }

    /**
     * Return list of all teams in which the given person is a member in.
     *
     * @param p Person object.
     * @return List of teams.
     */
    @Override
    public List<Team> teamsWithMember(Person p) {
        List<Team> teams = getFilteredTeamList();
        requireNonNull(p);
        List<Team> teamsWithMember = new ArrayList<>();
        ObservableList<Person> memberList = null;
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            memberList = team.getMemberList();
            for (int j = 0; j < memberList.size(); j++) {
                if (memberList.contains(p)) {
                    teamsWithMember.add(team);
                }
            }
        }
        return teamsWithMember;
    }

    /**
     * Returns true if person is a member in the given team.
     *
     * @param p Index of person.
     * @param t Index of team.
     * @return True if team has member.
     */
    public boolean teamHasMember(Index p, Index t) {
        List<Team> teams = getFilteredTeamList();
        List<Person> persons = getFilteredPersonList();
        requireNonNull(p);
        ObservableList<Person> memberList = null;
        Team team = teams.get(t.getZeroBased());
        Person person = persons.get(p.getZeroBased());
        memberList = team.getMemberList();
        for (int j = 0; j < memberList.size(); j++) {
            if (memberList.contains(person)) {
                return true;
            }
        }
        return false;
    }

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
