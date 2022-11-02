package tracko.logic.commands.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.commands.CommandTestUtil.DESC_AMY;
import static tracko.logic.commands.CommandTestUtil.DESC_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tracko.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tracko.logic.commands.CommandTestUtil.assertCommandFailure;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.logic.commands.CommandTestUtil.showOrderAtIndex;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalIndexes.INDEX_SECOND;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Order;
import tracko.testutil.EditOrderDescriptorBuilder;
import tracko.testutil.ItemQuantityPairBuilder;
import tracko.testutil.OrderBuilder;
import tracko.testutil.TypicalItems;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditOrderCommandTest {

    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        LocalDateTime timeUsed = model.getFilteredOrderList().get(0).getTimeCreated();

        //Builds an order using default fields in OrderBuilder but use timeCreated from the first order of the list
        //This is because editing of orders does not edit the timeCreated of an order
        Order editedOrder = new OrderBuilder().withTimeCreated(timeUsed).build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        List<ItemQuantityPair> defaultItemList = new ArrayList<>();
        defaultItemList.add(new ItemQuantityPairBuilder().build());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);
        Order editedOrder = orderInList.withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withItemList(defaultItemList).build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withItemList(defaultItemList).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST, new EditOrderDescriptor());
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList).withName(VALID_NAME_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
            .withName(VALID_NAME_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackO().getOrderList().size());

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_removeOnlyItemInOrderList_failure() {
        Model copiedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        copiedModel.addItem(TypicalItems.INVENTORY_ITEM_1);
        Order initialOrder = new OrderBuilder().build();
        copiedModel.setOrder(copiedModel.getOrderList().get(INDEX_FIRST.getZeroBased()), initialOrder);

        Pair<String, Integer> unlinkedPair = new Pair<>(
                initialOrder.getItemList().get(INDEX_FIRST.getZeroBased()).getItemName(), 0);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(initialOrder)
                .withUnlinkedPair(unlinkedPair).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST.fromZeroBased(0), descriptor);

        assertCommandFailure(editOrderCommand, copiedModel, EditOrderCommand.MESSAGE_ONE_ORDERED_ITEM);
    }

    @Test
    public void execute_removeOneItemFromList_success() {
        Model copiedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        copiedModel.addItem(TypicalItems.INVENTORY_ITEM_1);
        Order editedOrder = new OrderBuilder().build();
        Order initialOrder = new OrderBuilder().withItemQuantityPair(new ItemQuantityPairBuilder().build()).build();
        copiedModel.setOrder(copiedModel.getOrderList().get(INDEX_FIRST.getZeroBased()), initialOrder);

        Pair<String, Integer> unlinkedPair = new Pair<>(
                initialOrder.getItemList().get(INDEX_SECOND.getZeroBased()).getItemName(), 0);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(initialOrder)
                .withUnlinkedPair(unlinkedPair).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND.fromZeroBased(0), descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(copiedModel.getTrackO(), new UserPrefs());
        expectedModel.setOrder(copiedModel.getFilteredOrderList().get(0), editedOrder);
        assertCommandSuccess(editOrderCommand, copiedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_itemNotInInventory_failure() {
        Model copiedModel = new ModelManager(model.getTrackO(), new UserPrefs());
        Order initialOrder = new OrderBuilder().build();

        Pair<String, Integer> unlinkedPair = new Pair<>("Pencil Case", 20);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(initialOrder)
                .withUnlinkedPair(unlinkedPair).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST.fromZeroBased(0), descriptor);

        assertCommandFailure(editOrderCommand, copiedModel, EditOrderCommand.MESSAGE_NONEXISTENT_ITEM);
    }

    @Test
    public void equals() {
        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditOrderDescriptor copyDescriptor = new EditOrderDescriptor(DESC_AMY);
        EditOrderCommand commandWithSameValues = new EditOrderCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_FIRST, DESC_BOB)));
    }

}
