package gim.testutil;

import gim.model.AddressBook;
import gim.model.person.Exercise;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withExercise("John", "Doe").build();}
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
     * Adds a new {@code Exercise} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withExercise(Exercise person) {
        addressBook.addExercise(person);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
