package seedu.address.logic.commands.issue;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.SetIssueDefaultViewCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DefaultView;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

class SetIssueDefaultViewCommandTest {

    private Model model = new ModelManager();
    private Ui stubUi = new StubUiManager();

    @Test
    public void execute_issueDefaultView_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false);
        Model expectedModel = new ModelManager();
        expectedModel.setDefaultView(DefaultView.ISSUE);
        assertCommandSuccess(new SetIssueDefaultViewCommand(), model, expectedCommandResult, expectedModel, stubUi);
    }
}
