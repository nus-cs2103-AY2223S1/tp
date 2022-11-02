package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.PinIssueCommand.MESSAGE_ISSUE_NOT_FOUND;
import static seedu.address.logic.commands.issue.PinIssueCommand.MESSAGE_PIN_SUCCESS;
import static seedu.address.logic.commands.issue.PinIssueCommand.MESSAGE_UNPIN_SUCCESS;
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
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

public class PinIssueCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PinIssueCommand(null));
    }

    @Test
    public void execute_pinIssue_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssueUnpinned = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(false));
        actualModel.addIssue(stubIssueUnpinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_PIN_SUCCESS, stubIssueUnpinned));

        Model expectedModel = new ModelManager();
        Issue stubIssuePinned = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(true));
        expectedModel.addIssue(stubIssuePinned);
        IssueId stubIssueId = stubIssuePinned.getIssueId();

        assertCommandSuccess(new PinIssueCommand(stubIssueId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_unpinIssue_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssuePinned = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(true));
        actualModel.addIssue(stubIssuePinned);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_UNPIN_SUCCESS, stubIssuePinned));

        Model expectedModel = new ModelManager();
        Issue stubIssueUnpinned = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(false));
        expectedModel.addIssue(stubIssueUnpinned);
        IssueId stubIssueId = stubIssueUnpinned.getIssueId();

        assertCommandSuccess(new PinIssueCommand(stubIssueId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_pinNonExistingIssue_throwsCommandException() {
        Model model = new ModelManager();
        PinIssueCommand pinIssueCommand = new PinIssueCommand(new IssueId(1));
        assertThrows(CommandException.class, MESSAGE_ISSUE_NOT_FOUND, () ->
                pinIssueCommand.execute(model, stubUi));
    }

    @Test
    public void testEquals() {
        PinIssueCommand pinIssueCommandIdOne = new PinIssueCommand(new IssueId(1));
        PinIssueCommand pinIssueCommandIdTwo = new PinIssueCommand(new IssueId(2));
        PinIssueCommand pinIssueCommandIdOneCopy = new PinIssueCommand(new IssueId(1));

        // same object -> returns true
        assertTrue(pinIssueCommandIdOne.equals(pinIssueCommandIdOne));

        // same values -> returns true
        assertTrue(pinIssueCommandIdOne.equals(pinIssueCommandIdOneCopy));

        // different types -> returns false
        assertFalse(pinIssueCommandIdOne.equals(1));

        // null -> returns false
        assertFalse(pinIssueCommandIdOne.equals(null));

        // different person -> returns false
        assertFalse(pinIssueCommandIdOne.equals(pinIssueCommandIdTwo));
    }
}
