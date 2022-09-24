package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.ApplicationProcess;
import seedu.address.model.person.Date;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.Website;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Google"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Position("Backend Intern"),
                new ApplicationProcess("Apply"), new Date("23-09-2022"),
                new Website("https://careers.google.com/jobs"), getTagSet("friends")),
            new Person(new Name("TikTok"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Position("Frontend Intern"),
                new ApplicationProcess("offer"), new Date("05-10-2022"),
                new Website("https://tiktok.com/careers"), getTagSet("colleagues", "friends")),
            new Person(new Name("Amazon"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Position("Fullstack Intern"),
                new ApplicationProcess("accepted"), new Date("01-08-2022"),
                new Website("https://amazon.com/careers"), getTagSet("neighbours")),
            new Person(new Name("Grab"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Position("Data Analyst Intern"),
                new ApplicationProcess("assessment"), new Date("01-11-2022"),
                new Website("https://grab.com/careers"), getTagSet("family")),
            new Person(new Name("Meta"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Position("Software Engineer Intern"),
                new ApplicationProcess("rejected"), new Date("10-07-2022"),
                new Website("https://grab.com/careers"), getTagSet("classmates")),
            new Person(new Name("Shopback"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Position("AI Intern"),
                new ApplicationProcess("interview"), new Date("10-10-2022"),
                new Website("https://shopback.com/careers"), getTagSet("colleagues"))
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

}
