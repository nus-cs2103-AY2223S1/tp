package tracko.model.item;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tracko.model.item.exceptions.DuplicateItemException;
import tracko.model.item.exceptions.ItemNotFoundException;

/**
 * Represents the list of items in the inventory.
 */
public class InventoryList implements Iterable<Item> {

    private final ObservableList<Item> internalList = FXCollections.observableArrayList();
    private final ObservableList<Item> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an item to the inventory list.
     * @param toAdd Item to be added to the list
     */
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns an item with the given item name.
     * @param itemName The given item name
     * @returns An item with the given item name.
     * @throws ItemNotFoundException if item is not found
     */
    public Item get(String itemName) {

        Iterator<Item> itemIterator = iterator();

        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item.nameMatches(itemName)) {
                return item;
            }
        }

        throw new ItemNotFoundException();
    }

    /**
     * Deletes an item from the inventory list.
     * @param toDelete Item to be deleted from the list
     */
    public void delete(Item toDelete) {
        requireNonNull(toDelete);
        internalList.remove(toDelete);
    }

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItem);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the list.
     */
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSameItem(editedItem) && contains(editedItem)) {
            throw new DuplicateItemException();
        }

        target.updateData(editedItem);
    }

    public void setItems(InventoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        internalList.setAll(items);
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
    public ObservableList<Item> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Item> iterator() {
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
