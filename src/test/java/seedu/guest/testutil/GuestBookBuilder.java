package seedu.guest.testutil;

import seedu.guest.model.GuestBook;
import seedu.guest.model.guest.Guest;

/**
 * A utility class to help with building Guestbook objects.
 * Example usage: <br>
 *     {@code GuestBook ab = new GuestBookBuilder().withGuest("John", "Doe").build();}
 */
public class GuestBookBuilder {

    private GuestBook guestBook;

    public GuestBookBuilder() {
        guestBook = new GuestBook();
    }

    public GuestBookBuilder(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    /**
     * Adds a new {@code Guest} to the {@code GuestBook} that we are building.
     */
    public GuestBookBuilder withGuest(Guest guest) {
        guestBook.addGuest(guest);
        return this;
    }

    public GuestBook build() {
        return guestBook;
    }
}
