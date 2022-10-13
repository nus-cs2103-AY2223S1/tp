package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.supplyItem.SupplyItem;

/**
 * Wraps all data at the Inventory level
 */
public class Inventory implements ReadOnlyInventory {

    private final ObservableList<SupplyItem> inventory;

    {
        inventory = FXCollections.observableArrayList();
    }

    public Inventory() {}

    /**
     * Creates the Inventory using SupplyItems in the {@code toBeCopied}
     */
    public Inventory(ReadOnlyInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    @Override
    public ObservableList<SupplyItem> getInventory() {
        return inventory;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setItems(List<SupplyItem> items) {
        this.inventory.clear();
        this.inventory.addAll(items);
    }
    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);

        setItems(newData.getInventory());
    }

    /**
     * Adds a supply item to the inventory .
     */
    public void addItem(SupplyItem i) {
        inventory.add(i);
    }

    /**
     * Check a supply item for duplicates
     */
    public boolean hasItem(SupplyItem i) {
        return inventory.contains(i);
    }

    /**
     * Replaces the given supply item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the inventory.
     */
    public void setItem(SupplyItem target, Index targetIndex) {
        inventory.set(targetIndex.getZeroBased(), target);
    }

    /**
     * Deletes the supply item at the specified {@code Index}.
     */
    public void deleteItem(Index index) {
        inventory.remove(index.getZeroBased());
    }

    @Override
    public int hashCode() {
        return this.inventory.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if they are the same object.
                || (other instanceof Inventory
                && inventory.hashCode() == (((Inventory) other).inventory).hashCode()); //
    }
}
