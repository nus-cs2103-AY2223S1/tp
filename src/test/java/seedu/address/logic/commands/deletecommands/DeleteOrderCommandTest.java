package seedu.address.logic.commands.deletecommands;
/*
//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalOrders.getTypicalOrdersAddressBook;

//import org.junit.jupiter.api.Test;
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.order.Order;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteOrderCommand}.
 */
/*
public class DeleteOrderCommandTest {

    private Model modelForOrders = new ModelManager(getTypicalOrdersAddressBook(), new UserPrefs());

     Order List Tests
    @Test
    public void execute_validIndexUnfilteredOrderList_success() {
        Order personToDelete = modelForOrders.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(modelForOrders.getAddressBook(), new UserPrefs());
        expectedModel.deleteOrder(personToDelete);

        assertCommandSuccess(deleteOrderCommand, modelForOrders, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredOrderList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelForOrders.getFilteredOrderList().size() + 1);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, modelForOrders,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredOrderList_success() {
        showOrderAtIndex(modelForOrders, INDEX_FIRST);

        Order personToDelete = modelForOrders.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(modelForOrders.getAddressBook(), new UserPrefs());
        expectedModel.deleteOrder(personToDelete);
        showNoOrder(expectedModel);

        assertCommandSuccess(deleteOrderCommand, modelForOrders, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredOrderList_throwsCommandException() {
        showOrderAtIndex(modelForOrders, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < modelForOrders.getAddressBook().getOrderList().size());

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, modelForOrders,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_order() {
        DeleteOrderCommand deleteFirstCommand = new DeleteOrderCommand(INDEX_FIRST);
        DeleteOrderCommand deleteSecondCommand = new DeleteOrderCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        }

    /**
     * Updates {@code model}'s filtered list to show no Orders.
     */
/*
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
*/
