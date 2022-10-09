package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withItem("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Item} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withItem(Item item) {
        addressBook.addItem(item);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
