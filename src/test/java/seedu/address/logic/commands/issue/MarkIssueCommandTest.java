package seedu.address.logic.commands.issue;

import static seedu.address.commons.core.Messages.MESSAGE_ISSUE_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.MarkIssueCommand.MESSAGE_SUCCESS;
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

public class MarkIssueCommandTest {

    private Ui stubUi = new StubUiManager();

    @Test
    public void constructor_nullInputs_throwsNullPointerException() {

        // null Status
        assertThrows(NullPointerException.class, () -> new MarkIssueCommand(null, new IssueId(1)));

        // null IssueId
        assertThrows(NullPointerException.class, () -> new MarkIssueCommand(new Status(false), null));

        // null IssueId and Status
        assertThrows(NullPointerException.class, () -> new MarkIssueCommand(null, null));

    }

    @Test
    public void execute_validInt_success() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssueUnmarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(false));
        actualModel.addIssue(stubIssueUnmarked);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, stubIssueUnmarked));

        Model expectedModel = new ModelManager();
        Issue stubIssueMarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(true), stubProject,
                new IssueId(1), new Pin(false));
        expectedModel.addIssue(stubIssueMarked);
        IssueId stubIssueId = stubIssueMarked.getIssueId();

        Status toMark = new Status(true);

        assertCommandSuccess(new MarkIssueCommand(toMark, stubIssueId), actualModel,
                expectedCommandResult, expectedModel, stubUi);
    }

    @Test
    public void execute_noSuchIssue_failure() {
        Model actualModel = new ModelManager();
        Project stubProject = new Project(new Name("Stub"));
        Issue stubIssueUnmarked = new Issue(new Title("Test Issue"), new Deadline("2020-02-02"),
                Urgency.LOW, new Status(false), stubProject,
                new IssueId(1), new Pin(false));
        actualModel.addIssue(stubIssueUnmarked);

        Status toMark = new Status(true);

        MarkIssueCommand markIssueCommandIdTwo = new MarkIssueCommand(toMark, new IssueId(2));

        assertThrows(CommandException.class, MESSAGE_ISSUE_NOT_FOUND, (
            ) -> markIssueCommandIdTwo.execute(actualModel, stubUi));
    }

}
