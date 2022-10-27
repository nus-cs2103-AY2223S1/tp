package tracko.model.item;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tracko.model.item.exceptions.DuplicateItemException;
import tracko.model.item.exceptions.ItemNotFoundException;
import tracko.model.order.Order;

/**
 * Represents the list of items in the inventory.
 */
public class InventoryList implements Iterable<InventoryItem> {

    private final ObservableList<InventoryItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<InventoryItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an item to the inventory list.
     * @param toAdd Item to be added to the list
     */
    public void add(InventoryItem toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns an item with the given item name.
     * @param itemName The given item name
     * @returns An item with the given item name.
     * @throws ItemNotFoundException if item is not found
     */
    public InventoryItem get(String itemName) {

        Iterator<InventoryItem> itemIterator = iterator();

        while (itemIterator.hasNext()) {
            InventoryItem inventoryItem = itemIterator.next();
            if (inventoryItem.nameMatches(itemName)) {
                return inventoryItem;
            }
        }

        throw new ItemNotFoundException();
    }

    /**
     * Deletes an item from the inventory list.
     * @param toDelete Item to be deleted from the list
     */
    public void delete(InventoryItem toDelete) {
        requireNonNull(toDelete);
        internalList.remove(toDelete);
    }

    /**
     * Decreases quantity of items in Inventory list according to items in order
     * that was marked as delivered
     * @param deliveredOrder order that was delivered
     */
    public void reduceItems(Order deliveredOrder) {
        // decreases the quantity of each item delivered in the inventory list according to the quantity delivered
        internalList.forEach(item -> deliveredOrder.getItemList().forEach(orderItem -> {
            if (item.isSameItem(orderItem.getItem())) {
                item.reduceItem(orderItem.getQuantityValue());
            }
        }));
    }

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(InventoryItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItem);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the list.
     */
    public void setItem(InventoryItem target, InventoryItem editedInventoryItem) {
        requireAllNonNull(target, editedInventoryItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSameItem(editedInventoryItem) && contains(editedInventoryItem)) {
            throw new DuplicateItemException();
        }

        target.updateData(editedInventoryItem);
    }

    public void setItems(InventoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setItems(List<InventoryItem> inventoryItems) {
        requireAllNonNull(inventoryItems);
        internalList.setAll(inventoryItems);
    }

    /**
     * Refreshes the data to reflect the updated data in the GUI.
     */
    public void refreshData() {
        for (int i = 0; i < internalList.size(); i++) {
            internalList.set(i, internalList.get(i));
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InventoryItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InventoryItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryList // instanceof handles nulls
                && internalList.equals(((InventoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }


}
