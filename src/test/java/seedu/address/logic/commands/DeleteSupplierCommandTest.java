package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSupplierAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalSuppliers.getTypicalSupplierAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Supplier;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSupplierCommand}.
 */
public class DeleteSupplierCommandTest {

    private Model modelForSuppliers = new ModelManager(getTypicalSupplierAddressBook(), new UserPrefs());

    // Supplier List Tests
    @Test
    public void execute_validIndexUnfilteredSupplierList_success() {
        Supplier personToDelete = modelForSuppliers.getFilteredSupplierList().get(INDEX_FIRST.getZeroBased());
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_SUPPLIER_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForSuppliers.getAddressBook(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);

        assertCommandSuccess(deleteSupplierCommand, modelForSuppliers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredSupplierList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForSuppliers.getFilteredSupplierList().size() + 1);
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteSupplierCommand, modelForSuppliers,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredSupplierList_success() {
        showSupplierAtIndex(modelForSuppliers, INDEX_FIRST);

        Supplier personToDelete = modelForSuppliers.getFilteredSupplierList().get(INDEX_FIRST.getZeroBased());
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_SUPPLIER_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForSuppliers.getAddressBook(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);
        showNoSupplier(expectedModel);

        assertCommandSuccess(deleteSupplierCommand, modelForSuppliers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredSupplierList_throwsCommandException() {
        showSupplierAtIndex(modelForSuppliers, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < modelForSuppliers.getAddressBook().getSupplierList().size());

        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteSupplierCommand, modelForSuppliers,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_supplier() {
        DeleteSupplierCommand deleteFirstCommand = new DeleteSupplierCommand(INDEX_FIRST);
        DeleteSupplierCommand deleteSecondCommand = new DeleteSupplierCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteSupplierCommand deleteFirstCommandCopy = new DeleteSupplierCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no Suppliers.
     */
    private void showNoSupplier(Model model) {
        model.updateFilteredSupplierList(p -> false);

        assertTrue(model.getFilteredSupplierList().isEmpty());
    }
}
