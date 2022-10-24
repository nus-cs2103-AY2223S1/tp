package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.StudentClass;
import seedu.address.model.person.subject.Subject;
import seedu.address.model.person.subject.SubjectHandler;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                       new Address("Blk 30 Geylang Street 29, #06-40"), new StudentClass("1.2"), getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                       new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new StudentClass("2.1"),
                       getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                       new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new StudentClass("3.6"),
                       getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                       new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new StudentClass("4.2"),
                       getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                       new Address("Blk 47 Tampines Street 20, #17-35"), new StudentClass("2.6"),
                       getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                       new Address("Blk 45 Aljunied Street 85, #11-31"), new StudentClass("3.10"),
                       getRemarkSet("family"),
                       new SubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, "
                               + "56.0, 0.4, 2.0] %%math: CA1:[80.0, 100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]"),
                       getTagSet("colleagues"))
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
     * Returns a remark set containing the list of strings given.
     */
    public static Set<Remark> getRemarkSet(String... strings) {
        return Arrays.stream(strings)
                     .map(Remark::new)
                     .collect(Collectors.toSet());
    }

    /**
     * Returns a remark set containing the list of strings given.
     */
    public static Set<Subject> getSubjectSet(String... strings) {
        return Arrays.stream(strings)
                     .map(Subject::new)
                     .collect(Collectors.toSet());
    }
}
