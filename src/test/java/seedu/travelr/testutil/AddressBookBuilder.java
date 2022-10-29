package seedu.travelr.testutil;

import seedu.travelr.model.Travelr;
import seedu.travelr.model.trip.Trip;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withTrip("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Travelr addressBook;

    public AddressBookBuilder() {
        addressBook = new Travelr();
    }

    public AddressBookBuilder(Travelr addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Trip} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTrip(Trip trip) {
        addressBook.addTrip(trip);
        return this;
    }

    public Travelr build() {
        return addressBook;
    }
}
