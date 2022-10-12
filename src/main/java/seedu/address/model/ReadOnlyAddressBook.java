package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.Person;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Returns an unmodifiable view of the issues list.
     * This list will not contain any duplicate issues.
     */
    ObservableList<Issue> getIssueList();

<<<<<<< HEAD
    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();
=======
    ObservableList<Client> getClientList();

>>>>>>> e759cb303b22f8efb0b2be99e1ae20fec47ea9a2

    /**
     * Returns the number of projects in project list.
     */
    String getProjectCount();

    /**
     * Returns the number of issues in issue list.
     */
    String getIssueCount();

    /**
     * Returns the number of clients in client list.
     */
    String getClientCount();
}
