package tracko.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import tracko.model.item.InventoryList;
import tracko.model.item.Item;
import tracko.model.order.Order;
import tracko.model.order.OrderList;

/**
 * Wraps all data at the root level
 */
public class TrackO implements ReadOnlyTrackO {

    private final OrderList orders;
    private final InventoryList items;

    {
        orders = new OrderList();
        items = new InventoryList();
    }

    public TrackO() {}

    /**
     * Creates a TrackO using the data in the {@code toBeCopied}
     */
    public TrackO(ReadOnlyTrackO toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TrackO} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackO newData) {
        requireNonNull(newData);
        setItems(newData.getInventoryList());
        setOrders(newData.getOrderList());
    }

    // ORDER METHODS =======================================================================

    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Adds an order to be tracked.
     * @param order The order to be added
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Deletes an order to be tracked.
     * @param order The order to be deleted
     */
    public void deleteOrder(Order order) {
        orders.delete(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    // ITEM METHODS =======================================================================

    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    public void setItems(InventoryList inventoryList) {
        this.items.setItems(inventoryList);
    }

    /**
     * Adds an item to be tracked.
     * @param item The item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Returns an item that has the given item name.
     * @param itemName The given item name
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Deletes an item to be tracked.
     * @param item The item to be added
     */
    public void deleteItem(Item item) {
        items.delete(item);
    }

    /**
     * Returns true if an item with the same identity as {@code item} exists in the inventory list.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the inventory list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory list.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);
        items.setItem(target, editedItem);
    }

    @Override
    public ObservableList<Item> getInventoryList() {
        return items.asUnmodifiableObservableList();
    }

    /**
     * Returns the inventory list object of the model.
     * @return The inventory list object of the model.
     */
    public InventoryList getInventoryReference() {
        return items;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TrackO)
                && items.equals(((TrackO) other).items)
                && orders.equals(((TrackO) other).orders);
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }

    public void setOrder(Order orderToEdit, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(orderToEdit, editedOrder);
    }

    public void refreshInventoryData() {
        items.refreshData();
    }


    public void refreshOrderData() {
        orders.refreshData();
    }
}
