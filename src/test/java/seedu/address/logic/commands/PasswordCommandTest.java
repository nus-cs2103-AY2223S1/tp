package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_SET_PASSWORD_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class PasswordCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_updatePasswordPasswordNotSet_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        PasswordCommand passwordCommand = new PasswordCommand("password", "foobar");
        passwordCommand.execute(expectedModel);
        assertTrue(expectedModel.isPasswordCorrect("foobar"));
    }

    @Test
    public void execute_setPasswordPasswordNotSet_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        PasswordCommand passwordCommand = new PasswordCommand("foobar");
        passwordCommand.execute(expectedModel);
        assertTrue(expectedModel.isPasswordCorrect("foobar"));
    }

    @Test
    public void execute_setPasswordPasswordWeak_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        PasswordCommand passwordCommand = new PasswordCommand("123456");
        CommandResult commandResult = passwordCommand.execute(expectedModel);
        assertEquals(commandResult.getFeedbackToUser(), String.format(MESSAGE_SET_PASSWORD_SUCCESS,
                "Password strength: Weak\n"
                        + "Warning: This is a top-10 common password.\n"
                        + "Suggestions:\n"
                        + "- Add another word or two. Uncommon words are better."));
        assertTrue(expectedModel.isPasswordCorrect("123456"));
    }

    @Test
    public void execute_setPasswordPasswordVeryStrong_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        PasswordCommand passwordCommand = new PasswordCommand("5!c)|w}Rk<fU;GCMZ_vr");
        CommandResult commandResult = passwordCommand.execute(expectedModel);
        assertEquals(commandResult.getFeedbackToUser(), String.format(MESSAGE_SET_PASSWORD_SUCCESS,
                "Password strength: Very strong"));
        assertTrue(expectedModel.isPasswordCorrect("5!c)|w}Rk<fU;GCMZ_vr"));
    }

    @Test
    public void execute_updatePasswordPasswordSet_success() throws CommandException {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.updatePassword("", "foobar");
        PasswordCommand passwordCommand = new PasswordCommand("foobar", "barfoo");
        passwordCommand.execute(expectedModel);
        assertTrue(expectedModel.isPasswordCorrect("barfoo"));
    }

    @Test
    public void execute_setPasswordPasswordSet_failure() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.updatePassword("", "foobar");
        PasswordCommand passwordCommand = new PasswordCommand("barfoo");

        assertCommandFailure(passwordCommand, expectedModel,
                String.format(PasswordCommand.MESSAGE_PASSWORD_UPDATE_ERROR,
                        PasswordCommand.MESSAGE_PASSWORD_ALREADY_SET));
    }

    @Test
    public void execute_incorrectOldPassword_failure() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.updatePassword("", "foobar");
        PasswordCommand passwordCommand = new PasswordCommand("password", "barfoo");

        assertCommandFailure(passwordCommand, expectedModel,
                String.format(PasswordCommand.MESSAGE_PASSWORD_UPDATE_ERROR,
                        PasswordCommand.MESSAGE_INCORRECT_PASSWORD));
    }

    @Test
    public void equals() {
        final PasswordCommand standardCommand = new PasswordCommand("foobar", "barfoo");

        // same values -> returns true
        PasswordCommand commandWithSameValues = new PasswordCommand("foobar", "barfoo");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different oldPassword -> returns false
        assertFalse(standardCommand.equals(new PasswordCommand("barfoo")));
        assertFalse(standardCommand.equals(new PasswordCommand("password", "barfoo")));

        // different newPassword -> returns false
        assertFalse(standardCommand.equals(new PasswordCommand("foobar", "password")));
    }

}
