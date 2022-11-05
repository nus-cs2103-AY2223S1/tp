package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("male"), new Date("13/02/1988"),
                    new Uid()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("female"),
                    new Date("11/05/1968"), new Uid()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("female"), new Date("04/12/2002"),
                    new Uid()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Gender("male"),
                    new Date("29/07/1996"), new Uid()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("male"), new Date("02/06/1984"),
                    new Uid()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("male"), new Date("02/11/1954"),
                    new Uid())
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventTitle("2 2 Sale"), new Date("02/02/2022"), new StartTime("02:00"),
                    new Purpose("For football fans")),
            new Event(new EventTitle("4 4 Sale"), new Date("04/04/2022"), new StartTime("16:00"),
                    new Purpose("For computer scientists")),
            new Event(new EventTitle("6 6 Sale") , new Date("06/06/2022"), new StartTime("06:00"),
                    new Purpose("For car enthusiasts")),
            new Event(new EventTitle("8 8 Sale") , new Date("08/08/2022"), new StartTime("20:00"),
                    new Purpose("For bread lovers")),
            new Event(new EventTitle("10 10 Sale") , new Date("10/10/2022"), new StartTime("10:00"),
                    new Purpose("For watch hobbyists")),
            new Event(new EventTitle("12 12 Sale") , new Date("12/12/2022"), new StartTime("12:00"),
                    new Purpose("For stationery buyers"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }
}
