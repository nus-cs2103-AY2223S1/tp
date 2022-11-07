package seedu.address.logic.commands.client;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.client.ClientWithoutModel;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOBILE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_CLIENT_ALREADY_PRESENT;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_DUPLICATE_CLIENT;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_PROJECT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;

public class AddClientCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullClientWithoutModel_throwsNullPointerException() {
        Project stubProject = new Project(new Name("Stub"));

        assertThrows(NullPointerException.class, () ->
                new AddClientCommand(null, stubProject.getProjectId()));
    }

    @Test
    public void execute_validArgs_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        actualModel.addProject(stubProject);
        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(),
                new Pin(false));

        Model expectedModel = new ModelManager();
        expectedModel.addProject(stubProject);
        ArrayList<Project> projectList = new ArrayList<>();
        projectList.add(stubProject);
        Client addedClient = new Client(new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                new ClientEmail(VALID_EMAIL_AMY), projectList, new ClientId(1), new Pin(false));
        expectedModel.addClient(addedClient);

        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                addedClient));

        assertCommandSuccess(new AddClientCommand(clientWithoutModel, stubProject.getProjectId()),
                actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void execute_nonExistentProjectId_throwsCommandException() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        actualModel.addProject(stubProject);
        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(),
                new Pin(false));

        AddClientCommand addClientCommand = new AddClientCommand(clientWithoutModel, new ProjectId(1000));

        // non-existent projectId
        assertThrows(CommandException.class, MESSAGE_PROJECT_NOT_FOUND,
                () -> addClientCommand.execute(actualModel, stubUi));

    }

    @Test
    public void execute_projectAlreadyHasClient_throwsCommandException() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"), new Repository("user/stub"), new Deadline("2020-12-12"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(1), new Pin(false));
        actualModel.addProject(stubProject);

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(),
                new Pin(false));

        AddClientCommand addClientCommand = new AddClientCommand(clientWithoutModel, stubProject.getProjectId());

        assertThrows(CommandException.class, MESSAGE_CLIENT_ALREADY_PRESENT,
                () -> addClientCommand.execute(actualModel, stubUi));

    }

    @Test
    public void execute_clientExists_throwsCommandException() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        actualModel.addProject(stubProject);

        ArrayList<Project> projectList = new ArrayList<>();
        projectList.add(stubProject);
        Client client = new Client(new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                new ClientEmail(VALID_EMAIL_AMY), projectList, new ClientId(1), new Pin(false));
        actualModel.addClient(client);

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(),
                new Pin(false));

        AddClientCommand addClientCommand = new AddClientCommand(clientWithoutModel, stubProject.getProjectId());

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_CLIENT,
                () -> addClientCommand.execute(actualModel, stubUi));
    }

    @Test
    public void testEquals() {

        Project stubProject = new Project(new Name("Stub"));
        ArrayList<ProjectId> projectList = new ArrayList<>();
        projectList.add(stubProject.getProjectId());

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(),
                new Pin(false));
        ClientWithoutModel clientWithoutModelWithProject = new ClientWithoutModel(new Name(VALID_NAME_AMY),
                new ClientMobile(VALID_MOBILE_AMY), new ClientEmail(VALID_EMAIL_AMY), projectList,
                new Pin(false));

        AddClientCommand addClientCommandIdOne = new AddClientCommand(clientWithoutModel, new ProjectId(1));
        AddClientCommand addClientCommandIdOneCopy = new AddClientCommand(clientWithoutModel, new ProjectId(1));
        AddClientCommand addClientCommandIdTwo = new AddClientCommand(clientWithoutModelWithProject, new ProjectId(2));

        // same object -> returns true
        assertTrue(addClientCommandIdOne.equals(addClientCommandIdOne));

        // same values -> returns true
        assertTrue(addClientCommandIdOne.equals(addClientCommandIdOneCopy));

        // different types -> returns false
        assertFalse(addClientCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(addClientCommandIdOne.equals(null));

        // different command -> returns false
        assertFalse(addClientCommandIdOne.equals(addClientCommandIdTwo));
    }

}
