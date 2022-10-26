package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Appointment appointmentAlex1 =
            new Appointment("Tinnitus", LocalDateTime.parse("2022-12-10T13:30"),
                    List.of(0, 0, 0), Set.of(Tag.EAR), false);
    private static final Appointment appointmentAlex2 =
            new Appointment("Monthly Checkup", LocalDateTime.parse("2022-12-01T10:00"),
                    List.of(0, 1, 0), Collections.emptySet(), false);
    private static final Appointment appointmentBernice1 =
            new Appointment("Sinus", LocalDateTime.parse("2022-12-15T14:30"),
                    List.of(0, 0, 0), Set.of(Tag.NOSE), false);
    private static final Appointment appointmentBernice2 =
            new Appointment("Tonsillitis", LocalDateTime.parse("2022-12-11T16:30"),
                    List.of(0, 0, 0), Set.of(Tag.THROAT), false);
    private static final Appointment appointmentCharlotte =
            new Appointment("Yearly Checkup", LocalDateTime.parse("2023-01-17T10:00"),
                    List.of(1, 0, 0), Collections.emptySet(), false);
    private static final Appointment appointmentDavid1 =
            new Appointment("COVID", LocalDateTime.parse("2022-10-10T08:30"),
                    List.of(0, 0, 0), Set.of(Tag.THROAT), true);
    private static final Appointment appointmentDavid2 =
            new Appointment("Sore Throat", LocalDateTime.parse("2022-12-13T10:00"),
                    List.of(0, 0, 0), Set.of(Tag.THROAT), false);
    private static final Appointment appointmentRoy =
            new Appointment("Sinus", LocalDateTime.parse("2022-12-28T19:30"),
                    List.of(0, 0, 0), Set.of(Tag.NOSE), false);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), List.of(appointmentAlex1, appointmentAlex2),
                getTagSet("Ear", "Throat")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    List.of(appointmentBernice1, appointmentBernice2), getTagSet("Nose", "Throat")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), List.of(appointmentCharlotte),
                getTagSet("Ear")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    List.of(appointmentDavid1, appointmentDavid2), getTagSet("Throat")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new ArrayList<>(),
                getTagSet("Ear")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), List.of(appointmentRoy),
                getTagSet("Nose"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {appointmentAlex1, appointmentAlex2, appointmentBernice1, appointmentBernice2,
            appointmentCharlotte, appointmentDavid1, appointmentDavid2, appointmentRoy};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
            addAppointmentsIntoAddressBook(samplePerson, sampleAb);
        }
        return sampleAb;
    }

    private static void addAppointmentsIntoAddressBook(Person samplePerson, AddressBook addressBook) {
        for (Appointment appointment : samplePerson.getAppointments()) {
            addressBook.addAppointment(appointment);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::convertToTag)
                .collect(Collectors.toSet());
    }

}
