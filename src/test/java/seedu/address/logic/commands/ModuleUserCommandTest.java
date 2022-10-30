package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_6;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_9;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.UserBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ModuleUserCommand.
 */
public class ModuleUserCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        User defaultUser = model.getUser();

        UserBuilder userInList = new UserBuilder(defaultUser);
        ExistingUser editedUser = (ExistingUser) userInList.withCurrentModules("CS2103T", VALID_MODULE_7)
                .withPreviousModules(VALID_MODULE_9).withPlannedModules("CS2109S", VALID_MODULE_11).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_7).withPreviousModules(VALID_MODULE_9)
                .withPlannedModules(VALID_MODULE_11).withModToRemove("CS2040S").build();
        ModuleUserCommand moduleCommand = new ModuleUserCommand(descriptor);

        String expectedMessage = String.format(ModuleUserCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedUser.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allAddFieldsSpecified_success() {
        User defaultUser = model.getUser();

        UserBuilder userInList = new UserBuilder(defaultUser);
        ExistingUser editedUser = (ExistingUser) userInList.withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3, VALID_MODULE_4)
                .withPlannedModules(VALID_MODULE_5, VALID_MODULE_6).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedUser).build();
        ModuleUserCommand moduleCommand = new ModuleUserCommand(descriptor);

        String expectedMessage = String.format(ModuleUserCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedUser.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        User defaultUser = model.getUser();

        UserBuilder userInList = new UserBuilder(defaultUser);
        ExistingUser editedUser = (ExistingUser) userInList.withCurrentModules("CS2103T", VALID_MODULE_2)
                .withPlannedModules("CS2109S", VALID_MODULE_11, VALID_MODULE_12).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_2).withPlannedModules(VALID_MODULE_11, VALID_MODULE_12).build();
        ModuleUserCommand moduleCommand = new ModuleUserCommand(descriptor);

        String expectedMessage = String.format(ModuleUserCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedUser.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeModulesOnly_success() {
        User defaultUser = model.getUser();

        UserBuilder userInList = new UserBuilder(defaultUser);
        ExistingUser editedUser = (ExistingUser) userInList.withCurrentModules().withPlannedModules().build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModToRemove("CS2103T", "CS2109S").build();
        ModuleUserCommand moduleCommand = new ModuleUserCommand(descriptor);

        String expectedMessage = String.format(ModuleUserCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedUser.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setUser(editedUser);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        ModuleUserCommand moduleCommand = new ModuleUserCommand(new ModuleCommand.EditModuleDescriptor());
        ExistingUser editedUser = (ExistingUser) model.getUser();

        String expectedMessage = String.format(ModuleUserCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedUser.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyUser_failure() {
        Model modelWithoutUser = model;
        modelWithoutUser.deleteUser();

        User defaultUser = model.getUser();
        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(defaultUser).build();
        ModuleUserCommand moduleCommand = new ModuleUserCommand(descriptor);

        assertCommandFailure(moduleCommand, modelWithoutUser, ModuleUserCommand.MESSAGE_NO_USER_TO_EDIT);
    }

    @Test
    public void equals() {
        final ModuleUserCommand standardCommand = new ModuleUserCommand(MODULE_DESC_AMY);

        // same values -> returns true
        ModuleCommand.EditModuleDescriptor copyDescriptor = new ModuleCommand.EditModuleDescriptor(MODULE_DESC_AMY);
        ModuleUserCommand commandWithSameValues = new ModuleUserCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ModuleUserCommand(MODULE_DESC_BOB)));
    }
}
