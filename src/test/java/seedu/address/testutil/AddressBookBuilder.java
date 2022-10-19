package seedu.address.testutil;

import seedu.address.model.AddressBook;

/**
 * A utility class to help with building Addressbook objects.
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public AddressBook build() {
        return addressBook;
    }
}
