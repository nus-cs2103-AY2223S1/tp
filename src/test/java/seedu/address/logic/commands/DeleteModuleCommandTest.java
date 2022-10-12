package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {

    private static final String NON_EXISTENT_MODULE_CODE = "CS2109S";
    private Model model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());

    @Test
    public void execute_validModuleUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(moduleToDelete.getModuleCode());

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentModuleUnfilteredList_throwsCommandException() {
        ModuleCode nonExistentModule = new ModuleCode(NON_EXISTENT_MODULE_CODE);
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(nonExistentModule);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_SUCH_MODULE);
    }

    @Test
    public void equals() {
        DeleteModuleCommand deleteCsCommand =
                new DeleteModuleCommand(new ModuleCode(VALID_CS_MODULE_CODE));
        DeleteModuleCommand deleteMaCommand =
                new DeleteModuleCommand(new ModuleCode(VALID_MA_MODULE_CODE));

        // same object -> returns true
        assertTrue(deleteCsCommand.equals(deleteCsCommand));

        // same values -> returns true
        DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(new ModuleCode(VALID_CS_MODULE_CODE));
        assertTrue(deleteCsCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteCsCommand.equals(1));

        // null -> returns false
        assertFalse(deleteCsCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteCsCommand.equals(deleteMaCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
