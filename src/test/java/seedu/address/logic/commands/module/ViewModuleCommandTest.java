package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleWithModuleCode;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalProfNusWithModules;

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
 * {@code ViewModuleCommand}.
 */
public class ViewModuleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithModules(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleCode moduleToViewCode = moduleToView.getCode();
        ViewModuleCommand viewModuleCommand = new ViewModuleCommand(moduleToViewCode);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewModuleCommand.MESSAGE_SUCCESS, moduleToView),
                false, false, false,
                false, false, true, false, false, false);

        ModelManager expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());

        assertCommandSuccess(viewModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidModuleCodeUnfilteredList_throwsCommandException() {
        ModuleCode moduleCode = new ModuleCode("null");
        ViewModuleCommand viewModuleCommand = new ViewModuleCommand(moduleCode);

        assertCommandFailure(viewModuleCommand, model, Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
    }

    @Test
    public void execute_validModuleCodeFilteredList_success() {
        showModuleWithModuleCode(model, INDEX_FIRST_MODULE);

        Module moduleToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleCode moduleCode = moduleToView.getCode();
        ViewModuleCommand viewModuleCommand = new ViewModuleCommand(moduleCode);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewModuleCommand.MESSAGE_SUCCESS, moduleToView),
                false, false, false,
                false, false, true, false, false, false);

        Model expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
        showNoModule(expectedModel);

        assertCommandSuccess(viewModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ViewModuleCommand viewFirstCommand = new ViewModuleCommand(CODE_FIRST_MODULE);
        ViewModuleCommand viewSecondCommand = new ViewModuleCommand(CODE_SECOND_MODULE);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewModuleCommand viewFirstCommandCopy = new ViewModuleCommand(CODE_FIRST_MODULE);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different module -> returns false
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
