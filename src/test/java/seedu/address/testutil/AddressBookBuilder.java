package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.internship.Internship;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withInternship("John", "Doe").build();}
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
     * Adds a new {@code Internship} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withInternship(Internship internship) {
        addressBook.addInternship(internship);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
