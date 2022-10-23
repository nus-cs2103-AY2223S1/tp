package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the versioning of the address book data.
 */
public class VersionedAddressBook extends AddressBook {
    /** List of adressbook states **/
    private final List<ReadOnlyAddressBook> addressBookStateList;
    /** Index of current state **/
    private int currentStatePointer;

    /**
     * Creates a VersionedAddressBook and sets the initial state to the given {@code ReadOnlyAddressBook}.
     *
     * @param initialState Initial address book.
     */
    public VersionedAddressBook(ReadOnlyAddressBook initialState) {
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     *
     * @param addressBook Address book to be added to state list.
     */
    public void commit(ReadOnlyAddressBook addressBook) {
        if (currentStatePointer < addressBookStateList.size() - 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
        addressBookStateList.add(new AddressBook(addressBook));
        currentStatePointer++;
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() {
        assert currentStatePointer > 0;
        currentStatePointer--;
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public void redo() {
        assert currentStatePointer < addressBookStateList.size() - 1;
        currentStatePointer++;
    }

    /**
     * Returns true if address book has states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if address book has states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    /**
     * Returns current state of address book.
     *
     * @return Current state of address book.
     */
    public ReadOnlyAddressBook getCurrentState() {
        return addressBookStateList.get(currentStatePointer);
    }
}
