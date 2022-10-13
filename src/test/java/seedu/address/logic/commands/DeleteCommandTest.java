package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showDelivererAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showSupplierAtIndex;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;
import static seedu.address.testutil.TypicalDeliverers.getTypicalDelivererAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersonCategories.PERSON_CATEGORY_BUYER;
import static seedu.address.testutil.TypicalPersonCategories.PERSON_CATEGORY_DELIVERER;
import static seedu.address.testutil.TypicalPersonCategories.PERSON_CATEGORY_SUPPLIER;
import static seedu.address.testutil.TypicalSuppliers.getTypicalSupplierAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model modelForBuyers = new ModelManager(getTypicalBuyerAddressBook(), new UserPrefs());
    private Model modelForDeliverers = new ModelManager(getTypicalDelivererAddressBook(), new UserPrefs());
    private Model modelForSuppliers = new ModelManager(getTypicalSupplierAddressBook(), new UserPrefs());

    // Buyer List Tests
    @Test
    public void execute_validIndexUnfilteredBuyerList_success() {
        Buyer personToDelete = modelForBuyers.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForBuyers.getAddressBook(), new UserPrefs());
        expectedModel.deleteBuyer(personToDelete);

        assertCommandSuccess(deleteCommand, modelForBuyers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredBuyerList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForBuyers.getFilteredBuyerList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForBuyers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredBuyerList_success() {
        showBuyerAtIndex(modelForBuyers, INDEX_FIRST_PERSON);

        Buyer personToDelete = modelForBuyers.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForBuyers.getAddressBook(), new UserPrefs());
        expectedModel.deleteBuyer(personToDelete);
        showNoBuyer(expectedModel);

        assertCommandSuccess(deleteCommand, modelForBuyers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredBuyerList_throwsCommandException() {
        showBuyerAtIndex(modelForBuyers, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelForBuyers.getAddressBook().getBuyerList().size());

        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForBuyers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // Deliverer List Tests
    @Test
    public void execute_validIndexUnfilteredDelivererList_success() {
        Deliverer personToDelete = modelForDeliverers.getFilteredDelivererList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForDeliverers.getAddressBook(), new UserPrefs());
        expectedModel.deleteDeliverer(personToDelete);

        assertCommandSuccess(deleteCommand, modelForDeliverers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredDelivererList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForDeliverers.getFilteredDelivererList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForDeliverers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredDelivererList_success() {
        showDelivererAtIndex(modelForDeliverers, INDEX_FIRST_PERSON);

        Deliverer personToDelete = modelForDeliverers.getFilteredDelivererList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForDeliverers.getAddressBook(), new UserPrefs());
        expectedModel.deleteDeliverer(personToDelete);
        showNoDeliverer(expectedModel);

        assertCommandSuccess(deleteCommand, modelForDeliverers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredDelivererList_throwsCommandException() {
        showDelivererAtIndex(modelForDeliverers, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelForDeliverers.getAddressBook().getDelivererList().size());

        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForDeliverers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // Supplier List Tests
    @Test
    public void execute_validIndexUnfilteredSupplierList_success() {
        Supplier personToDelete = modelForSuppliers.getFilteredSupplierList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForSuppliers.getAddressBook(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);

        assertCommandSuccess(deleteCommand, modelForSuppliers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredSupplierList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForSuppliers.getFilteredSupplierList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForSuppliers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredSupplierList_success() {
        showSupplierAtIndex(modelForSuppliers, INDEX_FIRST_PERSON);

        Supplier personToDelete = modelForSuppliers.getFilteredSupplierList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForSuppliers.getAddressBook(), new UserPrefs());
        expectedModel.deleteSupplier(personToDelete);
        showNoSupplier(expectedModel);

        assertCommandSuccess(deleteCommand, modelForSuppliers, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredSupplierList_throwsCommandException() {
        showSupplierAtIndex(modelForSuppliers, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelForSuppliers.getAddressBook().getSupplierList().size());

        DeleteCommand deleteCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelForSuppliers, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_buyer() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(PERSON_CATEGORY_BUYER, INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(PERSON_CATEGORY_BUYER, INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void equals_deliverer() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(PERSON_CATEGORY_DELIVERER, INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(PERSON_CATEGORY_DELIVERER, INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void equals_supplier() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(PERSON_CATEGORY_SUPPLIER, INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no buyers.
     */
    private void showNoBuyer(Model model) {
        model.updateFilteredBuyerList(p -> false);

        assertTrue(model.getFilteredBuyerList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no deliverers.
     */
    private void showNoDeliverer(Model model) {
        model.updateFilteredDelivererList(p -> false);

        assertTrue(model.getFilteredDelivererList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no suppliers.
     */
    private void showNoSupplier(Model model) {
        model.updateFilteredSupplierList(p -> false);

        assertTrue(model.getFilteredSupplierList().isEmpty());
    }
}
