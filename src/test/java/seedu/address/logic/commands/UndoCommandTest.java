package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class UndoCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_undo_success() {
        CommandResult expectedCommandResult =
                new CommandResult(UndoCommand.MESSAGE_UNDO_ACKNOWLEDGEMENT, false, true, false, false);
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }
}
