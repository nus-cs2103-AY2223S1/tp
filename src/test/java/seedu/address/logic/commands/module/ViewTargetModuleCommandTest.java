package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalProfNusWithModules;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewTargetModuleCommand}.
 */
public class ViewTargetModuleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithModules(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ViewTargetModuleCommand viewTargetModuleCommand = new ViewTargetModuleCommand(INDEX_FIRST_MODULE);
        Module moduleTargetToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewTargetModuleCommand.MESSAGE_VIEW_TARGET_MODULE_SUCCESS, moduleTargetToView),
                false, false, false,
                false, true, false, false, false, false);

        ModelManager expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());

        assertCommandSuccess(viewTargetModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAllModuleList().size() + 1);
        ViewTargetModuleCommand viewTargetModuleCommand = new ViewTargetModuleCommand(outOfBoundIndex);

        assertCommandFailure(viewTargetModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        Module moduleTargetToView = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ViewTargetModuleCommand viewTargetModuleCommand = new ViewTargetModuleCommand(INDEX_FIRST_MODULE);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewTargetModuleCommand.MESSAGE_VIEW_TARGET_MODULE_SUCCESS, moduleTargetToView),
                false, false, false,
                false, true, false, false, false, false);

        Model expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());

        assertCommandSuccess(viewTargetModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ViewTargetModuleCommand viewFirstCommand = new ViewTargetModuleCommand(INDEX_FIRST_MODULE);
        ViewTargetModuleCommand viewSecondCommand = new ViewTargetModuleCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewTargetModuleCommand viewFirstCommandCopy = new ViewTargetModuleCommand(INDEX_FIRST_MODULE);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
