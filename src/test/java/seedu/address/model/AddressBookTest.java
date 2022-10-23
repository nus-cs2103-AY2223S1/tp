package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getProjectList());
        assertEquals(Collections.emptyList(), addressBook.getClientList());
        assertEquals(Collections.emptyList(), addressBook.getIssueList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose lists can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Issue> issues = FXCollections.observableArrayList();
        private final ObservableList<Client> clients = FXCollections.observableArrayList();


        AddressBookStub(Collection<Project> projects, Collection<Issue> issues, Collection<Client> clients) {
            this.projects.setAll(projects);
            this.issues.setAll(issues);
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Project> getModifiableProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Issue> getIssueList() {
            return issues;
        }

        @Override
        public ObservableList<Issue> getModifiableIssueList() {
            return issues;
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public Project getProjectById(int id) {
            return null;
        }

        @Override
        public Issue getIssueById(int id) {
            return null;
        }

        @Override
        public Client getClientById(int id) {
            return null;
        }

        @Override
        public int generateClientId() {
            return 0;
        }

        @Override
        public int generateIssueId() {
            return 0;
        }

        @Override
        public int generateProjectId() {
            return 0;
        }
    }

}
