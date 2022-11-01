package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditModuleCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // For expected
        Module editedModule =
                new ModuleBuilder().withLinks(new HashSet<>()).withTasks(new ArrayList<>()).build();

        // For actual
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        expectedModel.goToHomePage();

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());

        // For expected
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule =
                moduleInList.withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK).build();

        // For actual
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(indexLastModule, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);
        expectedModel.goToHomePage();

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // For actual
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, new EditModuleDescriptor());

        // For expected
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.goToHomePage();

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        // For expected
        Module moduleInFilteredList =
                model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module editedModule =
                new ModuleBuilder(moduleInFilteredList)
                        .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK)
                        .build();

        // For actual
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder()
                        .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK)
                        .build());

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        expectedModel.goToHomePage();

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        // For actual
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SECOND_MODULE, descriptor);

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        // edit module in filtered list into a duplicate in address book
        Module moduleInList = model.getAddressBook().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        // For actual
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleCode(VALID_MA2001_MODULE_CODE).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than number of modules in Plannit.
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of unfiltered module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getModuleList().size());

        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex,
                new EditModuleDescriptorBuilder().withModuleCode(VALID_MA2001_MODULE_CODE).build());

        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_MODULE, DESC_CS2106);

        // same values -> returns true
        EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_CS2106);
        EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        assertFalse(standardCommand.equals(2103));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_MODULE, DESC_CS2106)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_FIRST_MODULE, DESC_MA2001)));
    }

}
