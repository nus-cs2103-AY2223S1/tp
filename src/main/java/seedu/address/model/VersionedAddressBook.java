package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.exceptions.NextStateNotFoundException;
import seedu.address.model.exceptions.PreviousStateNotFoundException;

/**
 * Keeps track of the current state of the AddressBook by storing multiple
 * versions of the AddressBook after each successful command.
 */
public class VersionedAddressBook extends AddressBook {
    // Keeps track of the current version of AddressBook
    private int currentStatePointer;

    // Stores the different versions of AddressBook
    private ArrayList<ReadOnlyAddressBook> addressBookStateList = new ArrayList<>();

    /**
     * Creates an instance of VersionedAddressBook.
     * @param addressBook AddressBook to be initialised as the first state
     */
    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        this.currentStatePointer = 0;
        addressBookStateList.add(this.currentStatePointer, addressBook);
    }

    /**
     * Saves the current AddressBook state in its history.
     * @param addressBook AddressBook to save
     */
    public void commit(AddressBook addressBook) {
        requireNonNull(addressBook);
        AddressBook copiedAddressBook = new AddressBook(addressBook);
        this.addressBookStateList.add(copiedAddressBook);
        this.currentStatePointer++;
    }

    /**
     * Restores the previous AddressBook state from its history.
     * @param currentAddressBook AddressBook to set to the previous state
     * @throws PreviousStateNotFoundException if currentStatePointer is at the initialised state
     */
    public void undo(AddressBook currentAddressBook) throws PreviousStateNotFoundException {
        requireNonNull(currentAddressBook);

        if (this.currentStatePointer <= 0) {
            throw new PreviousStateNotFoundException();
        }
        this.currentStatePointer--;
        currentAddressBook.resetData(this.addressBookStateList.get(this.currentStatePointer));
    }

    /**
     * Restores the previously undone AddressBook state from its history.
     * @param currentAddressBook AddressBook to set to the before undone state
     * @throws NextStateNotFoundException if currentStatePointer is at the most updated state
     */
    public void redo(AddressBook currentAddressBook) throws NextStateNotFoundException {
        requireNonNull(currentAddressBook);

        int length = this.addressBookStateList.size();
        if (this.currentStatePointer + 1 >= length) {
            throw new NextStateNotFoundException();
        }
        this.currentStatePointer++;
        currentAddressBook.resetData(this.addressBookStateList.get(this.currentStatePointer));
    }
}
