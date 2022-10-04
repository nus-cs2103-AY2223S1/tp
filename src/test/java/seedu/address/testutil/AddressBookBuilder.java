package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.profile.Profile;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withProfile("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Profile} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withProfile(Profile profile) {
        addressBook.addProfile(profile);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
