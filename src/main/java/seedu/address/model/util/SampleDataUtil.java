package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
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
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Nurse(new Uid(1L), new Name("Betsy"), new Gender("F"), new Phone("98345432"),
                    new Email("betsy@example.com"),
                    new Address("Blk 431 Ang Mo Kio Ave 10, Singapore 560431 #01-01"),
                    getTagSet("Pediatric", "heartDiseaseSpecialist"), getUnavailableDateList("2022-11-11"),
                    getHomeVisitsList("2022-12-12,1:4"), getFullyScheduledDateList("2022-10-12")),
            new Nurse(new Uid(2L), new Name("Jason"), new Gender("M"), new Phone("98723432"),
                    new Email("jason@example.com"),
                    new Address("Blk 855 Woodlands Street 83, Singapore 730855 #01-01"),
                    getTagSet("asthmaSpecialist"), getUnavailableDateList("2022-10-09"),
                    getHomeVisitsList("2022-10-10,2:5", "2022-12-24,1:6"),
                    getFullyScheduledDateList("2022-10-10")),
            new Patient(new Uid(3L), new Name("Alex Yeoh"), new Gender("M"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), getDatesSlotsList("2022-11-11,1")),
            new Patient(new Uid("4"), new Name("Bernice Yu"), new Gender("F"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), getDatesSlotsList("2022-12-12,1")),
            new Patient(new Uid("5"), new Name("Charlotte Oliveiro"), new Gender("F"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"),
                    getDatesSlotsList("2022-10-10,2", "2022-09-24,1")),
            new Patient(new Uid("6"), new Name("David Li"), new Gender("M"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"), getDatesSlotsList("2022-10-10,2", "2022-12-24,1")),
            new Patient(new Uid("7"), new Name("Irfan Ibrahim"), new Gender("M"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"),
                    getDatesSlotsList("2022-10-10,1", "2022-09-24,1", "2023-01-13,3")),
            new Patient(new Uid("8"), new Name("Roy Balakrishnan"), new Gender("M"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"), getDatesSlotsList("2022-12-12,4"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
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
     * Returns a homeVisit list containing the list of strings given.
     */
    public static List<HomeVisit> getHomeVisitsList(String... strings) {
        return Arrays.stream(strings)
                .map(HomeVisit::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns an unavailable date list containing the list of strings given.
     */
    public static List<Date> getUnavailableDateList(String... strings) {
        return Arrays.stream(strings)
                .map(Date::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a fully scheduled date list containing the list of strings given.
     */
    public static List<Date> getFullyScheduledDateList(String... strings) {
        return Arrays.stream(strings)
                .map(Date::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a datesSlots list containing the list of strings given.
     */
    public static List<DateSlot> getDatesSlotsList(String... strings) {
        return Arrays.stream(strings)
                .map(DateSlot::new)
                .collect(Collectors.toList());
    }

}
