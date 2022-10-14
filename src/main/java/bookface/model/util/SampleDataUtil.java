package bookface.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import bookface.model.BookFace;
import bookface.model.ReadOnlyBookFace;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Person;
import bookface.model.person.Phone;
import bookface.model.tag.Tag;

/**
 * Contains utility methods for populating {@code BookFace} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new HashSet<>(),
                getTagSetFromStringArray("colleagues"))
        };
    }

    public static ReadOnlyBookFace getSampleBookFace() {
        BookFace sampleAb = new BookFace();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSetFromStringArray(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static <T> Set<T> getSetFromStringArray(Function<String, T> constructor, String... strings) {
        return Arrays.stream(strings)
                .map(constructor)
                .collect(Collectors.toSet());
    }
}
