package seedu.address.logic.commands.issue;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

class DeleteIssueCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_deleteNonExistingIssue_throwsCommandException() {
        Model model = new ModelManager();
        DeleteIssueCommand deleteIssueCommand = new DeleteIssueCommand(INDEX_FIRST);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_ID, () ->
                deleteIssueCommand.execute(model, stubUi));
    }

}
