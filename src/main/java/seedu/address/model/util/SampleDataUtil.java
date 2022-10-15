package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePatients() {
        return new Person[] {
            new Nurse(new Uid(1L), new Name("Betsy"), new Gender("F"), new Phone("98345432"),
                new Email("betsy@example.com"), new Address("Blk 431 Ang Mo Kio Ave 10, Singapore 560431 #01-01"),
                getTagSet("Pediatric", "heartDiseaseSpecialist")),
            new Nurse(new Uid(2L), new Name("Jason"), new Gender("M"), new Phone("98723432"),
                new Email("jason@example.com"), new Address("Blk 855 Woodlands Street 83, Singapore 730855 #01-01"),
                getTagSet("asthmaSpecialist")),
            new Patient(new Uid(3L), new Name("Alex Yeoh"), new Gender("M"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getDatesTimesList("2022-11-11T14:00")),
            new Patient(new Uid(4L), new Name("Bernice Yu"), new Gender("F"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getDatesTimesList("2022-12-12T20:00")),
            new Patient(new Uid(5L), new Name("Charlotte Oliveiro"), new Gender("F"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                getDatesTimesList("2022-10-10T09:00", "2022-09-24T08:00")),
            new Patient(new Uid(6L), new Name("David Li"), new Gender("M"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getDatesTimesList("2022-10-10T09:00", "2022-12-24T08:00")),
            new Patient(new Uid(7L), new Name("Irfan Ibrahim"), new Gender("M"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                getDatesTimesList("2022-10-10T09:00", "2022-09-24T08:00", "2023-01-13T11:00")),
            new Patient(new Uid(8L), new Name("Roy Balakrishnan"), new Gender("M"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getDatesTimesList("2022-12-12T13:00"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePatients()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a datesTimes list containing the list of strings given.
     */
    public static List<DateTime> getDatesTimesList(String...strings) {
        return Arrays.stream(strings)
                .map(DateTime::new)
                .collect(Collectors.toList());
    }

}
