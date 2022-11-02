package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.DefaultView;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
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
     * Returns the user prefs' project book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' project book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    DefaultView getDefaultView();

    /**
     * Replaces project book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    boolean hasProject(Project project);

    boolean hasIssue(Issue issue);
    boolean hasClient(Client client);


    /**
     * Returns true if an entity with the given id is present in the project book.
     */
    boolean hasProjectId(int id);

    boolean hasIssueId(int id);

    boolean hasClientId(int id);

    /**
     * Get a entities by id
     */
    Project getProjectById(int id);
    Issue getIssueById(int id);
    Client getClientById(int id);

    Client getClient(Client client);

    void deleteProject(Project target);

    void deleteIssue(Issue target);
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the project book.
     */
    void addProject(Project project);
    void addClient(Client client);

    void addIssue(Issue issue);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the project book.
     * The client identity of {@code editedProject} must not be the same as another existing project
     * in the project book.
     */
    void setProject(Project target, Project editedProject);

    void setIssue(Issue target, Issue editedIssue);

    void setClient(Client target, Client editedClient);


    /**
     * Generate the next entity ids
     * @return id
     */
    int generateClientId();

    int generateIssueId();

    int generateProjectId();

    ObservableList<Project> getFilteredProjectList();

    ObservableList<Issue> getFilteredIssueList();

    ObservableList<Client> getFilteredClientList();

    void updateFilteredProjectList(Predicate<Project> predicate);

    void updateFilteredIssueList(Predicate<Issue> predicate);

    void updateFilteredClientList(Predicate<Client> predicate);

    void sortProjectsById(int sortProjectsById);

    void sortProjectsByDeadline(int sortProjectsByDeadlineKey);

    void sortProjectsByIssueCount(int sortProjectsByIssueCountKey);

    void sortProjectsByName(int sortProjectsByNameKey);

    void sortIssuesById(int sortIssuesById);

    void sortIssuesByDeadline(int sortIssuesByDeadline);

    void sortIssuesByUrgency(int sortIssuesByUrgency);

    void sortClientsById(int sortClientsById);

    void sortClientsByName(int sortClientsByName);

    void setDefaultView(DefaultView defaultView);

    void sortClientsByPin();

    void sortProjectsByPin();

    void sortIssuesByPin();

    void sortProjectsByCurrentCategory();

    void sortClientsByCurrentCategory();

    void sortIssuesByCurrentCategory();
}
