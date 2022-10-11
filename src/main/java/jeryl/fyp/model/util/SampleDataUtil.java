package jeryl.fyp.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jeryl.fyp.model.AddressBook;
import jeryl.fyp.model.ReadOnlyAddressBook;
import jeryl.fyp.model.person.Address;
import jeryl.fyp.model.person.Email;
import jeryl.fyp.model.person.Name;
import jeryl.fyp.model.person.Person;
import jeryl.fyp.model.person.StudentID;
import jeryl.fyp.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alice Yeoh"), new StudentID("A1438807T"), new Email("aliceyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), "neural network",
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new StudentID("A1272758C"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), "decision tree",
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new StudentID("A1210283B"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), "genetic algorithm",
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new StudentID("A1031282L"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), "computer network",
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new StudentID("A1492021I"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), "computer graphics",
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new StudentID("A1624417P"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), "circuit design",
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
}
