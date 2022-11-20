package seedu.address.logic.commands.module;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_PL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleWithModuleCode;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalProfNusWithModules;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProfNus;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditModuleCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithModules(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(CODE_FIRST_MODULE, descriptor);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                false, false, true,
                false, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());
        ModuleCode lastModuleCode = lastModule.getCode();

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withName(VALID_MODULE_NAME_CYBERSEC)
                .withModuleCode(VALID_MODULE_CODE_CYBERSEC)
                .withTags(VALID_TAG_IMPORTANT).build();

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULE_NAME_CYBERSEC)
                .withCode(VALID_MODULE_CODE_CYBERSEC).withTags(VALID_TAG_IMPORTANT).build();
        EditModuleCommand editCommand = new EditModuleCommand(lastModuleCode, descriptor);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                false, false, true,
                false, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditModuleCommand editModuleCommand = new EditModuleCommand(CODE_FIRST_MODULE, new EditModuleDescriptor());
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                false, false, true,
                false, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());

        assertCommandSuccess(editModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleWithModuleCode(model, INDEX_FIRST_MODULE);

        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList)
                .withModuleCode(VALID_MODULE_CODE_PL).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(CODE_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withCode(VALID_MODULE_CODE_PL).build());

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                false, false, true,
                false, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(CODE_SECOND_MODULE, descriptor);

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleWithModuleCode(model, INDEX_FIRST_MODULE);

        // edit module in filtered list into a duplicate in profNus
        Module moduleInList = model.getProfNus().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand(CODE_FIRST_MODULE,
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand(CODE_FIRST_MODULE, DESC_SWE);

        // same values -> returns true
        EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_SWE);
        EditModuleCommand commandWithSameValues = new EditModuleCommand(CODE_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different module code -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(CODE_SECOND_MODULE, DESC_SWE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(CODE_FIRST_MODULE, DESC_CYBERSEC)));
    }

}
