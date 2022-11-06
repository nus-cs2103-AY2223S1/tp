package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.product.Product;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteProductCommand}.
 */
public class DeleteProductCommandTest {

    private Model model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        // ep: productlist: [filtered][unfiltered], index:[valid][invalid]
        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(INDEX_FIRST_ELEMENT);

        String expectedMessage = String.format(DeleteProductCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        ModelManager expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);

        assertCommandSuccess(deleteProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_ELEMENT);
        assert model.getFilteredProductList().size() == 1;

        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_ELEMENT.getZeroBased());
        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(INDEX_FIRST_ELEMENT);

        String expectedMessage = String.format(DeleteProductCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        Model expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);
        showNoProduct(expectedModel);

        assertCommandSuccess(deleteProductCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProductAtIndex(model, INDEX_FIRST_ELEMENT);
        assert model.getFilteredProductList().size() == 1;

        Index outOfBoundIndex = INDEX_SECOND_ELEMENT;
        // ensures that outOfBoundIndex is still in bounds of client list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyInsuRec().getClientList().size());

        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteProductCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProductCommand deleteFirstCommand = new DeleteProductCommand(INDEX_FIRST_ELEMENT);
        DeleteProductCommand deleteSecondCommand = new DeleteProductCommand(INDEX_SECOND_ELEMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProductCommand deleteFirstCommandCopy = new DeleteProductCommand(INDEX_FIRST_ELEMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered product list to show no product.
     */
    private void showNoProduct(Model model) {
        model.updateFilteredProductList(p -> false);

        assertTrue(model.getFilteredProductList().isEmpty());
    }
}
