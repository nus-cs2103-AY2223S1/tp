package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.foodrem.commons.exceptions.ItemStorageFullException;
import seedu.foodrem.model.item.exceptions.DuplicateItemException;
import seedu.foodrem.model.item.exceptions.ItemNotFoundException;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * An item is considered unique by comparing using {@link Item#isSameItem(Item)}. As such, adding and updating of
 * items uses {@link Item#isSameItem(Item)} for equality to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueItemList. However, the removal of an item uses {@link Item#equals(Object)}
 * to ensure that the item with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Item#isSameItem(Item)
 */
public class UniqueItemList implements Iterable<Item> {
    private static final int MAX_ITEMS = 10000;
    private final ObservableList<Item> internalList = FXCollections.observableArrayList();
    private final ObservableList<Item> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns {@code true} if the list contains an equivalent item as the given argument.
     *
     * @return true if the item list contains the item, false otherwise.
     */
    public boolean contains(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItem);
    }

    /**
     * Returns true is the storage is full, false otherwise.
     *
     * @return true if the storage is full, false otherwise.
     */
    public boolean isStorageFull() {
        return internalList.size() == MAX_ITEMS;
    }

    /**
     * Adds an item to the list.
     * The item must not already exist in the list.
     *
     * @param toAdd the item to add.
     */
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        if (isStorageFull()) {
            throw new ItemStorageFullException(MAX_ITEMS);
        }
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the list.
     *
     * @param target the item to be removed from the list.
     * @param editedItem the item to be added to the list.
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

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     *
     * @param toRemove the item to be removed from the list.
     */
    public void remove(Item toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Replaces all the items in the list.
     *
     * @param replacement the new item list.
     */
    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     *
     * @param items the new item list.
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }
        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Item> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the iterator of the internalList.
     */
    @Override
    public Iterator<Item> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns {@code true} if both {@link UniqueItemList} have the same items.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueItemList
                && internalList.equals(((UniqueItemList) other).internalList));
    }

    /**
     * Returns the hashCode of the unique item list.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns {@code true} if {@code items} contains only unique items.
     *
     * @return true if the items contain only unique items, false otherwise.
     */
    private boolean itemsAreUnique(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSameItem(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
