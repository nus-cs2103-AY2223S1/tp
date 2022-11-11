package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.logic.commands.CommandResult;
import tracko.model.Model;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.TrackO;
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;
import tracko.testutil.OrderBuilder;


public class AddOrderCommandTest {

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        AddOrderCommand command = new AddOrderCommand(validOrder);
        command.setAwaitingInput(false);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.ordersAdded);
    }

    @Test
    public void equals() {
        Order alice = new OrderBuilder().withName("Alice").build();
        Order bob = new OrderBuilder().withName("Bob").build();
        AddOrderCommand addAliceCommand = new AddOrderCommand(alice);
        AddOrderCommand addBobCommand = new AddOrderCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddOrderCommand addAliceCommandCopy = new AddOrderCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different order -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTrackOFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackOFilePath(Path trackOFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order orderToEdit, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markOrder(Order orderToMark, boolean isPaid, boolean isDelivered) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(InventoryItem inventoryItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public InventoryItem getItem(String itemName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InventoryItem> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(InventoryItem inventoryItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(InventoryItem inventoryItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(InventoryItem target, InventoryItem editedInventoryItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<InventoryItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredItemListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InventoryItem> getInventoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackO(ReadOnlyTrackO newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getSortedOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedOrderList(Comparator<Order> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<Order> ordersAdded = new ArrayList<>();

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public ReadOnlyTrackO getTrackO() {
            return new TrackO();
        }
    }

}
