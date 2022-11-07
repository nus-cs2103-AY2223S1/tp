package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DefaultView;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2,
                3, 4, DefaultView.CLIENT));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2,
                3, 4, DefaultView.PROJECT);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasProject_nullIssue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIssue(null));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProjectList().remove(0));
    }

    @Test
    public void getFilteredIssueList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredIssueList().remove(0));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void updateFilteredProjectList_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredProjectList(null));
    }

    @Test
    public void updateFilteredIssueList_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredIssueList(null));
    }

    @Test
    public void updateFilteredClientList_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredClientList(null));
    }

    @Test
    public void addProject_validProject_success() {
        Project stubProject = new Project(new Name("stub"));
        modelManager.addProject(stubProject);
        assertTrue(modelManager.hasProject(stubProject));
    }

    @Test
    public void addIssue_validIssue_success() {
        Issue stubIssue = new Issue(new Title("stub"), new Project(new Name("stub")));
        modelManager.addIssue(stubIssue);
        assertTrue(modelManager.hasIssue(stubIssue));
    }

    @Test
    public void addClient_validClient_success() {
        Client stubClient = new Client(new Name("stub"));
        modelManager.addClient(stubClient);
        assertTrue(modelManager.hasClient(stubClient));
    }

    @Test
    public void deleteProject_validProject_success() {
        Project stubProject = new Project(new Name("stub"));
        modelManager.addProject(stubProject);
        assertTrue(modelManager.hasProject(stubProject));
        modelManager.deleteProject(stubProject);
        assertFalse(modelManager.hasProject(stubProject));
    }

    @Test
    public void deleteIssue_validIssue_success() {
        Issue stubIssue = new Issue(new Title("stub"), new Project(new Name("stub")));
        modelManager.addIssue(stubIssue);
        assertTrue(modelManager.hasIssue(stubIssue));
        modelManager.deleteIssue(stubIssue);
        assertFalse(modelManager.hasIssue(stubIssue));
    }

    @Test
    public void deleteClient_validClient_success() {
        Client stubClient = new Client(new Name("stub"));
        modelManager.addClient(stubClient);
        assertTrue(modelManager.hasClient(stubClient));
        modelManager.deleteClient(stubClient);
        assertFalse(modelManager.hasClient(stubClient));
    }

    @Test
    public void setProject_validProject_success() {
        Project stubProject1 = new Project(new Name("stub1"));
        Project stubProject2 = new Project(new Name("stub2"));
        modelManager.addProject(stubProject1);
        assertTrue(modelManager.hasProject(stubProject1));
        modelManager.setProject(stubProject1, stubProject2);
        assertFalse(modelManager.hasProject(stubProject1));
        assertTrue(modelManager.hasProject(stubProject2));
    }

    @Test
    public void setIssue_validIssue_success() {
        Issue stubIssue1 = new Issue(new Title("stub1"), new Project(new Name("stub1")));
        Issue stubIssue2 = new Issue(new Title("stub2"), new Project(new Name("stub2")));
        modelManager.addIssue(stubIssue1);
        assertTrue(modelManager.hasIssue(stubIssue1));
        modelManager.setIssue(stubIssue1, stubIssue2);
        assertFalse(modelManager.hasIssue(stubIssue1));
        assertTrue(modelManager.hasIssue(stubIssue2));
    }

    @Test
    public void setClient_validClient_success() {
        Client stubClient1 = new Client(new Name("stub1"));
        Client stubClient2 = new Client(new Name("stub2"));
        modelManager.addClient(stubClient1);
        assertTrue(modelManager.hasClient(stubClient1));
        modelManager.setClient(stubClient1, stubClient2);
        assertFalse(modelManager.hasClient(stubClient1));
        assertTrue(modelManager.hasClient(stubClient2));
    }

    @Test
    public void getClient_validClient_success() {
        Client stubClient = new Client(new Name("stub1"));
        modelManager.addClient(stubClient);
        assertEquals(stubClient, modelManager.getClient(stubClient));
    }

    @Test
    public void getProjectById_validProject_success() {
        Project stubProject = new Project(new Name("stub"), new Repository("stub/stub"), new Deadline("2022-02-02"),
                new Client(new Name("stub")), new ArrayList<>(), new ProjectId(1), new Pin(false));
        modelManager.addProject(stubProject);
        assertEquals(stubProject, modelManager.getProjectById(1));
    }

    @Test
    public void getIssueById_validIssue_success() {
        Issue stubIssue = new Issue(new Title("stub"), new Deadline("2022-02-02"), Urgency.MEDIUM, new Status(false),
                new Project(new Name("stub")), new IssueId(1), new Pin(false));
        modelManager.addIssue(stubIssue);
        assertEquals(stubIssue, modelManager.getIssueById(1));
    }

    @Test
    public void getClientById_validClient_success() {
        Client stubClient = new Client(new Name("stub"), new ClientMobile("12345678"), new ClientEmail("stub@stub"),
                new ArrayList<>(), new ClientId(1), new Pin(false));
        modelManager.addClient(stubClient);
        assertEquals(stubClient, modelManager.getClientById(1));
    }

    @Test
    public void equals_valid_success() {
        assertEquals(modelManager, modelManager);
    }

    @Test
    public void generateProjectId_firstGeneration_success() {
        ModelManager testModelManager = new ModelManager();
        int first = testModelManager.generateProjectId();
        assertEquals(first, 1);
    }

    @Test
    public void generateIssueId_firstGeneration_success() {
        ModelManager testModelManager = new ModelManager();
        int first = testModelManager.generateIssueId();
        assertEquals(first, 1);
    }

    @Test
    public void generateClientId_firstGeneration_success() {
        ModelManager testModelManager = new ModelManager();
        int first = testModelManager.generateClientId();
        assertEquals(first, 1);
    }

}
