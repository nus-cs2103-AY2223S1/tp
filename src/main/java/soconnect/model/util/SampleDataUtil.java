package soconnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.SoConnect;
import soconnect.model.TodoList;
import soconnect.model.person.Address;
import soconnect.model.person.Email;
import soconnect.model.person.Name;
import soconnect.model.person.Person;
import soconnect.model.person.Phone;
import soconnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SoConnect} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlySoConnect getSampleSoConnect() {
        SoConnect sampleSC = new SoConnect();
        for (Person samplePerson : getSamplePersons()) {
            sampleSC.addPerson(samplePerson);
        }
        return sampleSC;
    }

    public static ReadOnlyTodoList getSampleTodoList() {
        TodoList sampleTodoList = new TodoList();
        /*
        for (Person samplePerson : getSamplePersons()) {
            sampleSC.addPerson(samplePerson);
        }
         */
        return sampleTodoList;
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
