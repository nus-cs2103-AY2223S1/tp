package seedu.address.logic.commands.project;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.EditProjectCommand.MESSAGE_SUCCESS;
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

class EditProjectCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_editProject_success() {
        Model actualModel = new ModelManager();
        Project beforeEditProject = new Project(new Name("Stub"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        actualModel.addProject(beforeEditProject);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, beforeEditProject));

        Model expectedModel = new ModelManager();
        Project afterEditProject = new Project(new Name("Stub"),
                new Repository("editStub/editStub"), new Deadline("2023-01-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        expectedModel.addProject(afterEditProject);
        ProjectId stubProjectId = afterEditProject.getProjectId();
        Repository stubProjectRepository = afterEditProject.getRepository();
        Deadline stubProjectDeadline = afterEditProject.getDeadline();

        assertCommandSuccess(new EditProjectCommand(stubProjectId, null, null, stubProjectRepository,
                        stubProjectDeadline), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_editNonExistingProject_throwsCommandException() {
        Model model = new ModelManager();
        String noProjectIdOneInProjectBook = "Project id 1 does not exist in the project book";
        EditProjectCommand editProjectCommand = new EditProjectCommand(new ProjectId(1), null, null, null, null);
        assertThrows(CommandException.class, noProjectIdOneInProjectBook, () ->
                editProjectCommand.execute(model, stubUi));
    }

}
