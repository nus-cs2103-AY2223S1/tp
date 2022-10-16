package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleWithModuleCode;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBookWithModules;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ModuleViewCommand}.
 */
public class ModuleViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithModules(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleCode moduleToViewCode = moduleToView.getCode();
        ModuleViewCommand viewModuleCommand = new ModuleViewCommand(moduleToViewCode);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ModuleViewCommand.MESSAGE_SUCCESS, moduleToView),
                false, false, false,
                false, true, false);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidModuleCodeUnfilteredList_throwsCommandException() {
        ModuleCode moduleCode = new ModuleCode("null");
        ModuleViewCommand viewModuleCommand = new ModuleViewCommand(moduleCode);

        assertCommandFailure(viewModuleCommand, model, Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
    }

    @Test
    public void execute_validModuleCodeFilteredList_success() {
        showModuleWithModuleCode(model, INDEX_FIRST_MODULE);

        Module moduleToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleCode moduleCode = moduleToView.getCode();
        ModuleViewCommand viewModuleCommand = new ModuleViewCommand(moduleCode);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ModuleViewCommand.MESSAGE_SUCCESS, moduleToView),
                false, false, false,
                false, true, false);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showNoModule(expectedModel);

        assertCommandSuccess(viewModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ModuleViewCommand viewFirstCommand = new ModuleViewCommand(CODE_FIRST_MODULE);
        ModuleViewCommand viewSecondCommand = new ModuleViewCommand(CODE_SECOND_MODULE);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ModuleViewCommand viewFirstCommandCopy = new ModuleViewCommand(CODE_FIRST_MODULE);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no module.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
