package soconnect.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
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
import soconnect.model.todo.Date;
import soconnect.model.todo.Description;
import soconnect.model.todo.Priority;
import soconnect.model.todo.Todo;

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

    /**
     * Generates sample {@code Todo}s for first time users.
     *
     * @return Array of sample {@code Todo}s.
     */
    public static Todo[] getSampleTodos() {
        // Generated date should be updated to current date
        Date now = new Date(LocalDate.now());

        Date tomorrow = new Date(LocalDate.now().plusDays(1));

        return new Todo[] {
            new Todo(new Description("Watch recorded lecture videos"), now, new Priority("high"),
                getTagSet("math")),
            new Todo(new Description("Revise content"), now, new Priority("medium"),
                getTagSet("english")),
            new Todo(new Description("Prepare for tutorials"), tomorrow, new Priority("low"),
                getTagSet("math", "chinese")),
            new Todo(new Description("Prepare presentation slides"), tomorrow, new Priority("high"),
                new HashSet<>())
        };
    }

    public static ReadOnlySoConnect getSampleSoConnect() {
        SoConnect sampleSC = new SoConnect();
        for (Person samplePerson : getSamplePersons()) {
            sampleSC.addPerson(samplePerson);
        }
        for (Tag sampleTag : getSampleTagList()) {
            sampleSC.addTag(sampleTag);
        }
        return sampleSC;
    }

    /**
     * Generates sample {@code TodoList} for first time users.
     *
     * @return Sample {@code TodoList}.
     */
    public static ReadOnlyTodoList getSampleTodoList() {
        TodoList sampleTodoList = new TodoList();
        for (Todo sampleTodo : getSampleTodos()) {
            sampleTodoList.addTodo(sampleTodo);
        }
        return sampleTodoList;
    }

    public static Tag[] getSampleTagList() {
        return new Tag[] {
            new Tag("friends"),
            new Tag("colleagues"),
            new Tag("neighbours"),
            new Tag("family"),
            new Tag("classmates"),
            new Tag("math"),
            new Tag("english"),
            new Tag("chinese")
        };
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
