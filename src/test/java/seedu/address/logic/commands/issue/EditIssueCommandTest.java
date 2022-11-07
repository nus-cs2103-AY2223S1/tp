package seedu.address.logic.commands.issue;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.EditIssueCommand.MESSAGE_SUCCESS;
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

class EditIssueCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_editIssue_success() {
        Model actualModel = new ModelManager();
        Issue beforeEditIssue = new Issue(new Title("Fix some bugs"),
                new Deadline("2022-02-02"), Urgency.MEDIUM,
                new Status(false), new Project(new Name("Stub")), new IssueId(1),
                new Pin(false));
        actualModel.addIssue(beforeEditIssue);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, beforeEditIssue));

        Model expectedModel = new ModelManager();
        Issue afterEditIssue = new Issue(new Title("Fix some bugs"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(1),
                new Pin(false));
        expectedModel.addIssue(afterEditIssue);

        IssueId stubIssueId = afterEditIssue.getIssueId();
        Urgency stubUrgency = afterEditIssue.getUrgency();
        Deadline stubDeadline = afterEditIssue.getDeadline();

        assertCommandSuccess(new EditIssueCommand(null, stubDeadline, stubUrgency, stubIssueId), actualModel,
                expectedCommandResult, actualModel, stubUi);
    }

    @Test
    public void execute_editNonExistingIssue_throwsCommandException() {
        Model model = new ModelManager();
        String noIssueIdOneInProjectBook = "Issue id 1 does not exist in the project book";
        EditIssueCommand editIssueCommand = new EditIssueCommand(null, null, null, new IssueId(1));
        assertThrows(CommandException.class, noIssueIdOneInProjectBook, () ->
                editIssueCommand.execute(model, stubUi));
    }

}
