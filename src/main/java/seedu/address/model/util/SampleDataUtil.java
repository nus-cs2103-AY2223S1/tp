package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Phone;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    // Hard coded for sample purposes. Checks greater than or equal to account for additional samples.
    private static final int MAX_PERSON_ONE_INDEX = 6;
    private static final int MAX_GROUP_ONE_INDEX = 4;
    private static final int MAX_TASK_ONE_INDEX = 2;

    /**
     * Creates {@code Person} data samples for sample use.
     *
     * @return static list of sample {@code Person} with sample data.
     */
    public static Person[] getSamplePersons() {
        Person p1 = new Person("Alex Yeoh");
        p1.addAttribute(new Phone("87438807"));
        p1.addAttribute(new Email("alexyeoh@example.com"));
        p1.addAttribute(new Address("Blk 30 Geylang Street 29, #06-40"));
        p1.addTags("friends");

        Person p2 = new Person("Bernice Yu");
        p2.addAttribute(new Phone("99272758"));
        p2.addAttribute(new Email("berniceyu@example.com"));
        p2.addAttribute(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));
        p2.addTags("colleagues", "friends");

        Person p3 = new Person("Charlotte Oliveiro");
        p3.addAttribute(new Phone("93210283"));
        p3.addAttribute(new Email("charlotte@example.com"));
        p3.addAttribute(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"));
        p3.addTags("family");

        Person p4 = new Person("David Li");
        p4.addAttribute(new Phone("91031282"));
        p4.addAttribute(new Email("lidavid@example.com"));
        p4.addAttribute(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
        p4.addTags("family");

        Person p5 = new Person("Irfan Ibrahim");
        p5.addAttribute(new Phone("92492021"));
        p5.addAttribute(new Email("irfan@example.com"));
        p5.addAttribute(new Address("Blk 47 Tampines Street 20, #17-35"));
        p5.addTags("husband");

        Person p6 = new Person("Roy Balakrishnan");
        p6.addAttribute(new Phone("92624417"));
        p6.addAttribute(new Email("royb@example.com"));
        p6.addAttribute(new Address("Blk 45 Aljunied Street 85, #11-31"));
        p6.addTags("colleagues");

        return new Person[] {
            p1, p2, p3, p4, p5, p6
        };
    }

    /**
     * Creates {@code Group} data samples for sample use.
     *
     * @return static list of sample {@code Group} with sample data.
     */
    public static Group[] getSampleGroups() {
        Group g1 = new Group("ABC Company");
        g1.addAttribute(new Address("ABC building #06-06"));
        g1.addAttribute(new Email("companyEmail@email.com"));
        g1.addTags("Job", "Architecture");

        Group g2 = new Group("Project Alpha");
        g2.addAttribute(new Address("BCD building #07-07"));
        g2.addAttribute(new Email("companyEmail2@gmail.com"));
        g2.addTags("ProjectAlpha");
        g2.setParent(g1);

        Group g3 = new Group("Home contacts");
        g3.addAttribute(new Address("Blk 59 Choa Chu Kang North 5, #04-04"));
        g3.addAttribute(new Phone("63636363"));
        g3.addTags("Home");

        Group g4 = new Group("Children");
        g4.addTags("Children");
        g4.setParent(g3);

        return new Group[] {
            g1, g2, g3, g4
        };
    }

    /**
     * Creates {@code Task} data samples for sample use.
     *
     * @return static list of sample {@code Task} with sample data.
     */
    public static Task[] getSampleTask() {
        Task t1 = new Task("Update bugged feature", "Feature is bugged");
        t1.setCompletionTime(LocalDateTime.of(1, 3, 3, 1, 1, 3));
        t1.addTags("Bug", "Feature");

        Task t2 = new Task("Do the laundry", "Bring laundry to a laundromat.");
        t2.addTags("Urgent", "Chores");
        t2.addAttribute(new Phone("83839393"));
        t2.addAttribute(new Address("Maryland Rd 59, #01-02"));

        return new Task[] {
            t1, t2
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        Person[] persons = getSamplePersons();
        Group[] groups = getSampleGroups();
        Task[] tasks = getSampleTask();

        addParenting(persons, groups, tasks);

        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addTeam(sampleGroup);
        }

        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Task sampleTask : getSampleTask()) {
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

    /**
     * Adds parenting between the persons (p), groups (g) and tasks (t).
     *
     * The example parenting scheme will use (in one-indexed notation):
     * p6 -> g2
     * p3 -> g4
     * p4 -> g4
     * p5 -> g3
     * t1 -> g2
     * t2 -> p3
     */
    private static void addParenting(Person[] persons, Group[] groups, Task[] tasks) {

        assert persons.length >= MAX_PERSON_ONE_INDEX && groups.length >= MAX_GROUP_ONE_INDEX
                && tasks.length >= MAX_TASK_ONE_INDEX;

        persons[5].setParent(groups[1]);
        persons[2].setParent(groups[3]);
        persons[3].setParent(groups[3]);
        persons[4].setParent(groups[2]);
        tasks[0].setParent(groups[1]);
        tasks[1].setParent(persons[2]);
    }
}
