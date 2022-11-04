package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HistoryCommand.MESSAGE_EMPTY;
import static seedu.address.logic.commands.HistoryCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.HistoryList;

public class HistoryCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_history_success() {
        HistoryList history = new HistoryList();
        history.addToHistory("test");
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS + history.printList());
        assertCommandSuccess(new HistoryCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    void equals() {
        HistoryCommand historyCommand1 = new HistoryCommand();
        HistoryCommand historyCommand2 = new HistoryCommand();

        // same object -> returns true
        assertTrue(historyCommand1.equals(historyCommand1));

        // different types -> returns false
        assertFalse(historyCommand1.equals(1));

        // null -> returns false
        assertFalse(historyCommand1.equals(null));

        // different historyLists -> returns false
        assertFalse(historyCommand1.equals(historyCommand2));
    }
}
