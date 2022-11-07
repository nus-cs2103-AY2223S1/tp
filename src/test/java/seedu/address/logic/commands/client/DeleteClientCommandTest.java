package seedu.address.logic.commands.client;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;

class DeleteClientCommandTest {

    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_deleteNonExistingClient_throwsCommandException() {
        Model model = new ModelManager();
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_ID, () ->
                deleteClientCommand.execute(model, stubUi));
    }

}
