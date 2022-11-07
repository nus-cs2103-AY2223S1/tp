package seedu.address.logic.commands.issue;

import static seedu.address.commons.core.Messages.MESSAGE_ISSUE_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.UnmarkIssueCommand.MESSAGE_SUCCESS;
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

public class UnmarkIssueCommandTest {
    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullInputs_throwsNullPointerException() {

        // null Status
        assertThrows(NullPointerException.class, () -> new UnmarkIssueCommand(null, new IssueId(1)));

        // null IssueId
        assertThrows(NullPointerException.class, () -> new UnmarkIssueCommand(new Status(false), null));

        // null IssueId and Status
        assertThrows(NullPointerException.class, () -> new UnmarkIssueCommand(null, null));

    }

    @Test
    public void execute_validInt_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssueMarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(true), stubProject,
                new IssueId(1), new Pin(false));
        actualModel.addIssue(stubIssueMarked);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, stubIssueMarked));

        Model expectedModel = new ModelManager();
        Issue stubIssueUnmarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(false));
        expectedModel.addIssue(stubIssueUnmarked);
        IssueId stubIssueId = stubIssueUnmarked.getIssueId();

        Status toMark = new Status(false);

        assertCommandSuccess(new UnmarkIssueCommand(toMark, stubIssueId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_noSuchIssue_failure() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssueMarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(true), stubProject,
                new IssueId(1), new Pin(false));
        actualModel.addIssue(stubIssueMarked);

        Status toMark = new Status(true);

        UnmarkIssueCommand unmarkIssueCommandIdTwo = new UnmarkIssueCommand(toMark, new IssueId(2));

        assertThrows(CommandException.class, MESSAGE_ISSUE_NOT_FOUND, (
        ) -> unmarkIssueCommandIdTwo.execute(actualModel, stubUi));
    }
}
