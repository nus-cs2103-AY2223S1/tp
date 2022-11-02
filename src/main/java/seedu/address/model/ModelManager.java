package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.DefaultView;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the project book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private final FilteredList<Issue> filteredIssues;
    private final FilteredList<Client> filteredClients;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with project book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.addressBook.getProjectList());
        filteredIssues = new FilteredList<>(this.addressBook.getIssueList());
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
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

    @Override
    public DefaultView getDefaultView() {
        return userPrefs.getDefaultView();
    }

    @Override
    public void setDefaultView(DefaultView defaultView) {
        userPrefs.setDefaultView(defaultView);
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
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return addressBook.hasProject(project);
    }

    @Override
    public boolean hasIssue(Issue issue) {
        requireNonNull(issue);
        return addressBook.hasIssue(issue);
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public boolean hasProjectId(int id) {
        return addressBook.hasProjectId(id);
    }

    @Override
    public boolean hasIssueId(int id) {
        return addressBook.hasIssueId(id);
    }

    @Override
    public boolean hasClientId(int id) {
        return addressBook.hasClientId(id);
    }

    @Override
    public Project getProjectById(int id) {
        return addressBook.getProjectById(id);
    }

    @Override
    public Issue getIssueById(int id) {
        return addressBook.getIssueById(id);
    }

    @Override
    public Client getClientById(int id) {
        return addressBook.getClientById(id);
    }

    @Override
    public Client getClient(Client client) {
        return addressBook.getClient(client);
    }

    @Override
    public void deleteProject(Project target) {
        addressBook.removeProject(target);
    }

    @Override
    public void deleteIssue(Issue target) {
        addressBook.removeIssue(target);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
    }

    @Override
    public void addProject(Project project) {
        addressBook.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void addIssue(Issue issue) {
        addressBook.addIssue(issue);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        addressBook.setProject(target, editedProject);
    }

    @Override
    public void setIssue(Issue target, Issue editedIssue) {
        requireAllNonNull(target, editedIssue);

        addressBook.setIssue(target, editedIssue);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
    }

    @Override
    public int generateClientId() {
        return addressBook.generateClientId();
    }

    @Override
    public int generateIssueId() {
        return addressBook.generateIssueId();
    }

    @Override
    public int generateProjectId() {
        return addressBook.generateProjectId();
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of projects backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public ObservableList<Issue> getFilteredIssueList() {
        return filteredIssues;
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }


    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    @Override
    public void updateFilteredIssueList(Predicate<Issue> predicate) {
        requireNonNull(predicate);
        filteredIssues.setPredicate(predicate);
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
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
                && filteredProjects.equals(other.filteredProjects)
                && filteredIssues.equals(other.filteredIssues)
                && filteredClients.equals(other.filteredClients);
    }

    @Override
    public void sortProjectsByDeadline(int key) {
        addressBook.sortProjectsByDeadline(key);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void sortProjectsByIssueCount(int key) {
        addressBook.sortProjectsByIssueCount(key);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void sortProjectsByName(int key) {
        addressBook.sortProjectsByName(key);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void sortProjectsById(int key) {
        addressBook.sortProjectsById(key);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void sortIssuesById(int key) {
        addressBook.sortIssuesById(key);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void sortIssuesByDeadline(int key) {
        addressBook.sortIssuesByDeadline(key);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void sortIssuesByUrgency(int key) {
        addressBook.sortIssuesByUrgency(key);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void sortClientsById(int key) {
        addressBook.sortClientsById(key);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void sortClientsByName(int key) {
        addressBook.sortClientsByName(key);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void sortClientsByPin() {
        addressBook.sortClientsByPin();
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void sortProjectsByPin() {
        addressBook.sortProjectsByPin();
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void sortIssuesByPin() {
        addressBook.sortIssuesByPin();
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }
}
