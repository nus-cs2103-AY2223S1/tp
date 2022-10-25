package seedu.address.logic.commands;

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
    public void execute_history_empty() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EMPTY);
        assertCommandSuccess(new HistoryCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_history_filled() {
        HistoryList.addToHistory("test");
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS + HistoryList.printList());
        assertCommandSuccess(new HistoryCommand(), model, expectedCommandResult, expectedModel);
        HistoryList.clearList();
    }
}
