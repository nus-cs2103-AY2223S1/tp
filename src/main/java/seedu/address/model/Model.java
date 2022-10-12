package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
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
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if the task with the same identity as {@code task} exists in
     * the address book
     * @param task The task to check identity against
     * @return true if the task exists, false otherwise
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     * @param task The task to delete.
     */
    void deleteTask(Task task);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     * @param task The task to add.
     */
    void addTask(Task task);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicates}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(List<Predicate<Person>> predicates);

    /**
     * Updates the current scope to a new container
     */
    void updateContextContainer(AbstractContainerItem container);

    boolean hasTeam(Group grp);

    void deleteTeam(Group grp);

    void addTeam(Group grp);

    void updateFilteredTeamList(Predicate<Group> predicate);

    void updateFilteredTeamList(List<Predicate<Group>> predicates);

    /**
     * Checks if context can be changed from the current context to another group.
     *
     * @param path the path to be switched to.
     * @return true if context can be changed to path, false otherwise.
     */
    boolean canChangeContext(String path);

    /**
     * Changes current context to the specified path
     *
     * @param path that will be the new current context.
     */
    void changeContext(String path);

    boolean isInSamePath(String path);

    AbstractContainerItem getCurrentContext();

    ObservableList<Group> getFilteredTeamList();
}
