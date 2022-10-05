package foodwhere.testutil;

import foodwhere.model.AddressBook;
import foodwhere.model.stall.Stall;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Stall} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Stall stall) {
        addressBook.addPerson(stall);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
