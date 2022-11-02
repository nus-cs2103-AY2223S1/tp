package tracko.model.order;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tracko.model.item.InventoryItem;
import tracko.model.item.exceptions.ItemNotFoundException;

/**
 * A list of orders.
 *
 * Supports a minimal set of list operations
 */
public class OrderList implements Iterable<Order> {

    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an order to the list.
     * The order must not already exist in the list.
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Delete an order from the list.
     * The order exist in the list.
     */
    public void delete(Order toDelete) {
        requireNonNull(toDelete);
        internalList.remove(toDelete);
    }

    public void setOrders(OrderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        internalList.setAll(orders);
    }

    public boolean containsOrderWithItem(InventoryItem inventoryItem) {
        return internalList.stream().anyMatch(order -> order.containsItem(inventoryItem));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    /**
     * Refreshes the data to reflect the updated data in the GUI.
     */
    public void refreshData() {
        for (int i = 0; i < internalList.size(); i++) {
            Order order = internalList.get(i);
            internalList.set(i, order);
        }
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof OrderList // instanceof handles nulls
            && internalList.equals(((OrderList) other).internalList));
    }

    public void setOrder(Order orderToEdit, Order editedOrder) {
        requireAllNonNull(orderToEdit, editedOrder);

        int index = internalList.indexOf(orderToEdit);

        if (index == -1) {
            throw new ItemNotFoundException();
        }

        internalList.set(index, editedOrder);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
