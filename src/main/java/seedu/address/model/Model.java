package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.client.Person;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;
    Predicate<Issue> PREDICATE_SHOW_ALL_ISSUES = unused -> true;

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
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasPerson(Person person);

    boolean hasProject(Project project);

    boolean hasIssue(Issue issue);
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deletePerson(Person target);

    void deleteProject(Project target);

    void deleteIssue(Issue target);
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addProject(Project person);

    void addPerson(Person person);
    void addClient(Client client);

    void addIssue(Issue issue);

    /**
     * Replaces the given client {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedPerson} must not be the same as another existing client in the address book.
     */
    void setProject(Project target, Project editedProject);

    void setPerson(Person target, Person editedPerson);

    void setIssue(Issue target, Issue editedIssue);

    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Project> getFilteredProjectList();

    ObservableList<Issue> getFilteredIssueList();

    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredProjectList(Predicate<Project> predicate);

    void updateFilteredIssueList(Predicate<Issue> predicate);

    void updateFilteredClientList(Predicate<Client> predicate);


}
