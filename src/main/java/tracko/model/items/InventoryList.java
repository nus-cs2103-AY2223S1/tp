package tracko.model.items;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void setItems(InventoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        internalList.setAll(items);
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
