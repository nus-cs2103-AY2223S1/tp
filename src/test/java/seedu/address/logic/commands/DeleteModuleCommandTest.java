package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {

    private static final String NON_EXISTENT_MODULE_CODE = "CS2109S";
    private Model model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());

    @Test
    public void execute_validModuleAtHome_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(moduleToDelete.getModuleCode());

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleNotAtHome_success() {
        model.setHomeStatus(false);

        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        moduleToDelete = new ModuleBuilder(moduleToDelete).withModuleCode(
                moduleToDelete.getModuleCodeAsUpperCaseString().toLowerCase()).build();
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(moduleToDelete.getModuleCode());

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                moduleToDelete);

        // HomeStatus is initialised as true when model is first initialised.
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleIgnoreCaseUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        moduleToDelete = new ModuleBuilder(moduleToDelete).withModuleCode(
                        moduleToDelete.getModuleCodeAsUpperCaseString().toLowerCase()).build();
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(moduleToDelete.getModuleCode());

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentModule_throwsCommandException() {
        ModuleCode nonExistentModule = new ModuleCode(NON_EXISTENT_MODULE_CODE);
        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(nonExistentModule);
        ObservableList<Boolean> isAtHome = FXCollections.observableArrayList(true);
        ObservableList<Boolean> isNotAtHome = FXCollections.observableArrayList(false);

        // At Home Page
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_SUCH_MODULE);
        assertEquals(model.getHomeStatus(), isAtHome);

        // Not At Home Page
        model.setHomeStatus(false);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_SUCH_MODULE);
        assertEquals(model.getHomeStatus(), isNotAtHome);
    }

    @Test
    public void equals() {
        DeleteModuleCommand deleteCsCommand =
                new DeleteModuleCommand(new ModuleCode(VALID_CS2106_MODULE_CODE));
        DeleteModuleCommand deleteMaCommand =
                new DeleteModuleCommand(new ModuleCode(VALID_MA2001_MODULE_CODE));

        // same object -> returns true
        assertTrue(deleteCsCommand.equals(deleteCsCommand));

        // same values -> returns true
        DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(new ModuleCode(VALID_CS2106_MODULE_CODE));
        assertTrue(deleteCsCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteCsCommand.equals(1));

        // null -> returns false
        assertFalse(deleteCsCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteCsCommand.equals(deleteMaCommand));
    }
}
