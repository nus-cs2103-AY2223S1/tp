package longtimenosee.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import longtimenosee.model.AddressBook;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Person;


/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event WITH_ALICE = new EventBuilder().withName("Alice Pauline")
            .withDate("2022-12-12")
            .withDescription("Lunch with Alice Pauline")
            .withDuration("10:00__12:00")
            .build();
    public static final Event WITH_BENSON = new EventBuilder().withName("Benson Meier")
            .withDate("2022-11-11")
            .withDescription("Coffee with Benson")
            .withDuration("15:00__16:00")
            .build();
    public static final Event WITH_CARL = new EventBuilder().withName("Carl Kurz")
            .withDate("2022-12-05")
            .withDescription("Dinner with Carl")
            .withDuration("19:00__20:30")
            .build();
    public static final Event WITH_ELLE = new EventBuilder().withName("Elle Meyer")
            .withDate("2022-12-20")
            .withDescription("Lunch with Elle")
            .withDuration("12:00__13:00")
            .build();
    public static final Event WITH_FIONA = new EventBuilder().withName("Fiona Kunz")
            .withDate("2022-12-20")
            .withDescription("Coffee with Fiona")
            .withDuration("14:30__15:30")
            .build();
    public static final Event WITH_GEORGE = new EventBuilder().withName("George Best")
            .withDate("2022-10-10")
            .withDescription("Drinks with Mr Best")
            .withDuration("21:00__23:00")
            .build();



    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(WITH_ALICE, WITH_BENSON, WITH_CARL, WITH_ELLE, WITH_FIONA, WITH_GEORGE));
    }
}

