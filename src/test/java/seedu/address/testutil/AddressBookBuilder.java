package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPatient("John", "Doe").build();}
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
     * Adds a new {@code Patient} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPatient(Patient patient) {
        addressBook.addPatient(patient);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
