package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * A utility class that gives an {@code AddressBook} with both typical persons and typical events.
 */
public class TypicalAddressBook {

    /**
     * Returns an {@code AddressBook} with all the typical events and persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : TypicalEvents.getTypicalEvents()) {
            ab.addEvent(event);
        }
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }
}
