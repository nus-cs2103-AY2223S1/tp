package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearAllCommandTest {
    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        Model model = new ModelManager();

        assertThrows(CommandException.class, ClearAllCommand.MESSAGE_ALL_LISTS_ALREADY_EMPTY,
                () -> new ClearAllCommand().execute(model));
    }

//    @Test
//    public void execute_nonEmptyAddressBook_success() {
//        Model model = new ModelManager()
//    }
}
