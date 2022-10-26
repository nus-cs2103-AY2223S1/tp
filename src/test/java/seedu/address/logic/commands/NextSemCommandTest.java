package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NextSemCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.user.User;

public class NextSemCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nextSem_success() throws CommandException {
        User user = model.getUser();
        NextSemCommand nextSemCommand = new NextSemCommand();

        String expectedMessage = String.format(MESSAGE_SUCCESS, user);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.nextSem();

        assertCommandSuccess(nextSemCommand, model, expectedMessage, expectedModel);
    }
}
