package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Manages the versioning of the address book data.
 */
public class VersionedAddressBook extends AddressBook {
    /** List of addressbook states **/
    private final List<ReadOnlyAddressBook> addressBookStateList;
    /** Index of current state **/
    private int currentStatePointer;
    private final Logger logger = LogsCenter.getLogger(VersionedAddressBook.class);

    /**
     * Creates a VersionedAddressBook and sets the initial state to the given {@code ReadOnlyAddressBook}.
     *
     * @param initialState Initial address book.
     */
    public VersionedAddressBook(ReadOnlyAddressBook initialState) {
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialState));
        super.resetData(initialState);
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     */
    public void commit() {
        if (currentStatePointer < addressBookStateList.size() - 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
        addressBookStateList.add(super.getCopyOfAddressBook());
        currentStatePointer++;

        logger.info("Versioned AddressBook State Change: [COMMIT][Updated State: "
                + (currentStatePointer + 1) + " / " + addressBookStateList.size() + "]");
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() {
        assert currentStatePointer > 0;
        currentStatePointer--;

        logger.info("Versioned AddressBook State Change: [UNDO][Updated State: "
                + (currentStatePointer + 1) + " / " + addressBookStateList.size() + "]");
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public void redo() {
        assert currentStatePointer < addressBookStateList.size() - 1;
        currentStatePointer++;

        logger.info("Versioned AddressBook State Change: [REDO][Updated State: "
                + (currentStatePointer + 1) + " / " + addressBookStateList.size() + "]");
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
