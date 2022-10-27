package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code GetCommand}.
 */
public class GetCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_getCommand_throwsCommandException() {
        assertThrows(CommandException.class, () -> new GetCommand().execute(model));
    }
}
