package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.user.User;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteUserCommand}.
 */
public class DeleteUserCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        User userToDelete = model.getUser();
        DeleteCommand deleteCommand = new DeleteUserCommand();

        String expectedMessage = String.format(DeleteUserCommand.MESSAGE_DELETE_USER_SUCCESS, userToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteUser();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noUserToDelete_throwsCommandException() {
        Model modelWithoutUser = model;
        modelWithoutUser.deleteUser();
        DeleteCommand deleteCommand = new DeleteUserCommand();

        String expectedMessage = DeleteUserCommand.MESSAGE_NO_USER_TO_DELETE;

        assertCommandFailure(deleteCommand, modelWithoutUser, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteUserCommand = new DeleteUserCommand();

        // same object -> returns true
        assertTrue(deleteUserCommand.equals(deleteUserCommand));

        // different types -> returns false
        assertFalse(deleteUserCommand.equals(1));

        // null -> returns false
        assertFalse(deleteUserCommand.equals(null));
    }

}
