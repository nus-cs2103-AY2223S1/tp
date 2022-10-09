package seedu.waddle.testutil;

import seedu.waddle.model.AddressBook;
import seedu.waddle.model.itinerary.Itinerary;

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
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Itinerary itinerary) {
        addressBook.addPerson(itinerary);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
