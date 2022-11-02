package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PROJECT_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.PinProjectCommand.MESSAGE_PIN_SUCCESS;
import static seedu.address.logic.commands.project.PinProjectCommand.MESSAGE_UNPIN_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

public class PinProjectCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PinProjectCommand(null));
    }

    @Test
    public void execute_pinProject_success() {
        Model actualModel = new ModelManager();
        Client stubClient = new Client(new Name("Stub"));
        Project stubProjectUnpinned = new Project(new Name("Stub"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                stubClient, new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        actualModel.addProject(stubProjectUnpinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_PIN_SUCCESS, stubProjectUnpinned));

        Model expectedModel = new ModelManager();
        Project stubProjectPinned = new Project(new Name("Stub"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                stubClient, new ArrayList<>(), new ProjectId(1),
                new Pin(true));
        expectedModel.addProject(stubProjectPinned);
        ProjectId stubProjectId = stubProjectPinned.getProjectId();

        assertCommandSuccess(new PinProjectCommand(stubProjectId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_unpinProject_success() {
        Model actualModel = new ModelManager();
        Client stubClient = new Client(new Name("Stub"));
        Project stubProject = new Project(new Name("Stub"));
        Project stubProjectPinned = new Project(new Name("Stub"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                stubClient, new ArrayList<>(), new ProjectId(1),
                new Pin(true));
        actualModel.addProject(stubProjectPinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_UNPIN_SUCCESS, stubProjectPinned));

        Model expectedModel = new ModelManager();
        Project stubProjectUnpinned = new Project(new Name("Stub"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                stubClient, new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        expectedModel.addProject(stubProjectUnpinned);
        ProjectId stubProjectId = stubProjectUnpinned.getProjectId();

        assertCommandSuccess(new PinProjectCommand(stubProjectId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_pinNonExistingProject_throwsCommandException() {
        Model model = new ModelManager();
        PinProjectCommand pinProjectCommand = new PinProjectCommand(new ProjectId(1));
        assertThrows(CommandException.class, MESSAGE_PROJECT_NOT_FOUND, () ->
                pinProjectCommand.execute(model, stubUi));
    }

    @Test
    public void testEquals() {
        PinProjectCommand pinProjectCommandIdOne = new PinProjectCommand(new ProjectId(1));
        PinProjectCommand pinProjectCommandIdTwo = new PinProjectCommand(new ProjectId(2));
        PinProjectCommand pinProjectCommandIdOneCopy = new PinProjectCommand(new ProjectId(1));

        // same object -> returns true
        assertTrue(pinProjectCommandIdOne.equals(pinProjectCommandIdOne));

        // same values -> returns true
        assertTrue(pinProjectCommandIdOne.equals(pinProjectCommandIdOneCopy));

        // different types -> returns false
        assertFalse(pinProjectCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(pinProjectCommandIdOne.equals(null));

        // different person -> returns false
        assertFalse(pinProjectCommandIdOne.equals(pinProjectCommandIdTwo));
    }
}
