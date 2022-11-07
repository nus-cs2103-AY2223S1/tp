package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Occupation("STUDENT"), new Name("Alex Yeoh"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"), new Tutorial("T08"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends"), new Social(),
                            getGroupSet("friends")),
            new Person(new Occupation("TA"), new Name("Bernice Yu"),
                    new Phone("99272758"), new Email("berniceyu@example.com"), new Tutorial("F13"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends"),
                    new Social(), getGroupSet("friends", "colleagues")),
            new Person(new Occupation("PROFESSOR"), new Name("Charlotte Oliveiro"),
                    new Phone("93210283"), new Email("charlotte@example.com"), new Tutorial("W10"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours"), new Social(),
                            getGroupSet("neighbours")),
            new Person(new Occupation("STUDENT"), new Name("David Li"),
                    new Phone("91031282"), new Email("lidavid@example.com"), new Tutorial("T08"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family"),
                    new Social(), getGroupSet("family")),
            new Person(new Occupation("STUDENT"), new Name("Irfan Ibrahim"),
                    new Phone("92492021"), new Email("irfan@example.com"), new Tutorial("T09"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates"), new Social(),
                    getGroupSet("classmates")),
            new Person(new Occupation("STUDENT"), new Name("Roy Balakrishnan"),
                    new Phone("92624417"), new Email("royb@example.com"), new Tutorial("T13"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"), new Social(),
                    getGroupSet("colleagues"))
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
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

}
