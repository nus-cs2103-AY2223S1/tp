package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.customer.Customer;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withCustomer("John", "Doe").build();}
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
     * Adds a new {@code Customer} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
