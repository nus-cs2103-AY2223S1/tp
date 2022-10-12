package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code AddressBook} with versioning that adheres to the specifications laid
 * out in the DeveloperGuide
 *
 */

public class VersionedAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);

        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(addressBook);
        currentStatePointer = 0;
    }

    public void commit() {
        removeUndoneStates();
        addressBookStateList.add(new VersionedAddressBook(this));
        currentStatePointer++;
    }

    public void undo() {
        currentStatePointer--;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof VersionedAddressBook) {
            VersionedAddressBook otherVersionedAddressBook = (VersionedAddressBook) other;
            return super.equals(otherVersionedAddressBook)
                    && addressBookStateList.equals(otherVersionedAddressBook.addressBookStateList)
                    && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
        }
        return false;
    }

    private void removeUndoneStates() {
        for (int i = currentStatePointer + 1; i < addressBookStateList.size(); ++i) {
            addressBookStateList.remove(i);
        }
    }

}
