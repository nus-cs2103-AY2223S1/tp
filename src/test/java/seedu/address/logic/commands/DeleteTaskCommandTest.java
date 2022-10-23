package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_TASK_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_TASK_A;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromModuleDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.DeleteTaskFromModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());
    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null));
    }
    @Test
    public void execute_taskDeletedFromModule_success() {
        DeleteTaskFromModuleDescriptor descriptor =
                new DeleteTaskFromModuleDescriptorBuilder(CS2103T_WITH_TASK_A).build();
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(descriptor);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                CS2103T);

        Model modelWithTaskToDelete = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        modelWithTaskToDelete.setModule(CS2103T, CS2103T_WITH_TASK_A);

        assertCommandSuccess(deleteTaskCommand, modelWithTaskToDelete,
                expectedMessage, model);
    }

    @Test
    public void execute_nonExistentModuleCode_throwsCommandException() {
        Module nonExistentModule =
                new ModuleBuilder()
                        .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK).build();
        DeleteTaskCommand.DeleteTaskFromModuleDescriptor descriptor =
                new DeleteTaskFromModuleDescriptorBuilder(nonExistentModule).build();
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(descriptor);

        assertThrows(CommandException.class, String.format(MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                        nonExistentModule.getModuleCodeAsUpperCaseString()), () ->
                        deleteTaskCommand.execute(model));
    }
    @Test
    public void execute_nonExistentTaskNumber_throwsCommandException() {
        Index nonExistentTaskNumber = Index.fromOneBased(99);
        DeleteTaskFromModuleDescriptor descriptor =
                new DeleteTaskFromModuleDescriptorBuilder(CS2103T,
                        nonExistentTaskNumber).build();
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(descriptor);

        assertThrows(CommandException.class,
                MESSAGE_NO_SUCH_TASK_NUMBER, () ->
                deleteTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        final List<Module> modules = getTypicalModules();
        final DeleteTaskFromModuleDescriptor standardDescriptor =
                new DeleteTaskFromModuleDescriptorBuilder(CS2103T).build();
        final DeleteTaskCommand standardCommand = new DeleteTaskCommand(standardDescriptor);

        // same values -> returns true
        DeleteTaskFromModuleDescriptor copyDescriptor = new DeleteTaskFromModuleDescriptorBuilder(CS2103T).build();
        DeleteTaskCommand commandWithSameValues = new DeleteTaskCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new AddModuleCommand(CS2103T)));

        // different module code -> returns false
        DeleteTaskFromModuleDescriptor differentModuleDescriptor =
                new DeleteTaskFromModuleDescriptorBuilder(modules.get(1)).build();
        assertFalse(standardCommand.equals(new DeleteTaskCommand(differentModuleDescriptor)));

        // different task number -> returns false
        final DeleteTaskFromModuleDescriptor differentTaskDescriptionDescriptor =
                new DeleteTaskFromModuleDescriptorBuilder(CS2103T,
                        Index.fromOneBased(999)).build();
        assertFalse(standardCommand.equals(new DeleteTaskCommand(differentTaskDescriptionDescriptor)));
    }

}
