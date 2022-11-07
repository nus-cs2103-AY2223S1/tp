package seedu.address.logic.commands.project;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.ProjectWithoutModel;
import seedu.address.model.project.Repository;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOSITORY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.AddProjectCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.project.AddProjectCommand.MESSAGE_DUPLICATE_PROJECT;
import static seedu.address.testutil.Assert.assertThrows;

public class AddProjectCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullProjectWithoutModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProjectCommand(null));
    }

    @Test
    public void execute_validArgs_success() {
        Model actualModel = new ModelManager();

        ProjectWithoutModel projectWithoutModel = new ProjectWithoutModel(new Name("PROJECT"),
                new Repository(VALID_REPOSITORY), new Deadline(VALID_DEADLINE), ClientId.EmptyClientId.EMPTY_CLIENT_ID, new ArrayList<>(),
                new Pin(false));

        Model expectedModel = new ModelManager();
        Project addedProject = new Project(new Name("PROJECT"), new Repository(VALID_REPOSITORY),
                new Deadline(VALID_DEADLINE), Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(),
                new ProjectId(1), new Pin(false));
        expectedModel.addProject(addedProject);

        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                addedProject));

        assertCommandSuccess(new AddProjectCommand(projectWithoutModel),
                actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void execute_projectExists_throwsCommandException() {
        Model actualModel = new ModelManager();

        Project project = new Project(new Name("PROJECT"), new Repository(VALID_REPOSITORY),
                new Deadline(VALID_DEADLINE), new Client(new Name(VALID_NAME_AMY)), new ArrayList<>(),
                new ProjectId(1), new Pin(false));
        actualModel.addProject(project);

        ProjectWithoutModel projectWithoutModel = new ProjectWithoutModel(new Name("PROJECT"),
                new Repository(VALID_REPOSITORY), new Deadline(VALID_DEADLINE), new ClientId(1), new ArrayList<>(),
                new Pin(false));

        AddProjectCommand addProjectCommand = new AddProjectCommand(projectWithoutModel);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_PROJECT,
                () -> addProjectCommand.execute(actualModel, stubUi));
    }

    @Test
    public void testEquals() {
        ProjectWithoutModel projectWithoutModel = new ProjectWithoutModel(new Name("PROJECT"),
                new Repository(VALID_REPOSITORY), new Deadline(VALID_DEADLINE), new ClientId(1), new ArrayList<>(),
                new Pin(false));
        ProjectWithoutModel projectWithoutModelIdTwo = new ProjectWithoutModel(new Name("PROJECT"),
                new Repository(VALID_REPOSITORY), new Deadline(VALID_DEADLINE), new ClientId(2), new ArrayList<>(),
                new Pin(false));

        AddProjectCommand addProjectCommandIdOne = new AddProjectCommand(projectWithoutModel);
        AddProjectCommand addProjectCommandIdOneCopy = new AddProjectCommand(projectWithoutModel);
        AddProjectCommand addProjectCommandIdTwo = new AddProjectCommand(projectWithoutModelIdTwo);

        // same object -> returns true
        assertTrue(addProjectCommandIdOne.equals(addProjectCommandIdOne));

        // same value -> returns true
        assertTrue(addProjectCommandIdOne.equals(addProjectCommandIdOneCopy));

        // different types -> returns false
        assertFalse(addProjectCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(addProjectCommandIdOne.equals(null));

        // different command -> returns false
        assertFalse(addProjectCommandIdOne.equals(addProjectCommandIdTwo));

    }



}
