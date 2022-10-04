package seedu.guest.testutil;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.Guest;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private GuestBook guestBook;

    public AddressBookBuilder() {
        guestBook = new GuestBook();
    }

    public AddressBookBuilder(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Guest guest) {
        guestBook.addGuest(guest);
        return this;
    }

    public GuestBook build() {
        return guestBook;
    }
}
