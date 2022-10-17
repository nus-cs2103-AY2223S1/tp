package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * A utility class containing an address book with a list of
 * {@code Profile} and {@code Event} objects to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical profiles.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Profile profile : TypicalProfiles.getTypicalProfiles()) {
            ab.addProfile(profile);
        }
        for (Event event : TypicalEvents.getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

}
