package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;

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
     * Gets supply item in Inventory at the specified {@code index}.
     */
    public SupplyItem getSupplyItem(Index index) {
        requireNonNull(index);
        return this.supplyItems.get(index.getZeroBased());
    }

    /**
     * Checks whether {@code item} is a duplicate in Inventory.
     */
    public boolean hasSupplyItem(SupplyItem item) {
        requireNonNull(item);
        return this.supplyItems.contains(item);
    }

    /**
     * Checks whether {@code item} is a duplicate in Inventory excluding {@code excludedItem} from the check.
     */
    public boolean hasSupplyItemExcluding(SupplyItem item, SupplyItem excludedItem) {
        requireNonNull(item);
        requireNonNull(excludedItem);
        return this.supplyItems.stream()
                .filter(listItem -> !listItem.equals(excludedItem))
                .anyMatch(item::isSameItem);
    }

    /**
     * Returns the SupplyItem that is supplied by {@code supplier}, if any.
     * @param supplier the supplier to check for supply items supplied by
     * @return an Optional encapsulating the SupplyItem supplied by {@code supplier}
     */
    public Optional<SupplyItem> supplyItemSuppliedBy(Person supplier) {
        requireNonNull(supplier);
        return this.supplyItems.stream()
                .filter(listItem -> listItem.getSupplier().isSamePerson(supplier))
                .findFirst();
    }

    /**
     * Checks whether {@code supplier} is a supplier for any SupplyItem in the inventory.
     */
    public boolean hasSupplyItemSuppliedBy(Person supplier) {
        return this.supplyItems.stream()
                .anyMatch(listItem -> listItem.getSupplier().isSamePerson(supplier));
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
