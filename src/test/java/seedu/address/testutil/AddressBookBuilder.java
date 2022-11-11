package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.teammate.Teammate;

/**
 * A utility class to help with building AddressBook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withTeammate("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    /**
     * Adds a new {@code Teammate} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTeammate(Teammate teammate) {
        addressBook.addTeammate(teammate);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
