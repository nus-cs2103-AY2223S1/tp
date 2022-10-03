package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withCompany("John", "Doe").build();}
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
     * Adds a new {@code Company} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCompany(Company company) {
        addressBook.addCompany(company);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
