package seedu.uninurse.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Contains utility methods for populating {@code UninurseBook} with sample data.
 */
public class SampleDataUtil {
    public static final TaskList EMPTY_TASK_LIST = new TaskList();

    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_TASK_LIST,
                getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTaskList("Change dressing on right arm"),
                getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTaskList("Check blood glucose level", "Administer insulin dose"),
                getTagSet("neighbours")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_TASK_LIST,
                getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_TASK_LIST,
                getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_TASK_LIST,
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyUninurseBook getSampleUninurseBook() {
        UninurseBook sampleAb = new UninurseBook();
        for (Patient samplePerson : getSamplePersons()) {
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
     * Returns a {@code TaskList} containing the list of strings given.
     */
    public static TaskList getTaskList(String... strings) {
        ArrayList<Task> taskArrayList = Arrays.stream(strings)
                .map(Task::new).collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(taskArrayList);
    }

}
