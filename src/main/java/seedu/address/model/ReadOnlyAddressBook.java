package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * Unmodifiable view of an project book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Returns a modifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getModifiableProjectList();

    /**
     * Returns an unmodifiable view of the issues list.
     * This list will not contain any duplicate issues.
     */
    ObservableList<Issue> getIssueList();

    ObservableList<Issue> getModifiableIssueList();

    ObservableList<Client> getModifiableClientList();

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Get a project object via its id
     * @param id id to retrieve
     * @return project object
     */
    Project getProjectById(int id);

    /**
     * Get a issue object via its id
     * @param id id to retrieve
     * @return issue object
     */
    Issue getIssueById(int id);

    /**
     * Get a client object via its id
     * @param id id to retrieve
     * @return client object
     */
    Client getClientById(int id);


    Client getClient(Client client);

    /**
     * Generate the next client id
     * @return id
     */
    int generateClientId();

    /**
     * Generate the next issue id
     * @return id
     */
    int generateIssueId();

    /**
     * Generate the next project id
     * @return id
     */
    int generateProjectId();
}
