package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Team> PREDICATE_SHOW_ALL_TEAMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Checks whether the specified team is present.
     *
     * @param team The team to be checked whether it is present.
     * @return boolean if the team is present.
     */
    public boolean hasTeam(Team team);

    /**
     * Deletes the specified team.
     *
     * @param team The team to be deleted
     */
    public void deleteTeam(Team team);

    /**
     * Adds the specified team.
     *
     * @param team The team to be added.
     */
    public void addTeam(Team team);

    /**
     * Updates the team name to the specifiedd new team name.
     *
     * @param targetIndex The index of the team to be updated.
     * @param newTeamTeamName The new name of the team.
     */
    public void setTeamName(Index targetIndex, TeamName newTeamTeamName);

    /**
     * Adds the specified person to the team.
     *
     * @param person The person to be added to the team.
     * @param team The team to which the person to be added.
     */
    public void addPersonToTeam(Person person, Team team);

    /**
     * Removes the specified person from the team.
     *
     * @param person The person to be removed from the team.
     * @param team The team from which the person has to be removed.
     */
    public void removePersonFromTeam(Person person, Team team);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Gets the person object of the given person name.
     *
     * @param name The name of the person.
     * @return The person object of the specified person name.
     */
    public Person getPerson(PersonName name);

    /**
     * Gets the Person Name of the person in the specified index.
     *
     * @param personIndex The index of the person.
     * @return The PersonName of the specified person index.
     */
    public PersonName getPersonName(Index personIndex);

    /**
     * Gets the Person with the specified person index.
     *
     * @param personIndex The index of the person.
     * @return The person object.
     */
    public Person getPersonUsingIndex(Index personIndex);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered team list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTeamList(Predicate<Team> predicate);

    /** Returns an unmodifiable view of the filtered team list */
    ObservableList<Team> getFilteredTeamList();

    /**
     * Get the team object of the specified team name.
     *
     * @param teamName The team name of the object to be retrieved.
     * @return The team of the specified team name.
     */
    public Team getTeam(TeamName teamName);

    /**
     * Checks whether the specified team name is present.
     *
     * @param teamName The team name to be checked whether it exists.
     * @return boolean if the specified team name is present.
     */
    public boolean teamNameExists(TeamName teamName);

    /**
     * Gets the TeamName in the specified index.
     *
     * @param teamIndex The index of the team to be retrieved.
     * @return The team name in the specified index.
     */
    public TeamName getTeamName(Index teamIndex);

    /**
     * Gets the Team in the specified index.
     *
     * @param teamIndex The index of the team to be retrieved.
     * @return The team in the specified index.
     */
    public Team getTeamUsingIndex(Index teamIndex);

    /**
     * Gives the list of team with the members.
     *
     * @param p The person to be checked in the team.
     * @return A list of teams where the person is present.
     */
    public List<Team> teamsWithMember(Person p);

    /**
     * Checks whether the team has the specified member.
     *
     * @param p The index of the team of the corresponding member.
     * @param t The index of the corresponding member.
     * @return boolean whether the member is present in the team.
     */
    public boolean teamHasMember(Index p, Index t);

    /**
     * Checks whether the team has the specified task.
     *
     * @param index The index of the team of the corresponding task.
     * @param task The task to be checked.
     * @return boolean whether the task is present in the team.
     */
    boolean teamHasTask(Index index, Task task);

    /**
     * Adds task to the specified team.
     *
     * @param index The index of the team where the task to be added.
     * @param task The task to be added.
     */
    void addTask(Index index, Task task);

    /**
     * Marks the task as done.
     *
     * @param teamIndex The index of the team corresponding to the task to be marked.
     * @param taskIndex The index of the task to be marked.
     */
    void markTask(Index teamIndex, Index taskIndex);

    /**
     * Marks the task as not done.
     *
     * @param teamIndex The index of the team corresponding to the task to be unmarked.
     * @param taskIndex The index of the task to be unmarked.
     */
    void unmarkTask(Index teamIndex, Index taskIndex);

    /**
     * Deletes the given task.
     *
     * @param teamIndex The index of the team corresponding to the task.
     * @param taskIndex The index of the task to be deleted.
     */
    void deleteTask(Index teamIndex, Index taskIndex);

    /**
     * Edits the task with the given task name and deadline.
     *
     * @param teamIndex The team index of the specified task.
     * @param taskIndex The index of the task to be updated.
     * @param newTaskName The new task name.
     * @param newDeadline the new deadline.
     */
    void editTask(Index teamIndex, Index taskIndex, TaskName newTaskName, LocalDate newDeadline);

}
