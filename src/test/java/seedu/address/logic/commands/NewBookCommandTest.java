package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewBookCommand.MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT;

public class NewBookCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT,
                false, true, false, false);
        assertCommandSuccess(new NewBookCommand(), model, expectedCommandResult, expectedModel);
    }
}
