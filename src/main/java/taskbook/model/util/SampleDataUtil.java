package taskbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.TaskBook;
import taskbook.model.person.Address;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.person.Phone;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * Contains utility methods for populating {@code TaskBook} with sample data.
 */
public class SampleDataUtil {
    public static final Person ALEX_SAMPLE = new Person(new Name("Alex Yeoh"),
            new Phone("87438807"),
            new Email("alexyeoh@example.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends"));
    public static final Person BERNICE_SAMPLE = new Person(new Name("Bernice Yu"),
            new Phone("99272758"),
            new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            getTagSet("colleagues", "friends"));
    public static final Person CHARLOTTE_SAMPLE = new Person(new Name("Charlotte Oliveiro"),
            new Phone("93210283"),
            new Email("charlotte@example.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            getTagSet("neighbours"));
    public static final Person DAVID_SAMPLE = new Person(new Name("David Li"),
            new Phone("91031282"),
            new Email("lidavid@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            getTagSet("family"));
    public static final Person IRFAN_SAMPLE = new Person(new Name("Irfan Ibrahim"),
            new Phone("92492021"),
            new Email("irfan@example.com"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            getTagSet("classmates"));
    public static final Person ROY_SAMPLE = new Person(new Name("Roy Balakrishnan"),
            new Phone("92624417"),
            new Email("royb@example.com"),
            new Address("Blk 45 Aljunied Street 85, #11-31"),
            getTagSet("colleagues"));

    public static final Task ALEX_TASK_SAMPLE = new Todo(ALEX_SAMPLE,
            Assignment.FROM,
            new Description("Complete presentation template."),
            false);
    public static final Task BERNICE_TASK_SAMPLE = new Todo(BERNICE_SAMPLE,
            Assignment.TO,
            new Description("Send out marketing emails."),
            false);

    public static Person[] getSamplePersons() {
        return new Person[] {
            ALEX_SAMPLE,
            BERNICE_SAMPLE,
            CHARLOTTE_SAMPLE,
            DAVID_SAMPLE,
            IRFAN_SAMPLE,
            ROY_SAMPLE
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            ALEX_TASK_SAMPLE,
            BERNICE_TASK_SAMPLE
        };
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleAb = new TaskBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
