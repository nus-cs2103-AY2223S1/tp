package seedu.uninurse.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Contains utility methods for populating {@code UninurseBook} with sample data.
 */
public class SampleDataUtil {
    public static final ConditionList EMPTY_CONDITION_LIST = new ConditionList();
    public static final TaskList EMPTY_TASK_LIST = new TaskList();

    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_CONDITION_LIST, EMPTY_TASK_LIST,
                getTagList("friends")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getConditionList("ACL tear"),
                getTaskList(new Task("Change dressing on right arm", new DateTime("16-10-2022 1015"))),
                getTagList("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getConditionList("Type I Diabetes", "Hypertension"),
                getTaskList(new Task("Check blood glucose level", new DateTime("25-11-2022 1300")),
                        new Task("Administer insulin dose", new DateTime("30-12-2022 1845"))),
                getTagList("neighbours")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_CONDITION_LIST, EMPTY_TASK_LIST,
                getTagList("family")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_CONDITION_LIST, EMPTY_TASK_LIST,
                getTagList("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_CONDITION_LIST, EMPTY_TASK_LIST,
                getTagList("colleagues"))
        };
    }

    public static ReadOnlyUninurseBook getSampleUninurseBook() {
        UninurseBook sampleUb = new UninurseBook();
        for (Patient samplePerson : getSamplePersons()) {
            sampleUb.addPerson(samplePerson);
        }
        return sampleUb;
    }

    /**
     * Creates a {@code ConditionList} using a given list of strings.
     * @param conditionStrings The given list of strings of conditions.
     * @return A {@code ConditionList} of the given conditions.
     */
    public static ConditionList getConditionList(String... conditionStrings) {
        List<Condition> conditionList = Arrays.stream(conditionStrings)
                .map(Condition::new)
                .collect(Collectors.toList());
        return new ConditionList(conditionList);
    }

    /**
     * Returns a {@code TagList} containing the list of strings given.
     */
    public static TagList getTagList(String... tagStrings) {
        List<Tag> tagList = Arrays.stream(tagStrings)
                .map(Tag::new)
                .collect(Collectors.toList());
        return new TagList(tagList);
    }

    /**
     * Returns a {@code TaskList} containing the list of tasks given.
     */
    public static TaskList getTaskList(Task... tasks) {
        ArrayList<Task> taskArrayList = Arrays.stream(tasks).collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(taskArrayList);
    }

}
