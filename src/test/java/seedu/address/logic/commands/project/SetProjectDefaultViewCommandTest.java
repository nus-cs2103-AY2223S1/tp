package seedu.address.logic.commands.project;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.project.SetProjectDefaultViewCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DefaultView;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

public class SetProjectDefaultViewCommandTest {

    private Model model = new ModelManager();
    private Ui stubUi = new StubUiManager();

    @Test
    public void execute_projectDefaultView_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false);
        Model expectedModel = new ModelManager();
        expectedModel.setDefaultView(DefaultView.PROJECT);
        assertCommandSuccess(new SetProjectDefaultViewCommand(), model, expectedCommandResult, expectedModel, stubUi);
    }
}
