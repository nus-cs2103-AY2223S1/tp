package seedu.address.testutil;

import seedu.address.model.ClientBook;
import seedu.address.model.client.Client;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ClientBook addressBook;

    public AddressBookBuilder() {
        addressBook = new ClientBook();
    }

    public AddressBookBuilder(ClientBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Client person) {
        addressBook.addPerson(person);
        return this;
    }

    public ClientBook build() {
        return addressBook;
    }
}
