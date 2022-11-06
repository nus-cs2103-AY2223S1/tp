package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskPanel;
import seedu.address.model.TaskPanel;
import seedu.address.model.teammate.Address;
import seedu.address.model.teammate.Email;
import seedu.address.model.teammate.Name;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.teammate.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Project;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Contains utility methods for populating {@code AddressBook} and {@code TaskPanel} with sample data.
 */
public class SampleDataUtil {
    public static Teammate[] getSamplePersons() {
        return new Teammate[] {
            new Teammate(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Teammate(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Teammate(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Teammate(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Teammate(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Teammate(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Teammate sampleTeammate : getSamplePersons()) {
            sampleAb.addPerson(sampleTeammate);
        }
        return sampleAb;
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(
                new Title("Add task functionality."),
                false,
                Deadline.of(LocalDate.of(2023, 1 , 1)),
                Project.UNSPECIFIED,
                getContactSet("Alex Yeoh")
            ),
            new Task(
                new Title("Add task storage"),
                false,
                Deadline.of(TaskParserUtil.convertStringToLocalDate("01 January 2023")),
                new Project("CS2103T"),
                getContactSet("Bernice Yu"))
        };
    }

    public static ReadOnlyTaskPanel getSampleTaskPanel() {
        TaskPanel sampleTp = new TaskPanel();
        for (Task sampleTask : getSampleTasks()) {
            sampleTp.addTask(sampleTask);
        }
        return sampleTp;
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
     * Returns a contact set containing the list of strings given.
     */
    public static Set<Contact> getContactSet(String... strings) {
        return Arrays.stream(strings)
                .map(Contact::new)
                .collect(Collectors.toSet());
    }

}
