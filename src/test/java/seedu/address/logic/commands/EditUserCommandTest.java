package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_XAVIER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.user.User;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.UserBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditUserCommand.
 */
public class EditUserCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        User editedUser = new UserBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedUser).build();
        EditUserCommand editCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, editedUser);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        User defaultUser = model.getUser();

        UserBuilder userInList = new UserBuilder(defaultUser);
        User editedUser = userInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withGithub(VALID_GITHUB_BOB).build();

        EditUserCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withGithub(VALID_GITHUB_BOB).build();
        EditUserCommand editCommand = new EditUserCommand(descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, editedUser);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditUserCommand editCommand = new EditUserCommand(new EditUserCommand.EditPersonDescriptor());
        User editedUser = model.getUser();

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_USER_SUCCESS, editedUser);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyUser_failure() {
        Model modelWithoutUser = model;
        modelWithoutUser.deleteUser();

        User defaultUser = model.getUser();
        EditUserCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(defaultUser).build();
        EditUserCommand editCommand = new EditUserCommand(descriptor);

        assertCommandFailure(editCommand, modelWithoutUser, EditUserCommand.MESSAGE_NO_USER_TO_EDIT);
    }

    @Test
    public void equals() {
        final EditUserCommand standardCommand = new EditUserCommand(DESC_ZAC);

        // same values -> returns true
        EditCommand.EditPersonDescriptor copyDescriptor = new EditCommand.EditPersonDescriptor(DESC_ZAC);
        EditUserCommand commandWithSameValues = new EditUserCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditUserCommand(DESC_XAVIER)));
    }

}
