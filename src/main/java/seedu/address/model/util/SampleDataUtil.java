package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.FilePath;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NetWorth;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Description EMPTY_DESCRIPTION = new Description(Person.EMPTY_FIELD_VALUE);


    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Description("Likes coffee"),
                    new NetWorth("$3000"), getMeetingTimeSet("17-10-2022-14:00"), new FilePath(""),
                    getTagSet("POTENTIAL")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Description("Likes tea"),
                    new NetWorth("$5000"), getMeetingTimeSet("18-10-2022-14:00"), new FilePath(""),
                    getTagSet("SECURED")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Description("Likes wine"),
                    new NetWorth("$1000000"), getMeetingTimeSet("19-10-2022-14:00"), new FilePath(""),
                    getTagSet("SECURED")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Description("Likes beer"),
                    new NetWorth("$2000"), getMeetingTimeSet("20-10-2022-14:00"), new FilePath(""),
                    getTagSet("POTENTIAL")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new Description("Likes fruit juice"),
                    new NetWorth("$1000"), getMeetingTimeSet("21-10-2022-14:00"), new FilePath(""),
                    getTagSet("POTENTIAL")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new Description("Likes hard liquor"),
                    new NetWorth("$3500"), getMeetingTimeSet("22-10-2022-14:00"), new FilePath(""),
                    getTagSet("SECURED"))
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
    public static Set<MeetingTime> getMeetingTimeSet(String... strings) {
        return Arrays.stream(strings)
                .map(MeetingTime::new)
                .collect(Collectors.toSet());
    }
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
