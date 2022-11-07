package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.Command.ID_OVERFLOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.AddIssueCommand.MESSAGE_PROJECT_NOT_FOUND;
import static seedu.address.logic.commands.issue.AddIssueCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.IssueWithoutModel;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

public class AddIssueCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullIssueWithoutModel_throwsNullPointerException() {
        Project stubProject = new Project(new Name("Stub"));

        assertThrows(NullPointerException.class, () -> new AddIssueCommand(null,
                stubProject.getProjectId()));
    }

    @Test
    public void execute_validArgs_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        actualModel.addProject(stubProject);
        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(new Title(VALID_TITLE),
                new Deadline(VALID_DEADLINE), Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
                stubProject.getProjectId(), new Pin(false));

        Model expectedModel = new ModelManager();
        expectedModel.addProject(stubProject);
        Issue addedIssue = new Issue(new Title(VALID_TITLE), new Deadline(VALID_DEADLINE),
                Urgency.NONE, new Status(false), stubProject, new IssueId(1),
                new Pin(false));
        expectedModel.addIssue(addedIssue);

        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                addedIssue));

        assertCommandSuccess(new AddIssueCommand(issueWithoutModel, stubProject.getProjectId()),
                actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void execute_invalidProjectId_throwsCommandException() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        actualModel.addProject(stubProject);
        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(new Title(VALID_TITLE),
                new Deadline(VALID_DEADLINE), Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
                new ProjectId(0), new Pin(false));

        AddIssueCommand invalidAddIssueCommand = new AddIssueCommand(issueWithoutModel, new ProjectId(0));
        AddIssueCommand nonExistentAddIssueCommand = new AddIssueCommand(issueWithoutModel, new ProjectId(1000));

        // invalid projectId (0)
        assertThrows(CommandException.class, ID_OVERFLOW, () -> invalidAddIssueCommand.execute(actualModel, stubUi));

        // non-existent projectId
        assertThrows(CommandException.class, MESSAGE_PROJECT_NOT_FOUND, (
            ) -> nonExistentAddIssueCommand.execute(actualModel, stubUi));

    }

    @Test
    public void testEquals() {
        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(new Title(VALID_TITLE),
                new Deadline(VALID_DEADLINE), Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
                new ProjectId(1), new Pin(false));
        IssueWithoutModel issueWithoutModelIdTwo = new IssueWithoutModel(new Title(VALID_TITLE),
                new Deadline(VALID_DEADLINE), Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
                new ProjectId(2), new Pin(false));

        AddIssueCommand addIssueCommandIdOne = new AddIssueCommand(issueWithoutModel, new ProjectId(1));
        AddIssueCommand addIssueCommandIdOneCopy = new AddIssueCommand(issueWithoutModel, new ProjectId(1));
        AddIssueCommand addIssueCommandIdTwo = new AddIssueCommand(issueWithoutModelIdTwo, new ProjectId(2));

        // same object -> returns true
        assertTrue(addIssueCommandIdOne.equals(addIssueCommandIdOne));

        // same values -> returns true
        assertTrue(addIssueCommandIdOne.equals(addIssueCommandIdOneCopy));

        // different types -> returns false
        assertFalse(addIssueCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(addIssueCommandIdOne.equals(null));

        // different command -> returns false
        assertFalse(addIssueCommandIdOne.equals(addIssueCommandIdTwo));

    }






}
