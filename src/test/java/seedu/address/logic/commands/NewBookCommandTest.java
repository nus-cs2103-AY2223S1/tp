package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NewBookCommand.MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class NewBookCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_newBook_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_NEW_BOOK_ACKNOWLEDGEMENT, null,
                false, true, false, false);
        assertCommandSuccess(new NewBookCommand(), model, expectedCommandResult, expectedModel);
    }
}
