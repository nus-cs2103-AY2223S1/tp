package tracko.model.items;

import java.util.Iterator;

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
