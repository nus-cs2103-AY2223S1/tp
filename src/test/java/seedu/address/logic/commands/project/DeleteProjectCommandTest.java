package seedu.address.logic.commands.project;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

class DeleteProjectCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_deleteNonExistingProject_throwsCommandException() {
        Model model = new ModelManager();
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_ID, () ->
                deleteProjectCommand.execute(model, stubUi));
    }

}
