package seedu.travelr.testutil;

import seedu.travelr.model.AddressBook;
import seedu.travelr.model.trip.Trip;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withTrip("John", "Doe").build();}
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
     * Adds a new {@code Trip} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTrip(Trip trip) {
        addressBook.addTrip(trip);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
