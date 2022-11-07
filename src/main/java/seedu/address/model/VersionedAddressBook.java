package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all states of AddressBooks
 */
public class VersionedAddressBook extends AddressBook {

    private List<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedAddressBook with a copy of addressBook in it.
     * @param addressBook State to save into VersionedAddressBook
     */
    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);

        this.addressBookStateList = new ArrayList<>();
        this.addressBookStateList.add(new AddressBook(addressBook));
        this.currentStatePointer = 0;
    }

    /**
     * Adds current AddressBook into AddressBook State List, while incrementing current state pointer.
     */
    public void commit() {
        this.addressBookStateList.subList(currentStatePointer + 1, this.addressBookStateList.size()).clear();
        this.addressBookStateList.add(new AddressBook(this));
        this.currentStatePointer++;
    }

    /**
     * Reverts to the previous AddressBook state and returns the previous AddressBook state.
     */
    public void undo() {
        this.currentStatePointer--;
        resetData(this.addressBookStateList.get(this.currentStatePointer));
    }

    /**
     * Reverts to the forward AddressBook state and returns the forward AddressBook state.
     */
    public void redo() {
        this.currentStatePointer++;
        resetData(this.addressBookStateList.get(this.currentStatePointer));
    }

    /**
     * Checks if there is a previous state in VersionedAddressBook.
     * @return If VersionedAddressBook can be undid
     */
    public boolean canUndo() {
        return !(this.currentStatePointer <= 0);
    }

    /**
     * Checks if there is a forward state in VersionedAddressBook.
     * @return If VersionedAddressBook can be redid
     */
    public boolean canRedo() {
        return this.addressBookStateList.size() > this.currentStatePointer + 1;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedAddressBook); // instanceof handles nulls
    }
}
