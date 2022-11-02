package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Issue;
import seedu.address.model.list.UniqueEntityList;
import seedu.address.model.project.Project;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed
 *
 * This is a SINGLETON CLASS.
 * Static methods are present to retrieve, and create a new instance of the class.
 * When creating a new instance, the previous instance is destroyed.
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEntityList<Client> clients;
    private final UniqueEntityList<Project> projects;
    private final UniqueEntityList<Issue> issues;

    /**
     * Creates an empty addressbook
     */
    public AddressBook() {
        clients = new UniqueEntityList<>();
        projects = new UniqueEntityList<>();
        issues = new UniqueEntityList<>();
    }

    /**
     * Creates an AddressBook.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setList(projects);
    }

    /**
     * Replaces the contents of the issue list with {@code issues}.
     * {@code issues} must not contain duplicate issues.
     */
    public void setIssues(List<Issue> issues) {
        this.issues.setList(issues);
    }

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setList(clients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setIssues(newData.getIssueList());
        setProjects(newData.getProjectList());
        setClients(newData.getClientList());
    }

    //// client-level operations

    /**
     * Sorts client list by <code>ClientId</code>
     *
     */
    public void sortClientListById() {
        clients.sortById();
    }

    /**
     * Returns true if a project with the same identity as {@code project} exists in the project book.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.containsByName(project);
    }

    /**
     * Returns true if an issue with the same identity as {@code issue} exists in the project book.
     */
    public boolean hasIssue(Issue issue) {
        requireNonNull(issue);
        return issues.containsByName(issue);
    }

    /**
     * Returns true if a client with the same identity as {@code client} exists in the project book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.containsByName(client);
    }

    /**
     * Returns true if a project with the same identity as {@code project} exists in the project book.
     */
    public boolean hasProjectId(int id) {
        return projects.containsId(id);
    }

    /**
     * Returns true if an issue with the same identity as {@code issue} exists in the project book.
     */
    public boolean hasIssueId(int id) {
        return issues.containsId(id);
    }

    /**
     * Returns true if a client with the same identity as {@code client} exists in the project book.
     */
    public boolean hasClientId(int id) {
        return clients.containsId(id);
    }

    /**
     * Adds a project to the project book.
     * The project must not already exist in the project book.
     */
    public void addProject(Project p) {
        projects.add(p);
    }

    /**
     * Adds an issue to the project book.
     * The issue must not already exist in the project book.
     */
    public void addIssue(Issue i) {
        issues.add(i);
    }

    /**
     * Adds a client to the project book.
     * The client must not already exist in the project book.
     */
    public void addClient(Client c) {
        clients.add(c);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the project book.
     * The project identity of {@code editedProject} must not be the same as another
     * existing project in the project book.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);

        projects.setItem(target, editedProject);
    }

    /**
     * Replaces the given issue {@code target} in the list with {@code editedIssue}.
     * {@code target} must exist in the project book.
     * The issue identity of {@code editedIssue} must not be the same as another
     * existing issue in the project book.
     */
    public void setIssue(Issue target, Issue editedIssue) {
        requireNonNull(editedIssue);

        issues.setItem(target, editedIssue);
    }

    /**
     * Replaces the given issue {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the project book.
     * The issue identity of {@code editedClient} must not be the same as another
     * existing client in the project book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setItem(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the project book.
     */
    public void removeProject(Project key) {
        projects.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the project book.
     */
    public void removeIssue(Issue key) {
        issues.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the project book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    /**
     * Sort projects by id in ascending or descending order based on key value of 0 or 1 respectively.
     *
     * @param order zero for ascending order and one for descending order
     */
    public void sortProjectsById(int order) {
        ObservableList<Project> sortedProjectsById;
        if (order == 0) {
            //sort in ascending order
            sortedProjectsById =
                    getModifiableProjectList().sorted(Comparator.comparing(Project::getProjectIdInInt));
        } else {
            //sort in descending order
            sortedProjectsById = getModifiableProjectList().sorted((p1, p2) ->
                    p2.getProjectIdInInt() - p1.getProjectIdInInt());
        }
        setProjects(sortedProjectsById);
    }

    /**
     * Sort projects in chronological order or reverse chronological order based on key value of 0 or 1 respectively.
     *
     * @param order zero for chronological order and one for reverse chronological order
     */
    public void sortProjectsByDeadline(int order) {
        ObservableList<Project> sortedProjectsByDeadline;
        if (order == 0) {
            //sort according to chronological deadlines
            sortedProjectsByDeadline =
                    getModifiableProjectList().sorted(Comparator.comparing(p -> p.getDeadline().getLocalDate()));
        } else {
            //sort according to reverse chronological deadlines
            sortedProjectsByDeadline = getModifiableProjectList().sorted((p1, p2) ->
                p2.getDeadline().getLocalDate().compareTo(p1.getDeadline().getLocalDate()));
        }
        setProjects(sortedProjectsByDeadline);
    }

    /**
     * Sort projects according to incomplete issue count or completed issue count based on key of 0 or 1 respectively.
     *
     * @param order zero for sorting by incomplete issue count and one for sorting by completed issue count
     */
    public void sortProjectsByIssueCount(int order) {
        ObservableList<Project> sortedProjectsByIssueCount;
        if (order == 0) {
            //sort according to incomplete issue count
            sortedProjectsByIssueCount =
                    getModifiableProjectList().sorted((p1, p2) -> {
                        return p2.getIncompleteIssueCount() - p1.getIncompleteIssueCount();
                    });
        } else {
            //sort according to completed issue count
            sortedProjectsByIssueCount =
                    getModifiableProjectList().sorted((p1, p2) -> {
                        return p2.getCompletedIssueCount() - p1.getCompletedIssueCount();
                    });
        }
        setProjects(sortedProjectsByIssueCount);
    }

    /**
     * Sort projects in alphabetical or reverse alphabetical order of names based on key value of 0 or 1 respectively.
     *
     * @param order zero for alphabetical order and one for reverse alphabetical order
     */
    public void sortProjectsByName(int order) {
        ObservableList<Project> sortedProjectsByName;
        if (order == 0) {
            //sort according to alphabetical order
            sortedProjectsByName =
                    getModifiableProjectList().sorted(Comparator.comparing(p -> p.getProjectName().toString()));
        } else {
            //sort according to reverse alphabetical order
            sortedProjectsByName = getModifiableProjectList().sorted((p1, p2) ->
                    p2.getProjectName().toString().compareTo(p1.getProjectName().toString()));
        }
        setProjects(sortedProjectsByName);
    }

    /**
     * Sort issues by id in ascending or descending order based on key value of 0 or 1 respectively.
     *
     * @param order zero for ascending order and one for descending order
     */
    public void sortIssuesById(int order) {
        ObservableList<Issue> sortedIssuesById;
        if (order == 0) {
            //sort in ascending order
            sortedIssuesById =
                    getModifiableIssueList().sorted(Comparator.comparing(Issue::getIssueIdInInt));
        } else {
            //sort in descending order
            sortedIssuesById = getModifiableIssueList().sorted((i1, i2) ->
                    i2.getIssueIdInInt() - i1.getIssueIdInInt());
        }
        setIssues(sortedIssuesById);
    }

    /**
     * Sort issues in chronological order or reverse chronological order based on key value of 0 or 1 respectively.
     *
     * @param order zero for chronological order and one for reverse chronological order
     */
    public void sortIssuesByDeadline(int order) {
        ObservableList<Issue> sortedIssuesByDeadline;
        if (order == 0) {
            //sort according to chronological deadlines
            sortedIssuesByDeadline =
                    getModifiableIssueList().sorted(Comparator.comparing(i -> i.getDeadline().getLocalDate()));
        } else {
            //sort according to reverse chronological deadlines
            sortedIssuesByDeadline = getModifiableIssueList().sorted((i1, i2) ->
                    i2.getDeadline().getLocalDate().compareTo(i1.getDeadline().getLocalDate()));
        }
        setIssues(sortedIssuesByDeadline);
    }

    /**
     * Sort issues according to lowest or highest urgency based on key value of 0 or 1 respectively.
     *
     * @param order zero for lowest urgency and one for highest urgency
     */
    public void sortIssuesByUrgency(int order) {
        ObservableList<Issue> sortedIssuesByUrgency;
        if (order == 0) {
            //sort according to the lowest urgency
            sortedIssuesByUrgency =
                    getModifiableIssueList().sorted(Comparator.comparing(Issue::getUrgency));
        } else {
            //sort according to the highest urgency
            sortedIssuesByUrgency = getModifiableIssueList().sorted((i1, i2) ->
                    i2.getUrgency().compareTo(i1.getUrgency()));
        }
        setIssues(sortedIssuesByUrgency);
    }

    /**
     * Sort clients by id in ascending or descending order based on key value of 0 or 1 respectively.
     *
     * @param order zero for ascending order and one for descending order
     */
    public void sortClientsById(int order) {
        ObservableList<Client> sortedClientsById;
        if (order == 0) {
            //sort in ascending order
            sortedClientsById =
                    getModifiableClientList().sorted(Comparator.comparing(Client::getClientIdInInt));
        } else {
            //sort in descending order
            sortedClientsById = getModifiableClientList().sorted((c1, c2) ->
                    c2.getClientIdInInt() - c1.getClientIdInInt());
        }
        setClients(sortedClientsById);
    }

    /**
     * Sort clients in alphabetical or reverse alphabetical order of names based on key value of 0 or 1 respectively.
     *
     * @param order zero for alphabetical and one for reverse alphabetical
     */
    public void sortClientsByName(int order) {
        ObservableList<Client> sortedClientsByName;
        if (order == 0) {
            //sort according to alphabetical order
            sortedClientsByName =
                    getModifiableClientList().sorted(Comparator.comparing(c -> c.getClientName().toString()));
        } else {
            //sort according to reverse alphabetical order
            sortedClientsByName = getModifiableClientList().sorted((c1, c2) ->
                    c2.getClientName().toString().compareTo(c1.getClientName().toString()));
        }
        setClients(sortedClientsByName);
    }

    /**
     * Sorts client list with pinned clients at the front of the list.
     */
    public void sortClientsByPin() {
        ObservableList<Client> sortedClientsByPin;
        sortedClientsByPin = getModifiableClientList().sorted(Comparator.comparing(c -> !c.isPinned()));
        setClients(sortedClientsByPin);
    }

    /**
     * Sorts project list with pinned projects at the front of the list.
     */
    public void sortProjectsByPin() {
        ObservableList<Project> sortedProjectsByPin;
        sortedProjectsByPin = getModifiableProjectList().sorted(Comparator.comparing(p -> !p.isPinned()));
        setProjects(sortedProjectsByPin);
    }

    /**
     * Sorts issue list with pinned issues at the front of the list.
     */
    public void sortIssuesByPin() {
        ObservableList<Issue> sortedIssuesByPin;
        sortedIssuesByPin = getModifiableIssueList().sorted(Comparator.comparing(i -> !i.isPinned()));
        setIssues(sortedIssuesByPin);
    }

    //// util methods

    @Override
    public String toString() {
        return projects.asUnmodifiableObservableList().size() + " projects\n"
                + issues.asUnmodifiableObservableList().size() + " issues\n"
                + clients.asUnmodifiableObservableList().size() + " clients\n";
    }

    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getModifiableProjectList() {
        return projects.asModifiableObservableList();
    }

    @Override
    public ObservableList<Issue> getIssueList() {
        return issues.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Issue> getModifiableIssueList() {
        return issues.asModifiableObservableList();
    }

    @Override
    public ObservableList<Client> getModifiableClientList() {
        return clients.asModifiableObservableList();
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public Project getProjectById(int id) {
        return HasIntegerIdentifier.getElementById(projects, id);
    }

    @Override
    public Issue getIssueById(int id) {
        return HasIntegerIdentifier.getElementById(issues, id);
    }

    @Override
    public Client getClientById(int id) {
        return HasIntegerIdentifier.getElementById(clients, id);
    }

    @Override
    public Client getClient(Client client) {
        for (Client c : getModifiableClientList()) {
            if (c.hasSameName(client)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public int generateClientId() {
        return HasIntegerIdentifier.generateNextID(clients);
    }

    @Override
    public int generateIssueId() {
        return HasIntegerIdentifier.generateNextID(issues);
    }

    @Override
    public int generateProjectId() {
        return HasIntegerIdentifier.generateNextID(projects);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
    }

    @Override
    public int hashCode() {
        // TODO: Check for appropriate hashcode
        return clients.hashCode();
    }

    /**
     * Sorts all entity lists according to the default order - by id in ascending order, and by pin.
     */
    public void sortAllLists() {
        sortClientsById(0);
        sortProjectsById(0);
        sortIssuesById(0);
        sortClientsByPin();
        sortProjectsByPin();
        sortIssuesByPin();
    }

    /**
     * Sorts project list by the last known sort category and order.
     */
    public void sortProjectsByCurrentCategory() {
        switch(Project.getSortCategory()) {
        case ID:
            sortProjectsById(Project.getSortOrder());
            break;
        case NAME:
            sortProjectsByName(Project.getSortOrder());
            break;
        case DEADLINE:
            sortProjectsByDeadline(Project.getSortOrder());
            break;
        case ISSUE_COUNT:
            sortProjectsByIssueCount(Project.getSortOrder());
            break;
        default:
            assert false : "Invalid sort category for projects";
            break;
        }
    }

    /**
     * Sorts client list by the last known sort category and order.
     */
    public void sortClientsByCurrentCategory() {
        switch(Client.getSortCategory()) {
        case ID:
            sortClientsById(Client.getSortOrder());
            break;
        case NAME:
            sortClientsByName(Client.getSortOrder());
            break;
        default:
            assert false : "Invalid sort category for clients";
            break;
        }
    }

    /**
     * Sorts issue list by the last known sort category and order.
     */
    public void sortIssuesByCurrentCategory() {
        switch(Issue.getSortCategory()) {
        case ID:
            sortIssuesById(Issue.getSortOrder());
            break;
        case DEADLINE:
            sortIssuesByDeadline(Issue.getSortOrder());
            break;
        case URGENCY:
            sortIssuesByUrgency(Issue.getSortOrder());
            break;
        default:
            assert false : "Invalid sort category for issues";
            break;
        }
    }
}
