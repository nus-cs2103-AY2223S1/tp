package coydir.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import coydir.model.Database;
import coydir.model.ReadOnlyDatabase;
import coydir.model.person.Address;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Database} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new EmployeeId(), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Position("Chief Technology Officer"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new EmployeeId(), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Position("Chief of Staff"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new EmployeeId(), new Phone("93210283"),
                new Email("charlotte@example.com"), new Position("Chief Executive Officer"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new EmployeeId(), new Phone("91031282"),
                new Email("lidavid@example.com"), new Position("Janitor"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new EmployeeId(), new Phone("92492021"),
                new Email("irfan@example.com"), new Position("Senior Product Manager"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new EmployeeId(), new Phone("92624417"),
                new Email("royb@example.com"), new Position("UI/UX Designer"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyDatabase getSampleDatabase() {
        Database sampleAb = new Database();
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
