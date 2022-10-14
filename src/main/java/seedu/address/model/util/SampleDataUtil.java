package seedu.address.model.util;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.person.UniqueTagTypeMap;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Note EMPTY_NOTE = new Note("");

    public static Person[] getSamplePersons() {
        //        return null;
        //    to be rewritten.
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagTypeMap("friends"),
                    new Status("Application Received"), new Note("Has a dog.")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagTypeMap("colleagues", "friends"),
                    new Status("Application Received"), EMPTY_NOTE),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagTypeMap("neighbours"), new Status("OA in Progress"), EMPTY_NOTE),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagTypeMap("family"), new Status("Application Withdrawn"), EMPTY_NOTE),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagTypeMap("classmates"), new Status("Rejected"),
                    new Note("Proficient in Python.")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagTypeMap("colleagues"), new Status("Interview in Progress"), EMPTY_NOTE)
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
    public static UniqueTagTypeMap getTagTypeMap(String... strings) {
        UniqueTagTypeMap tags = new UniqueTagTypeMap();
        for (String tag: strings) {
            tags.mergeTag(new TagType("Skills", new Prefix("st/")), new Tag(tag));
        }
        return tags;
    }

}
