package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueItemList items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        items = new UniqueItemList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Items in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addItem(Item p) {
        items.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedItem} must not be the same as another existing person in the address book.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);

        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && items.equals(((AddressBook) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
