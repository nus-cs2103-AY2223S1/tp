package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.SupplyItem;

/**
 * Wraps all data at the Inventory level.
 */
public class Inventory implements ReadOnlyInventory {
    private final ObservableList<SupplyItem> supplyItems;

    {
        supplyItems = FXCollections.observableArrayList();
    }

    public Inventory() {}

    /**
     * Creates the Inventory using SupplyItems in the {@code toBeCopied}
     */
    public Inventory(ReadOnlyInventory inventory) {
        this();
        resetData(inventory);
    }

    @Override
    public ObservableList<SupplyItem> getSupplyItems() {
        return FXCollections.unmodifiableObservableList(supplyItems);
    }

    /// list overwrite operations

    /**
     * Replaces the content of the inventory with {@code supplyItems}.
     * {@code supplyItems} must not contain duplicate supplyItems.
     */
    public void setSupplyItems(List<SupplyItem> supplyItems) {
        this.supplyItems.clear();
        this.supplyItems.addAll(supplyItems);
    }

    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);
        setSupplyItems(newData.getSupplyItems());
    }

    /**
     * Adds a supply item to Inventory
     */
    public void addSupplyItem(SupplyItem item) {
        requireNonNull(item);
        this.supplyItems.add(item);
    }

    /**
     * Checks whether {@item} is a duplicate in Inventory.
     */
    public boolean hasSupplyItem(SupplyItem item) {
        requireNonNull(item);
        return this.supplyItems.contains(item);
    }

    /**
     * Replaces the given supply item {@code target} in the inventory with {@code editedSupplyItem}.
     * {@code target} must exist in the inventory.
     */
    public void setSupplyItem(SupplyItem target, Index targetIndex) {
        requireNonNull(target);
        requireNonNull(targetIndex);
        supplyItems.set(targetIndex.getZeroBased(), target);
    }

    /**
     * Deletes the supply item at the specified {@code Index}.
     */
    public void deleteSupplyItem(Index index) {
        requireNonNull(index);
        supplyItems.remove(index.getZeroBased());
    }

    @Override
    public int hashCode() {
        return this.supplyItems.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if they are the same object.
                || (other instanceof Inventory
                && supplyItems.hashCode() == ((Inventory) other).supplyItems.hashCode());
    }
}
